package com.sydneehaley.service;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.UserDao;

import java.util.Set;

public class UserService {

    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void registerNewUser(User user) {
        dao.createUser(user);
    }

    public User authenticateUser(User user) throws UserNotFoundException, PasswordIncorrectException {
        return dao.auth(user.getEmail(), user.getPassword());
    }

    public void getSession(User user) {
        dao.beginSession(user);
    }

    public void closeSession(User user) {
        dao.endSession(user);
    }

    public User getCurrentUser(String email) {
        return dao.getUser(email);
    }

    public Set<User> getAllUsers() {
        return dao.getAllUsers();
    }
}

