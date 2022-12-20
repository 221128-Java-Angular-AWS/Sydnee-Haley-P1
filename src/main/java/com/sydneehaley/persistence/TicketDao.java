package com.sydneehaley.persistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.Ticket;
public class TicketDao {
    private Connection connection; // empty connection object

    public TicketDao() {
        this.connection = ConnectionManager.getConnection();
    }

    public void createTicket(Ticket ticket) {
        try {
            String sql = "INSERT INTO ticket (user_id, subject, amount, account_number, notes, approval) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, ticket.getUserId());
            pstmt.setString(2, ticket.getSubject());
            pstmt.setDouble(3, ticket.getAmount());
            pstmt.setString(4, ticket.getAccountNumber());
            pstmt.setString(5, ticket.getNotes());
            pstmt.setBoolean(6, ticket.getApproval());
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public Set<Ticket> getAllTickets() {
        try {
            String sql = "SELECT * FROM ticket";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<Ticket> results = new HashSet<>();

            while(rs.next()) {
                Ticket ticket = new Ticket((java.util.UUID) rs.getObject("id"), (java.util.UUID) rs.getObject("user_id"), rs.getString("subject"), rs.getDouble("amount"),
                        rs.getString("account_number"), rs.getDate("date"), rs.getString("notes"), rs.getString("status"), rs.getBoolean("approval"));
                results.add(ticket);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
