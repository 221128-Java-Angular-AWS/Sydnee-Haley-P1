package com.sydneehaley.pojos;

import java.util.Objects;

public class Account {
    private String account_id;
    private String email;
    private String password;
    private Boolean access_token;


    public Account() {
    }

    public Account(String account_id, Boolean access_token, String email, String password) {
        this.account_id = account_id;
        this.access_token = access_token;
        this.email = email;
        this.password = password;
    }


    public String getAccountId() {
        return account_id;
    }

    public void setAccountId(String account_id) {
        this.account_id = account_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String username) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAccessToken() {
        return access_token;
    }

    public void setAccessToken(Boolean access_token) {
        this.access_token = access_token;
    }

  /*  @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(email, account.email) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "accountId=" + accountId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    */

}
