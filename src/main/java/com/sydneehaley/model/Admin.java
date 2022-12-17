package com.sydneehaley.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin {
    @JsonProperty("id")
    private String id;
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("inviteKey")
    private boolean inviteKey;
    @JsonProperty("management")
    private boolean management;
    @JsonProperty("admin")
    private boolean admin;


    public Admin() {
    }

    public Admin(String userId, boolean admin, boolean inviteKey, boolean management) {
        this.userId = userId;
        this.admin = admin;
        this.inviteKey = inviteKey;
        this.management = management;
    }

    public Admin(String id, String userId, boolean admin, boolean inviteKey, boolean management) {
        this.id = id;
        this.userId = userId;
        this.admin = admin;
        this.inviteKey = inviteKey;
        this.management = management;
    }

    public String getId() {
        return id;
    }

    public void setId(String accountId) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.id = userId;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getInviteKey() {
        return inviteKey;
    }

    public void setInviteKey(boolean inviteKey) {
        this.inviteKey = inviteKey;
    }

    public boolean getManagement() {
        return management;
    }

    public void setManagement(boolean management) {
        this.management = management;
    }

}
