package com.prannexus.clientonboarding.service;

import com.prannexus.clientonboarding.dto.ClientDocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ClientDocumentService {
    ClientDocumentDto uploadDocument(Long clientId, MultipartFile file, String uploadedBy) throws IOException;
    List<ClientDocumentDto> getDocumentsByClient(Long clientId);
    byte[] downloadDocument(Long documentId) throws IOException;
}
