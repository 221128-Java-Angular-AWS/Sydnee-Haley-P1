package com.sydneehaley.service;
import com.sydneehaley.persistence.AdminDao;
import com.sydneehaley.model.Admin;

import java.util.Set;

public class AdminService {

    private AdminDao dao;


    public AdminService(AdminDao dao) {
        this.dao = dao;
    }

    /*  public void registerNewAdmin(Admin admin) {
        dao.createAdmin(admin);
    }

*/
    public Set<Admin> getAllAdmins() {
        return dao.getAllAdmins();
    }
}
