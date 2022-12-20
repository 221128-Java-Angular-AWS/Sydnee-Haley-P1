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


public class CurrentUserServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;

    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }
        User user = service.getCurrentUser(email);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        resp.setStatus(200);
        resp.getWriter().println(json);

//        ObjectMapper mapper = new ObjectMapper();
//        User user = mapper.readValue(jsonBuilder.toString(), User.class);
//        service.getCurrentUser(user.getEmail());
//        String json = mapper.writeValueAsString(user);
//        resp.setStatus(200);
//      resp.getWriter().println(json);


    }
    }
