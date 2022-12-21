package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.model.Admin;
import com.sydneehaley.persistence.TicketDao;
import com.sydneehaley.service.TicketService;
import com.sydneehaley.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class UserTicketsServlet extends HttpServlet {
    private TicketService service;
    private ObjectMapper mapper;

    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);

        Set<Ticket> userTickets = service.getTicketsByFilterId(ticket);
        resp.setStatus(200);
        resp.getWriter().println(mapper.writeValueAsString(userTickets));
    }
}