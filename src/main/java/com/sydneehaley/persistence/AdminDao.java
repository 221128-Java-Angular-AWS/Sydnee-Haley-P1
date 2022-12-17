package com.sydneehaley.persistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import com.sydneehaley.model.Admin;
public class AdminDao {
    private Connection connection; // empty connection object

    public AdminDao() {
        this.connection = ConnectionManager.getConnection();
    } // initializing Connection object from ConnectionManager;



    public Set<Admin> getAllAdmins() {
        try {
            String sql = "SELECT * FROM admin";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<Admin> results = new HashSet<>();

            while(rs.next()) {
                Admin admin = new Admin(rs.getString("id"), rs.getString("user_id"),
                        rs.getBoolean("invite_key"), rs.getBoolean("management"), rs.getBoolean("admin") );
                results.add(admin);
            }

            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
