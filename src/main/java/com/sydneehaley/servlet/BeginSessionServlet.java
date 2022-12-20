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

public class BeginSessionServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
    }



    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonBuilder.toString(), User.class);
        service.getSession(user);
        System.out.println(user);
        resp.setStatus(201);
    }
}
