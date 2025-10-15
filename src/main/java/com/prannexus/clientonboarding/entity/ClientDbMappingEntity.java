package com.prannexus.clientonboarding.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client_db_mapping")
public class ClientDbMappingEntity {

    @Id
    @Column(name = "client_code", nullable = false)
    private String clientCode;

    @Column(name = "nexushire_db", nullable = false)
    private String nexushireDb;

    @Column(name = "nems_db", nullable = false)
    private String nemsDb;

    @Column(name = "login_db")
    private String loginDb;

    @Column(name = "master_config_db")
    private String masterConfigDb;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

    public ClientDbMappingEntity() { }

    public ClientDbMappingEntity(String clientCode, String nexushireDb, String nemsDb, String loginDb, String masterConfigDb, LocalDateTime createdDate) {
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
