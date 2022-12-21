package com.sydneehaley.service;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.UserDao;

import java.util.Set;

public class UsersService {

    private UserDao dao;

    public UsersService(UserDao dao) {
        this.dao = dao;
    }

    public void createNewUser(User user) {
        dao.createUser(user);
    }

    public User authenticateUser(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        return dao.auth(email, password);
    }

    public Set<User> getAllUsers() {
        return dao.getAllUsers();
    }
}

