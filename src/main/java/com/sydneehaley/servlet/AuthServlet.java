package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.UserDao;
import com.sydneehaley.service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    private UsersService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UsersService(new UserDao());
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = mapper.readValue(req.getInputStream(), User.class);

        try {
            User authenticatedUser = service.authenticateUser(user);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));

        } catch(UserNotFoundException e) {
            resp.getWriter().print("Email address not found.");
            resp.setStatus(401);
            resp.setStatus(401, "Your email address in incorrect.");
        } catch(PasswordIncorrectException e) {
            resp.getWriter().print("Incorrect password");
            resp.setStatus(401);
            resp.setStatus(401, "Your password is incorrect.");
        }
    }
}
