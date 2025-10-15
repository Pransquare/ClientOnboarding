package com.prannexus.clientonboarding.service.impl;

import com.prannexus.clientonboarding.dto.ClientDocumentDto;
import com.prannexus.clientonboarding.entity.ClientDocumentEntity;
import com.prannexus.clientonboarding.entity.ClientOnboardingEntity;
import com.prannexus.clientonboarding.mapper.ClientDocumentMapper;
import com.prannexus.clientonboarding.repository.ClientDocumentRepository;
import com.prannexus.clientonboarding.repository.ClientOnboardingRepository;
import com.prannexus.clientonboarding.service.ClientDocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientDocumentServiceImpl implements ClientDocumentService {

    private final ClientOnboardingRepository clientRepo;
    private final ClientDocumentRepository docRepo;

    @Value("${file.upload-dir:uploads/clients}")
    private String uploadDir;

    public ClientDocumentServiceImpl(ClientOnboardingRepository clientRepo,
                                     ClientDocumentRepository docRepo) {
        this.clientRepo = clientRepo;
        this.docRepo = docRepo;
    }

    @Override
    public ClientDocumentDto uploadDocument(Long clientId, MultipartFile file, String uploadedBy) throws IOException {
        ClientOnboardingEntity client = clientRepo.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path clientFolder = Paths.get(uploadDir, String.valueOf(clientId));
        Files.createDirectories(clientFolder);

        Path targetPath = clientFolder.resolve(fileName);
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        ClientDocumentEntity entity = new ClientDocumentEntity();
        entity.setClient(client);
        entity.setDocumentName(fileName);
        entity.setDocumentType(file.getContentType());
        entity.setDocumentPath(targetPath.toString());
        entity.setUploadedBy(uploadedBy);

        ClientDocumentEntity saved = docRepo.save(entity);
        return ClientDocumentMapper.toDto(saved);
    }

    @Override
    public List<ClientDocumentDto> getDocumentsByClient(Long clientId) {
        return docRepo.findByClient_ClientId(clientId)
                .stream()
                .map(ClientDocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public byte[] downloadDocument(Long documentId) throws IOException {
        ClientDocumentEntity doc = docRepo.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return Files.readAllBytes(Paths.get(doc.getDocumentPath()));
    }
}
