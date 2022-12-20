package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;
import com.sydneehaley.model.User;
import com.sydneehaley.persistence.UserDao;
import com.sydneehaley.service.UserService;

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

public class AuthServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = mapper.readValue(req.getInputStream(), User.class);
        UUID uuid = UUID.randomUUID();
        try {
            User authenticatedUser = service.authenticateUser(user);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));
            Cookie authCookie = new Cookie("session_token", uuid.toString());
            Cookie authCookieEmail = new Cookie("email", user.getEmail());


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
