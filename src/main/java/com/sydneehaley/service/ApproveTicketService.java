package com.sydneehaley.service;
import com.sydneehaley.model.Ticket;
import com.sydneehaley.persistence.ApproveTicketDao;

public class ApproveTicketService {

    private ApproveTicketDao dao;


    public ApproveTicketService(ApproveTicketDao dao) {
        this.dao = dao;
    }

    public void approveATicket(Ticket ticket) {
        dao.approveTicket(ticket.getId());
    }



}
