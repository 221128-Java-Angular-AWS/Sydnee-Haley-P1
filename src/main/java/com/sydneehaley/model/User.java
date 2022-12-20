package com.sydneehaley.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.Id;
import org.eclipse.persistence.annotations.TypeConverter;

import java.util.UUID;

public class User {
    @JsonProperty("id")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID id;

    @JsonProperty("access_token")
    private boolean accessToken;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("organization")
    private String organization;

    @JsonProperty("password")
    private String password;

    @JsonProperty("privileges")
    private String privileges;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("session_token")
    private boolean sessionToken;



    public User() {
    }

    public User(UUID id, boolean accessToken, String email, String firstName, String lastName, String organization, String password, String privileges,  String profileImage, boolean sessionToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.organization = organization;
        this.password = password;
        this.privileges = privileges;
        this.profileImage = profileImage;
        this.sessionToken = sessionToken;
    }

//    public User(UUID id, boolean accessToken, String email, String firstName, String lastName, String organization, String privileges,  String profileImage, boolean sessionToken) {
//
//        this.accessToken = accessToken;
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.organization = organization;
//
//        this.privileges = privileges;
//        this.profileImage = profileImage;
//        this.sessionToken = sessionToken;
//    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public boolean getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(boolean accessToken) {
        this.sessionToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrganization() {
        return organization;
    }
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPrivileges() {
        return privileges;
    }
    public void setPrivileges(String privileges) {
        this.privileges = privileges;
    }

    public boolean getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(boolean sessionToken) {
        this.sessionToken = sessionToken;
    }

}
