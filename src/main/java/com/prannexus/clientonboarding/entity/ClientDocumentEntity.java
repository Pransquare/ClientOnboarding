package com.prannexus.clientonboarding.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_documents")
public class ClientDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientOnboardingEntity client;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_path", nullable = false)
    private String documentPath;

    @Column(name = "uploaded_date")
    private LocalDateTime uploadedDate = LocalDateTime.now();

    @Column(name = "uploaded_by")
    private String uploadedBy;

    public ClientDocumentEntity() { }

    public ClientDocumentEntity(Long documentId, ClientOnboardingEntity client, String documentName,
                                String documentType, String documentPath, LocalDateTime uploadedDate,
                                String uploadedBy) {
        this.documentId = documentId;
        this.client = client;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentPath = documentPath;
        this.uploadedDate = uploadedDate;
        this.uploadedBy = uploadedBy;
    }

    // Getters and Setters
    public Long getDocumentId() { return documentId; }
    public void setDocumentId(Long documentId) { this.documentId = documentId; }
    public ClientOnboardingEntity getClient() { return client; }
    public void setClient(ClientOnboardingEntity client) { this.client = client; }
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
