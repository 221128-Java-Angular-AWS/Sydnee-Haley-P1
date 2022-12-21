package com.sydneehaley.persistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.sydneehaley.model.Ticket;
public class TicketDao {
    private Connection connection;

    public TicketDao() {
        this.connection = ConnectionManager.getConnection();
    }

    public void createTicket(Ticket ticket) {
        try {
            String sql = "INSERT INTO ticket (user_id, subject, amount, account_number, notes) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, ticket.getUserId());
            pstmt.setString(2, ticket.getSubject());
            pstmt.setDouble(3, ticket.getAmount());
            pstmt.setString(4, ticket.getAccountNumber());
            pstmt.setString(5, ticket.getNotes());
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateTicket(Ticket ticket) {
        try {
            String sql = "UPDATE ticket SET status = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, ticket.getStatus());
            pstmt.setObject(2, ticket.getId());
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public Set<Ticket> getAllTickets() {
        try {
            String sql = "SELECT * FROM ticket";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = pstmt.executeQuery();
            Set<Ticket> results = new HashSet<>();

            while(rs.next()) {
                Ticket ticket = new Ticket((java.util.UUID) rs.getObject("id"), (java.util.UUID) rs.getObject("user_id"), rs.getString("subject"), rs.getDouble("amount"),
                        rs.getString("account_number"), rs.getDate("date"), rs.getString("notes"), rs.getString("status"));
                results.add(ticket);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Ticket> filterTickets(String status) {
        try {
            String sql = "SELECT * FROM ticket WHERE status=?";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            Set<Ticket> results = new HashSet<>();

            while(rs.next()) {
                Ticket ticket = new Ticket((java.util.UUID) rs.getObject("id"), (java.util.UUID) rs.getObject("user_id"), rs.getString("subject"), rs.getDouble("amount"),
                        rs.getString("account_number"), rs.getDate("date"), rs.getString("notes"), rs.getString("status"));
                results.add(ticket);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
