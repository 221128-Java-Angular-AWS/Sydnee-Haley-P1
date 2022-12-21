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

    public void updateATicket(Ticket ticket) {
        dao.updateTicket(ticket);
    }

    public void deleteATicket(Ticket ticket) {
        dao.deleteTicket(ticket.getId());
    }

    public Set<Ticket> getTickets() {
        return dao.getAllTickets();
    }

    public Set<Ticket> getTicketsByFilter(String status) {
        return dao.filterTickets(status);
    }

    public Set<Ticket> getTicketsByFilterId(Ticket ticket) {
        return dao.filterTicketsById(ticket.getUserId());
    }
}

