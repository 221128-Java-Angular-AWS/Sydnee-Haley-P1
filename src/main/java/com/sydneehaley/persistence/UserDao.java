package com.sydneehaley.persistence;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.User;
public class UserDao {
    private Connection connection; // empty connection object

    public UserDao() {
        this.connection = ConnectionManager.getConnection();
    }

    public void createUser(User user) {
        try {
            String sql = "INSERT INTO users (access_token, email, first_name, last_name, organization, password, profile_image, privileges) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // pstmt.setObject(2, user.getAccountId());
            pstmt.setBoolean(1, user.getAccessToken());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setString(5, user.getOrganization());
            pstmt.setString(6, user.getPassword());
            pstmt.setString(7, user.getProfileImage());
            pstmt.setString(8, user.getPrivileges());
            System.out.println(pstmt);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User auth(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()) {
                throw new UserNotFoundException("This email was not found");
            }
            User user = new User((java.util.UUID) rs.getObject("id"), rs.getBoolean("access_token"),
                    rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("organization"), rs.getString("password"),
                    rs.getString("profile_image"),rs.getString("privileges"), rs.getBoolean("session_token"));

            if(user.getPassword().equals(password)) {
                return user;
            }
            throw new PasswordIncorrectException("That password is not correct");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void signInUser(User user)  {
        try{
            String sql = "UPDATE users " + "SET session_token = ? " + "WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setBoolean(1, true);
            pstmt.setString(2, user.getEmail());
            System.out.println(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }

    public Set<User> getAllUsers() {
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            Set<User> results = new HashSet<>();

            while(rs.next()) {
               User user = new User((java.util.UUID) rs.getObject("id"), rs.getBoolean("access_token"),
                       rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("organization"), rs.getString("password"),
                       rs.getString("profile_image"),rs.getString("privileges"), rs.getBoolean("session_token"));
               results.add(user);
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<User> getUser(User user) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            System.out.println(pstmt);
            ResultSet rs = pstmt.executeQuery();
            Set<User> result = new HashSet<>();

           while(rs.next()) {
                User currentUser = new User((java.util.UUID) rs.getObject("id"), rs.getBoolean("access_token"), rs.getString("email"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("organization"), rs.getString("password"), rs.getString("profile_image"), rs.getString("privileges"), rs.getBoolean("session_token"));
               result.add(user);
           }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
