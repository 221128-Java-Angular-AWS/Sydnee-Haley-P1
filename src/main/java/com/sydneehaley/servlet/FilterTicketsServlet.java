package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.persistence.TicketDao;
import com.sydneehaley.service.TicketService;
import com.sydneehaley.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class FilterTicketsServlet extends HttpServlet {
    private TicketService service;
    private ObjectMapper mapper;

    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.mapper = new ObjectMapper();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");

       Set<Ticket> filteredTickets = service.getTicketsByFilter(status);
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().println(mapper.writeValueAsString(filteredTickets));
        resp.setStatus(200);
    }
}