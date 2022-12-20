package com.sydneehaley.service;
import com.sydneehaley.model.Ticket;
import com.sydneehaley.persistence.DeleteTicketDao;


public class DeleteTicketService {

    private DeleteTicketDao dao;


    public DeleteTicketService(DeleteTicketDao dao) {
        this.dao = dao;
    }

    public void deleteATicket(Ticket ticket) {
        dao.deleteTicket(ticket.getId());
    }


}
