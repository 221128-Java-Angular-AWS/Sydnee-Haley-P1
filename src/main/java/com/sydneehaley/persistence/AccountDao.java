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
            String sqlAccount = "INSERT INTO Account (account_id, accessLevel, email, first_name, last_name, password ) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sqlAccount, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, account.getAccessLevel());
            pstmt.setString(2, account.getEmail());
            pstmt.setString(4, account.getFirstName());
            pstmt.setString(4, account.getLastName());
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
            String sql = "SELECT * FROM Accounts";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<Account> results = new HashSet<>();
            while(rs.next()) {
                Account account = new Account(rs.getString("access_level"), rs.getString("account_id"),
                        rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("password"));
                results.add(account);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
