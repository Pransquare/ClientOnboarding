package com.prannexus.clientonboarding.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_onboarding_audit")
public class ClientOnboardingAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "action_by")
    private String actionBy;

    @Column(name = "action_ts")
    private LocalDateTime actionTs = LocalDateTime.now();

    @Column(name = "remarks")
    private String remarks;

    public ClientOnboardingAuditEntity() { }

    public ClientOnboardingAuditEntity(Long auditId, Long clientId, String actionType, String actionBy,
                                       LocalDateTime actionTs, String remarks) {
        this.auditId = auditId;
        this.clientId = clientId;
        this.actionType = actionType;
        this.actionBy = actionBy;
        this.actionTs = actionTs;
        this.remarks = remarks;
    }

    // Getters and Setters
    public Long getAuditId() { return auditId; }
    public void setAuditId(Long auditId) { this.auditId = auditId; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getActionBy() { return actionBy; }
    public void setActionBy(String actionBy) { this.actionBy = actionBy; }
    public LocalDateTime getActionTs() { return actionTs; }
    public void setActionTs(LocalDateTime actionTs) { this.actionTs = actionTs; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
