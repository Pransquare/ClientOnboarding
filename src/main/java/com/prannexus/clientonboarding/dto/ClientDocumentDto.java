package com.prannexus.clientonboarding.dto;

import java.time.LocalDateTime;

public class ClientDocumentDto {

    private Long documentId;
    private Long clientId;
    private String documentName;
    private String documentType;
    private String documentPath;
    private LocalDateTime uploadedDate;
    private String uploadedBy;

    public ClientDocumentDto() { }

    public ClientDocumentDto(Long documentId, Long clientId, String documentName, String documentType,
                             String documentPath, LocalDateTime uploadedDate, String uploadedBy) {
        this.documentId = documentId;
        this.clientId = clientId;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentPath = documentPath;
        this.uploadedDate = uploadedDate;
        this.uploadedBy = uploadedBy;
    }

    // Getters and Setters
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getDocumentName() { return documentName; }
    public void setDocumentName(String documentName) { this.documentName = documentName; }
    public String getDocumentType() { return documentType; }
    public void setDocumentType(String documentType) { this.documentType = documentType; }
    public String getDocumentPath() { return documentPath; }
    public void setDocumentPath(String documentPath) { this.documentPath = documentPath; }
    public LocalDateTime getUploadedDate() { return uploadedDate; }
    public void setUploadedDate(LocalDateTime uploadedDate) { this.uploadedDate = uploadedDate; }
    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }
}
