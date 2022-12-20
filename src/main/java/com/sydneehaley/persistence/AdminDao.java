package com.sydneehaley.persistence;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.Admin;
import com.sydneehaley.model.User;

import java.sql.*;

public class AdminDao {

    private Connection connection; // empty connection object

    public AdminDao() {
        this.connection = ConnectionManager.getConnection();
    }
    public Admin auth(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        try {
            String sql = "SELECT * FROM users FULL OUTER JOIN admin ON users.id=admin.user_id WHERE (email = ? AND password = ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()) {
                throw new UserNotFoundException("This email was not found");
            }

            Admin admin = new Admin((java.util.UUID) rs.getObject("id"),   rs.getString("email"),
                    rs.getString("password"), rs.getBoolean("management"), rs.getBoolean("admin"));

            if(admin.getPassword().equals(password)) {
                return admin;
            }
            throw new PasswordIncorrectException("That password is not correct");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Admin getAdmin(String email) {
        Admin admin = new Admin();
        try {
            String sql = "SELECT * FROM users FULL OUTER JOIN admin ON users.id=admin.user_id WHERE (email = ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return admin = new Admin((java.util.UUID) rs.getObject("id"),   rs.getString("email"),
                        rs.getString("password"), rs.getBoolean("management"), rs.getBoolean("admin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
