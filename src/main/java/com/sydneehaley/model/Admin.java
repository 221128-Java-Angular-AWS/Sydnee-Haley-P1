package com.sydneehaley.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import org.eclipse.persistence.annotations.TypeConverter;

import java.util.UUID;

public class Admin {
    @JsonProperty("id")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID id;
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("management")
    private boolean management;
    @JsonProperty("admin")
    private boolean admin;


    public Admin() {
    }

    public Admin(UUID id, String email, String password, boolean admin,  boolean management) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.management = management;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID accountId) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getManagement() {
        return management;
    }

    public void setManagement(boolean management) {
        this.management = management;
    }

}
