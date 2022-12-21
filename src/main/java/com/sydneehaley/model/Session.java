package com.sydneehaley.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import org.eclipse.persistence.annotations.TypeConverter;

import java.sql.Date;
import java.util.UUID;

public class Session {
    @JsonProperty("id")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID id;
    @JsonProperty("user_id")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID userId;

    @JsonProperty("session_token")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID sessionToken;

    @JsonProperty("expires")
    private Date expires;


    public Session() {
    }

    public Session(UUID id, UUID userId, UUID sessionToken, Date expires) {
        this.id = id;
        this.userId = userId;
        this.sessionToken = sessionToken;
        this.expires = expires;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID accountId) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(UUID sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Date getDate() {
        return expires;
    }

    public void setDate(Date date) {
        this.expires = expires;
    }



}
