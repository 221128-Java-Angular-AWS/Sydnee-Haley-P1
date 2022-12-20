package com.sydneehaley.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.persistence.DeleteTicketDao;
import com.sydneehaley.service.DeleteTicketService;
import com.sydneehaley.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class DeleteTicketServlet extends HttpServlet {
    private DeleteTicketService service;

    public void init() throws ServletException {
        this.service = new DeleteTicketService(new DeleteTicketDao());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
