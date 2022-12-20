package com.sydneehaley.service;
import com.sydneehaley.model.Ticket;
import com.sydneehaley.persistence.UpdateTicketDao;

public class UpdateTicketService {

    private UpdateTicketDao dao;


    public UpdateTicketService(UpdateTicketDao dao) {
        this.dao = dao;
    }

     public void updateATicket(Ticket ticket) {
        dao.updateTicket(ticket.getId());
    }



}
