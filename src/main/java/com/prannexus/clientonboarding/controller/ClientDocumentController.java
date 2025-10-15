package com.prannexus.clientonboarding.controller;

import com.prannexus.clientonboarding.dto.ClientDocumentDto;
import com.prannexus.clientonboarding.service.ClientDocumentService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/clients/documents")
public class ClientDocumentController {

    private final ClientDocumentService service;

    public ClientDocumentController(ClientDocumentService service) {
        this.service = service;
    }

    @PostMapping("/upload/{clientId}")
    public ResponseEntity<ClientDocumentDto> uploadDocument(
            @PathVariable Long clientId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("uploadedBy") String uploadedBy) throws IOException {
        return ResponseEntity.ok(service.uploadDocument(clientId, file, uploadedBy));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<ClientDocumentDto>> getDocumentsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(service.getDocumentsByClient(clientId));
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long documentId) throws IOException {
        byte[] data = service.downloadDocument(documentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document_" + documentId)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}
