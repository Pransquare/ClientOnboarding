package com.prannexus.clientonboarding.dto;

import java.time.LocalDateTime;

public class ClientOnboardingDto {

    private Long clientId;
    private String clientName;
    private String clientCode;
    private String email;
    private String contactNumber;
    private String domain;
    private String approvalStatus;
    private String approvedBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String remarks;

    public ClientOnboardingDto() { }

    public ClientOnboardingDto(Long clientId, String clientName, String clientCode, String email,
                               String contactNumber, String domain, String approvalStatus,
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
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public LocalDateTime getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate = modifiedDate; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
