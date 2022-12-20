package com.sydneehaley.persistence;

import java.sql.*;
import java.util.UUID;

public class UpdateTicketDao {

    private Connection connection; // empty connection object

    public UpdateTicketDao() {
        this.connection = ConnectionManager.getConnection();
    }


    public void updateTicket(UUID id) {
        try {
            String sql = "update ticket set " +
                    "status = ? " +
                    "where id = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, "Approved");
            pstmt.setObject(2, id);
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    }




