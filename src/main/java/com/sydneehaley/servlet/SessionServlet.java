package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sydneehaley.model.Session;
import com.sydneehaley.persistence.SessionDao;
import com.sydneehaley.service.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class SessionServlet extends HttpServlet {
    private SessionService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new SessionService(new SessionDao());
        this.mapper = new ObjectMapper();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        ObjectMapper mapper = new ObjectMapper();
        Session session = mapper.readValue(jsonBuilder.toString(), Session.class);
        service.newSession(session);
        System.out.println(session);
        resp.setStatus(200);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = mapper.readValue(req.getInputStream(), Session.class);

        Session createSession = service.getSession(session);
        resp.setStatus(200);
        resp.getWriter().println(mapper.writeValueAsString(createSession));
        Cookie authCookie = new Cookie("ers_session_token", session.getUserId().toString());
        resp.addCookie(authCookie);
    }

}
