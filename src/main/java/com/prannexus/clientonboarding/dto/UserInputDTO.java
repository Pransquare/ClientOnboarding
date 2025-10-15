package com.prannexus.clientonboarding.dto;

import java.util.List;

public class UserInputDTO {
    private String name;
    private String email;
    private String password;
    private String clientCode;
    private String employeeCode;  // Add this field
    private List<UserRolesDTO> roles;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getClientCode() { return clientCode; }
    public void setClientCode(String clientCode) { this.clientCode = clientCode; }

    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }  // Add setter

    public List<UserRolesDTO> getRoles() { return roles; }
    public void setRoles(List<UserRolesDTO> roles) { this.roles = roles; }
}
