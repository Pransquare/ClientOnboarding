package com.prannexus.clientonboarding.dto;

import java.time.LocalDateTime;

public class ClientDbMappingDto {

    private String clientCode;
    private String nexushireDb;
    private String nemsDb;
    private String loginDb;
    private String masterConfigDb;
    private LocalDateTime createdDate;

    public ClientDbMappingDto() { }

    public ClientDbMappingDto(String clientCode, String nexushireDb, String nemsDb, String loginDb, String masterConfigDb, LocalDateTime createdDate) {
        this.clientCode = clientCode;
        this.nexushireDb = nexushireDb;
        this.nemsDb = nemsDb;
        this.loginDb = loginDb;
        this.masterConfigDb = masterConfigDb;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public String getClientCode() { return clientCode; }
    public void setClientCode(String clientCode) { this.clientCode = clientCode; }

    public String getNexushireDb() { return nexushireDb; }
    public void setNexushireDb(String nexushireDb) { this.nexushireDb = nexushireDb; }

    public String getNemsDb() { return nemsDb; }
    public void setNemsDb(String nemsDb) { this.nemsDb = nemsDb; }

    public String getLoginDb() { return loginDb; }
    public void setLoginDb(String loginDb) { this.loginDb = loginDb; }

    public String getMasterConfigDb() { return masterConfigDb; }
    public void setMasterConfigDb(String masterConfigDb) { this.masterConfigDb = masterConfigDb; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
}
