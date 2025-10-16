package com.prannexus.clientonboarding.dto;

import java.time.LocalDate;

public class CandidateDTO {

    // Primary & mandatory fields
    private Integer id; // optional, usually null for new insert
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddres;
    private String employmentType;
    private String status;
    private String workflowStatus;
    private String createdBy;
    private String modifiedBy;

    // Optional personal details
    private LocalDate dob;
    private String gender;
    private String nationality;
    private String primarySkill;

    // Contact & identification
    private String primaryMobileNo;
    private String alternateMobileNo;
    private String emergencyMobileNo;
    private String aadhaarNo;
    private String panId;
    private String passportNumber;

    // Document details
    private String documentType;
    private String documentNumber;
    private String documentName;

    // Employment / project details
    private String requirementId;
    private String requirementDesc;
    private String expectedCtc;
    private String grantedCtc;
    private String budget;
    private String budgetComment;       // optional, can be null
    private String managementComment;   // optional, can be null
    private String ctcType;
    private String workLocation;
    private String visa;
    private String visaCountry;
    private String visaExpiry;
    private String expiry;
    private String vendorName;

    // Date tracking
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    // NexusHire specific / onboarding
    private Boolean isSaved; // mandatory for NexusHire API
    private String clientCode; // for client onboarding

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailAddres() { return emailAddres; }
    public void setEmailAddres(String emailAddres) { this.emailAddres = emailAddres; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getWorkflowStatus() { return workflowStatus; }
    public void setWorkflowStatus(String workflowStatus) { this.workflowStatus = workflowStatus; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    public String getModifiedBy() { return modifiedBy; }
    public void setModifiedBy(String modifiedBy) { this.modifiedBy = modifiedBy; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getPrimarySkill() { return primarySkill; }
    public void setPrimarySkill(String primarySkill) { this.primarySkill = primarySkill; }

    public String getPrimaryMobileNo() { return primaryMobileNo; }
    public void setPrimaryMobileNo(String primaryMobileNo) { this.primaryMobileNo = primaryMobileNo; }

    public String getAlternateMobileNo() { return alternateMobileNo; }
    public void setAlternateMobileNo(String alternateMobileNo) { this.alternateMobileNo = alternateMobileNo; }

    public String getEmergencyMobileNo() { return emergencyMobileNo; }
    public void setEmergencyMobileNo(String emergencyMobileNo) { this.emergencyMobileNo = emergencyMobileNo; }

    public String getAadhaarNo() { return aadhaarNo; }
    public void setAadhaarNo(String aadhaarNo) { this.aadhaarNo = aadhaarNo; }

    public String getPanId() { return panId; }
    public void setPanId(String panId) { this.panId = panId; }

    public String getPassportNumber() { return passportNumber; }
    public void setPassportNumber(String passportNumber) { this.passportNumber = passportNumber; }

    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }

    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }

    public String getRequirementId() { return requirementId; }
    public void setRequirementId(String requirementId) { this.requirementId = requirementId; }

    public String getRequirementDesc() { return requirementDesc; }
    public void setRequirementDesc(String requirementDesc) { this.requirementDesc = requirementDesc; }

    public String getExpectedCtc() { return expectedCtc; }
    public void setExpectedCtc(String expectedCtc) { this.expectedCtc = expectedCtc; }

    public String getGrantedCtc() { return grantedCtc; }
    public void setGrantedCtc(String grantedCtc) { this.grantedCtc = grantedCtc; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getBudgetComment() { return budgetComment; }
    public void setBudgetComment(String budgetComment) { this.budgetComment = budgetComment; }

    public String getManagementComment() { return managementComment; }
    public void setManagementComment(String managementComment) { this.managementComment = managementComment; }

    public String getCtcType() { return ctcType; }
    public void setCtcType(String ctcType) { this.ctcType = ctcType; }

    public String getWorkLocation() { return workLocation; }
    public void setWorkLocation(String workLocation) { this.workLocation = workLocation; }

    public String getVisa() { return visa; }
    public void setVisa(String visa) { this.visa = visa; }

    public String getVisaCountry() { return visaCountry; }
    public void setVisaCountry(String visaCountry) { this.visaCountry = visaCountry; }

    public String getVisaExpiry() { return visaExpiry; }
    public void setVisaExpiry(String visaExpiry) { this.visaExpiry = visaExpiry; }

    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }

    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public LocalDate getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDate modifiedDate) { this.modifiedDate = modifiedDate; }

    public Boolean getIsSaved() { return isSaved; }
    public void setIsSaved(Boolean isSaved) { this.isSaved = isSaved; }

    public String getClientCode() { return clientCode; }
    public void setClientCode(String clientCode) { this.clientCode = clientCode; }
}
