package com.prannexus.clientonboarding.dto;

import java.time.LocalDate;

public class NemsEmployeeDto {

    private String firstName;
    private String lastName;
    private String emailId;
    private String clientCode;
    private String role;
    private String status;
    private String workflowStatus;
    private LocalDate dateOfJoining;
    private String workType;
    private String personalEmail;
    private String employeeCode; // Add this field

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmailId() { return emailId; }
    public void setEmailId(String emailId) { this.emailId = emailId; }

    public String getClientCode() { return clientCode; }
    public void setClientCode(String clientCode) { this.clientCode = clientCode; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getWorkflowStatus() { return workflowStatus; }
    public void setWorkflowStatus(String workflowStatus) { this.workflowStatus = workflowStatus; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    public String getWorkType() { return workType; }
    public void setWorkType(String workType) { this.workType = workType; }

    public String getPersonalEmail() { return personalEmail; }
    public void setPersonalEmail(String personalEmail) { this.personalEmail = personalEmail; }

    public String getEmployeeCode() { return employeeCode; } // Add getter
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; } // Add setter
}
