package com.prannexus.clientonboarding.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_onboarding")
public class ClientOnboardingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_code", nullable = false, unique = true)
    private String clientCode;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "domain")
    private String domain;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", columnDefinition = "ENUM('PENDING','APPROVED','REJECTED')")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "remarks")
    private String remarks;

    public enum ApprovalStatus { PENDING, APPROVED, REJECTED }

    public ClientOnboardingEntity() { }

    public ClientOnboardingEntity(Long clientId, String clientName, String clientCode, String email,
                                  String contactNumber, String domain, ApprovalStatus approvalStatus,
                                  String approvedBy, LocalDateTime createdDate, LocalDateTime modifiedDate,
                                  String remarks) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientCode = clientCode;
        this.email = email;
        this.contactNumber = contactNumber;
        this.domain = domain;
        this.approvalStatus = approvalStatus;
        this.approvedBy = approvedBy;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.remarks = remarks;
    }

    // Getters and Setters
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getClientCode() { return clientCode; }
    public void setClientCode(String clientCode) { this.clientCode = clientCode; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getDomain() { return domain; }
    public void setDomain(String domain) { this.domain = domain; }
    public ApprovalStatus getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(ApprovalStatus approvalStatus) { this.approvalStatus = approvalStatus; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public LocalDateTime getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate = modifiedDate; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
