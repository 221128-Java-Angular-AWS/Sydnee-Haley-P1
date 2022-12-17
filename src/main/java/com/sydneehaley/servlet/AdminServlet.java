package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.persistence.AdminDao;
import com.sydneehaley.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sydneehaley.model.Admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class AdminServlet extends HttpServlet {

    private AdminService service;

    public void init() throws ServletException {
        this.service = new AdminService(new AdminDao());
    }



    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Admin> accounts = service.getAllAdmins();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(accounts);
        resp.setStatus(200);
        resp.getWriter().println(json);
    }

    /* protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        // System.out.println("JSON string: " + jsonBuilder.toString());
        ObjectMapper mapper = new ObjectMapper();
        // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Account account = mapper.readValue(jsonBuilder.toString(), Account.class);
        service.registerNewAccount(account);
        System.out.println(account);
        resp.setStatus(201);
    }

     */
}
