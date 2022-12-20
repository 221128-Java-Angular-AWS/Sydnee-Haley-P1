package com.sydneehaley.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class DeleteTicketDao {

    private Connection connection; // empty connection object

    public DeleteTicketDao() {
        this.connection = ConnectionManager.getConnection();
    }


    public void deleteTicket(UUID id) {
        try {
            String sql = "DELETE FROM ticket WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, id);
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
