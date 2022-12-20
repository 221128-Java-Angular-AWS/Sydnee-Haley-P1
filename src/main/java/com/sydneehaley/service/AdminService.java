package com.sydneehaley.service;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.Admin;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.AdminDao;

import java.util.Set;

public class AdminService {

    private AdminDao dao;

    public AdminService(AdminDao dao) {
        this.dao = dao;
    }


    public Admin authenticateAdmin(Admin admin) throws UserNotFoundException, PasswordIncorrectException {
        return dao.auth(admin.getEmail(), admin.getPassword());
    }

    public Admin getCurrentAdmin(String email) {
        return dao.getAdmin(email);
    }

}

