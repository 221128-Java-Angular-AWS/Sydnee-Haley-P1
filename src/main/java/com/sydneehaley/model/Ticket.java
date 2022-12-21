package com.sydneehaley.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import org.eclipse.persistence.annotations.TypeConverter;

import java.sql.Date;
import java.util.UUID;

public class Ticket {
    @JsonProperty("id")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID id;
    @JsonProperty("user_id")
    @Id
    @TypeConverter(name = "uuidConverter", dataType = Object.class, objectType = UUID.class)
    private UUID userId;

    @JsonProperty("subject")
    private String subject;
    @JsonProperty("amount")
    private double amount;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("status")
    private String status;


    public Ticket() {
    }

    public Ticket(UUID id, UUID userId, String subject, double amount, String accountNumber, Date date, String notes, String status) {
        this.id = id;
        this.userId = userId;
        this.subject = subject;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.date = date;
        this.notes = notes;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
