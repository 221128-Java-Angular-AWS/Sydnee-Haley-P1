package com.sydneehaley.persistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import com.sydneehaley.pojos.Account;
public class AccountDao {
    private Connection connection; // empty connection object

    public AccountDao() {
        this.connection = ConnectionManager.getConnection();
    } // initializing Connection object from ConnectionManager;


    public void createAccount(Account account) {
        try {
            String sqlAccount = "INSERT INTO account (access_token, email, password ) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sqlAccount, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(1, account.getAccessToken());
            pstmt.setString(2, account.getEmail());
            pstmt.setString(3, account.getPassword());

            pstmt.executeUpdate();
            ResultSet rsAccount = pstmt.getGeneratedKeys();


            // set user query here
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Set<Account> getAllAccounts() {
        try {
            String sql = "SELECT * FROM account";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<Account> results = new HashSet<>();
            while(rs.next()) {
                Account account = new Account(rs.getString("id"), rs.getBoolean("access_token"),
                        rs.getString("email"), rs.getString("password"));
                results.add(account);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
