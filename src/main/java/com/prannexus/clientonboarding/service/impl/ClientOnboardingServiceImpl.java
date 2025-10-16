package com.prannexus.clientonboarding.service.impl;

import com.prannexus.clientonboarding.dto.CandidateDTO;
import com.prannexus.clientonboarding.dto.ClientOnboardingDto;
import com.prannexus.clientonboarding.dto.NemsEmployeeDto;
import com.prannexus.clientonboarding.dto.UserInputDTO;
import com.prannexus.clientonboarding.dto.UserRolesDTO;
import com.prannexus.clientonboarding.entity.ClientDbMappingEntity;
import com.prannexus.clientonboarding.entity.ClientOnboardingEntity;
import com.prannexus.clientonboarding.mapper.ClientOnboardingMapper;
import com.prannexus.clientonboarding.repository.ClientDbMappingRepository;
import com.prannexus.clientonboarding.repository.ClientOnboardingRepository;
import com.prannexus.clientonboarding.service.ClientOnboardingService;
import com.prannexus.clientonboarding.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientOnboardingServiceImpl implements ClientOnboardingService {

    private final ClientOnboardingRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientDbMappingRepository dbMappingRepository;

    @Autowired
    private EmailService emailService;

    private static final String BASE_NEXUSHIRE_DB = "smarthireprod";
    private static final String BASE_NEMS_DB = "emsportalprod";
    private static final String BASE_LOGIN_DB = "loginprod";
    private static final String BASE_MASTERCONFIG_DB = "masterconfigurationprod";

    public ClientOnboardingServiceImpl(ClientOnboardingRepository repository) {
        this.repository = repository;
    }

    // ------------------- SIGNUP -------------------
    @Override
    public ClientOnboardingDto signup(ClientOnboardingDto dto) {
        repository.findByEmail(dto.getEmail())
                .ifPresent(c -> { throw new RuntimeException("Client already registered with this email"); });

        String clientCode = generateClientCode(dto.getClientName());

        ClientOnboardingEntity entity = ClientOnboardingMapper.toEntity(dto);
        entity.setClientCode(clientCode);
        entity.setApprovalStatus(ClientOnboardingEntity.ApprovalStatus.PENDING);
        entity.setCreatedDate(LocalDateTime.now());
        ClientOnboardingEntity saved = repository.save(entity);

        emailService.sendEmail(
            dto.getEmail(),
            "Registration Received",
            "Thank you for signing up! Your registration is pending approval. Your client code: " + clientCode
        );

        return ClientOnboardingMapper.toDto(saved);
    }

    // ------------------- APPROVE CLIENT -------------------
    @Transactional
    @Override
    public ClientOnboardingDto approveClient(String clientCode, String approvedBy, String remarks) {
        ClientOnboardingEntity entity = repository.findByClientCode(clientCode)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        String hrEmail = entity.getEmail();
        String password = generateRandomPassword();
     // Create candidate first
        createCandidate(clientCode, entity.getClientName());

        // 1️⃣ Create default employees in EMS first and get employeeCode
        String hrEmployeeCode;
        try {
            hrEmployeeCode = createDefaultEmployeesInEMS(clientCode, entity.getClientName());
        } catch (Exception e) {
            throw new RuntimeException("Failed to create default employees in EMS: " + e.getMessage(), e);
        }

        // 2️⃣ Create HR user in loginprod using the employeeCode
        try {
            createHRUser(hrEmail, password, clientCode, entity.getClientName(), hrEmployeeCode);
        } catch (Exception e) {
            throw new RuntimeException("Cannot approve client: failed to create HR user: " + e.getMessage(), e);
        }

        // 3️⃣ Update client approval info
        entity.setApprovalStatus(ClientOnboardingEntity.ApprovalStatus.APPROVED);
        entity.setApprovedBy(approvedBy);
        entity.setModifiedDate(LocalDateTime.now());
        entity.setRemarks(remarks);
        ClientOnboardingEntity saved = repository.save(entity);

        // 4️⃣ Tenant DB names
        String nexushireDb = "nexushire_" + clientCode.toLowerCase();
        String nemsDb = "nems_" + clientCode.toLowerCase();
        String loginDb = "loginprod_" + clientCode.toLowerCase();
        String masterConfigDb = "masterconfigurationprod_" + clientCode.toLowerCase();

        // 5️⃣ Save DB mapping
        ClientDbMappingEntity mapping = new ClientDbMappingEntity();
        mapping.setClientCode(clientCode);
        mapping.setNexushireDb(nexushireDb);
        mapping.setNemsDb(nemsDb);
        mapping.setLoginDb(loginDb);
        mapping.setMasterConfigDb(masterConfigDb);
        mapping.setCreatedDate(LocalDateTime.now());
        dbMappingRepository.save(mapping);

        // 6️⃣ Create tenant databases
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + nexushireDb);
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + nemsDb);
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + loginDb);
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS " + masterConfigDb);

        // 7️⃣ Clone schemas
        cloneDatabaseSchema(BASE_NEXUSHIRE_DB, nexushireDb);
        cloneDatabaseSchema(BASE_NEMS_DB, nemsDb);
        cloneDatabaseSchema(BASE_LOGIN_DB, loginDb);
        cloneDatabaseSchema(BASE_MASTERCONFIG_DB, masterConfigDb);

        // 8️⃣ Copy user_rolename table in loginprod clone
        try {
            jdbcTemplate.execute(String.format(
                "INSERT INTO %s.user_role_name SELECT * FROM %s.user_role_name", loginDb, BASE_LOGIN_DB
            ));
        } catch (Exception e) {
            System.out.println("Failed to copy user_role_name: " + e.getMessage());
        }

        // 9️⃣ Send approval email with credentials
        emailService.sendEmail(
            hrEmail,
            "Your Organization Approved",
            "Your organization " + entity.getClientName() + " has been approved.\n" +
            "Login credentials:\nUsername: " + hrEmail + "\nPassword: " + password + "\n" +
            "Login URL: https://portal.prannexus.in/login"
        );

        return ClientOnboardingMapper.toDto(saved);
    }

    // ------------------- CREATE DEFAULT EMPLOYEES IN EMS -------------------
    private String createDefaultEmployeesInEMS(String clientCode, String clientName) {
        RestTemplate restTemplate = new RestTemplate();

        NemsEmployeeDto hrEmployee = new NemsEmployeeDto();
        hrEmployee.setFirstName("HR");
        hrEmployee.setLastName(clientName);
        hrEmployee.setEmailId("hr@" + clientCode.toLowerCase() + ".com");
        hrEmployee.setClientCode(clientCode);
        hrEmployee.setRole("HR");
        hrEmployee.setStatus("108"); // instead of "Active"

        hrEmployee.setWorkflowStatus("Completed");
        hrEmployee.setDateOfJoining(LocalDate.now());
        hrEmployee.setWorkType("permanent");
        hrEmployee.setPersonalEmail("hr@" + clientCode.toLowerCase() + ".com");

        ResponseEntity<NemsEmployeeDto> response = restTemplate.postForEntity(
            "http://localhost:8085/nems/Pransquare/nems/employee/createOrUpdateEmployee",
            hrEmployee,
            NemsEmployeeDto.class
        );

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Failed to create HR employee in EMS");
        }

        return response.getBody().getEmployeeCode(); // Return generated employeeCode
    }

    // ------------------- CREATE HR USER -------------------
    private void createHRUser(String username, String password, String clientCode, String clientName, String employeeCode) {
        UserInputDTO hrDto = new UserInputDTO();
        hrDto.setName(clientName);
        hrDto.setEmail(username);
        hrDto.setPassword(password);
        hrDto.setClientCode(clientCode);
        hrDto.setEmpCode(employeeCode);


        UserRolesDTO hrRole = new UserRolesDTO();
        hrRole.setRoleTypeCode("HR");
        hrDto.setRoles(List.of(hrRole));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
            "http://localhost:8085/usermanagement/register",
            hrDto,
            String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create HR user via usermanagement service");
        }
    }
    private void createCandidate(String clientCode, String clientName) {
        RestTemplate restTemplate = new RestTemplate();

        CandidateDTO candidate = new CandidateDTO();
        candidate.setFirstName("HR");
        candidate.setLastName(clientName);
        candidate.setEmailAddres("hr@" + clientCode.toLowerCase() + ".com");
        candidate.setEmploymentType("permanent");
        candidate.setStatus("108"); 
        candidate.setWorkflowStatus("Completed");
        candidate.setCreatedBy("Prannexus");
        candidate.setModifiedBy("Prannexus");
        candidate.setDob(LocalDate.now());
        candidate.setGender("Male");
        candidate.setClientCode(clientCode);

        // Optional fields to prevent Hibernate errors
        candidate.setBudgetComment(null);
        candidate.setManagementComment(null);
        candidate.setVendorName(null);
        candidate.setEmergencyMobileNo(null);

        // Correct URL with 'isSaved' as query parameter
        String url = "http://localhost:8085/nexushire/api/candidate/create?isSaved=true";

        ResponseEntity<CandidateDTO> response = restTemplate.postForEntity(
            url,
            candidate,
            CandidateDTO.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to create candidate");
        }
    }

    // ------------------- CLONE DATABASE -------------------
    private void cloneDatabaseSchema(String sourceDb, String targetDb) {
        List<String> tables = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables WHERE table_schema = ? AND table_type = 'BASE TABLE'",
                String.class, sourceDb);

        tables.forEach(table -> {
            try {
                jdbcTemplate.execute(String.format("CREATE TABLE %s.%s LIKE %s.%s", targetDb, table, sourceDb, table));
            } catch (Exception e) {
                if (!e.getMessage().contains("already exists")) throw new RuntimeException(e);
            }
        });

        // Clone views
        jdbcTemplate.query(
                "SELECT table_name, view_definition FROM information_schema.views WHERE table_schema = ?",
                (rs, rowNum) -> new String[]{rs.getString("table_name"), rs.getString("view_definition")},
                sourceDb
        ).forEach(view -> {
            String viewName = view[0];
            String viewDef = view[1];
            try {
                jdbcTemplate.execute("DROP VIEW IF EXISTS " + targetDb + "." + viewName);
                jdbcTemplate.execute("CREATE VIEW " + targetDb + "." + viewName + " AS " + viewDef);
            } catch (Exception e) {
                System.out.println("Skipping view: " + viewName + " - " + e.getMessage());
            }
        });
    }

    // ------------------- HELPER METHODS -------------------
    private String generateRandomPassword() {
        int length = 10;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder sb = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < length; i++) sb.append(chars.charAt(random.nextInt(chars.length())));
        return sb.toString();
    }

    private String generateClientCode(String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
        String cleaned = companyName.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String code = cleaned.length() >= 3 ? cleaned.substring(0, 3) : cleaned;
        String uniqueCode = code;
        int suffix = 1;
        while (repository.findByClientCode(uniqueCode).isPresent()) {
            uniqueCode = code + suffix;
            suffix++;
        }
        return uniqueCode;
    }

    @Override
    public List<ClientOnboardingDto> getAllClients() {
        return ClientOnboardingMapper.toDtoList(repository.findAll());
    }

    @Override
    public ClientOnboardingDto getClientByCode(String clientCode) {
        ClientOnboardingEntity entity = repository.findByClientCode(clientCode)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return ClientOnboardingMapper.toDto(entity);
    }
}
