package com.sydneehaley.persistence;

import java.sql.*;
import java.util.UUID;


import com.sydneehaley.model.Session;

public class SessionDao {
    private Connection connection;

    public SessionDao() {
        this.connection = ConnectionManager.getConnection();
    }


    public void createSession(UUID userId) {
        try {
            String sql = "INSERT INTO session (user_id) VALUES (?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, userId);
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Session getSession(UUID userId) {
        Session session = new Session();
        try {
            String sql = "SELECT * FROM session WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setObject(1, userId);
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return session = new Session((java.util.UUID) rs.getObject("id"), (java.util.UUID) rs.getObject("user_id"), (java.util.UUID) rs.getObject("session_token"), rs.getDate("expires"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
