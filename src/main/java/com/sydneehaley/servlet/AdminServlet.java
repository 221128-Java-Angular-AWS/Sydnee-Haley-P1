package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.Admin;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.AdminDao;
import com.sydneehaley.service.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import static java.lang.System.out;

public class AdminServlet extends HttpServlet {
    private AdminService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new AdminService(new AdminDao());
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }
        Admin admin = service.getCurrentAdmin(email);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(admin);
        resp.setStatus(200);
        resp.getWriter().println(json);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Admin admin = mapper.readValue(req.getInputStream(), Admin.class);
        UUID uuid = UUID.randomUUID();
        try {
            Admin authenticatedUser = service.authenticateAdmin(admin);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));
            Cookie authCookie = new Cookie("admin_session_token", uuid.toString());
            Cookie authCookieEmail = new Cookie("admin_email", admin.getEmail());


            resp.addCookie(authCookie);
            resp.addCookie(authCookieEmail);


        } catch(UserNotFoundException e) {
            resp.getWriter().print("Username not recognized");
            resp.setStatus(401);
            resp.setStatus(401, "Wrong email");
        } catch(PasswordIncorrectException e) {
            resp.getWriter().print("Wrong password");
            resp.setStatus(401);
            resp.setStatus(401, "Wrong password");
        }
    }
}
