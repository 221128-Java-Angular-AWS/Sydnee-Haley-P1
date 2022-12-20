package com.sydneehaley.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class ApproveTicketDao {

    private Connection connection; // empty connection object

    public ApproveTicketDao() {
        this.connection = ConnectionManager.getConnection();
    }


    public void approveTicket(UUID id) {
        try {
            String sql = "update ticket set " +
                    "approval = ? " +
                    "where id = ? ";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setBoolean(1, true);
            pstmt.setObject(2, id);
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
