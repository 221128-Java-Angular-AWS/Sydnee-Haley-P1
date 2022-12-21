package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.persistence.TicketDao;
import com.sydneehaley.service.TicketService;
import com.sydneehaley.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class TicketServlet extends HttpServlet {
    private TicketService service;

    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Ticket> tickets = service.getTickets();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(tickets);
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
        Ticket ticket = mapper.readValue(jsonBuilder.toString(), Ticket.class);
        service.newTicket(ticket);
        System.out.println(ticket);
        resp.setStatus(201);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(jsonBuilder.toString(), Ticket.class);
        service.updateATicket(ticket);
        System.out.println(ticket);
        resp.setStatus(200);
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }

        ObjectMapper mapper = new ObjectMapper();
        Ticket ticket = mapper.readValue(jsonBuilder.toString(), Ticket.class);
        service.deleteATicket(ticket);
        System.out.println(ticket);
        resp.setStatus(200);
    }
}
