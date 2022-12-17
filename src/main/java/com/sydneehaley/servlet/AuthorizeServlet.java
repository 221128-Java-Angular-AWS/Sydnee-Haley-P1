package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.persistence.UserDao;
import com.sydneehaley.service.UserService;
import com.sydneehaley.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class AuthorizeServlet extends HttpServlet {
    private UserService service;

    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonBuilder.toString(), User.class);
        Set<User> currentUser = service.getCurrentUser(user);
        String json = mapper.writeValueAsString(currentUser);
        resp.setStatus(200);
        resp.getWriter().println(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonBuilder.toString(), User.class);
        service.signIn(user);
        System.out.println(user);
        resp.setStatus(201);
    }
}
