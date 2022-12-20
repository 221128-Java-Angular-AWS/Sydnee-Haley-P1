package com.sydneehaley.service;


import com.sydneehaley.model.Ticket;
import com.sydneehaley.persistence.TicketDao;

import java.util.Set;

public class TicketService {

    private TicketDao dao;

    public TicketService(TicketDao dao) {
        this.dao = dao;
    }

    public void newTicket(Ticket ticket) {
        dao.createTicket(ticket);
    }

    public Set<Ticket> getTickets() {
        return dao.getAllTickets();
    }
}

