package com.sydneehaley.service;

import com.sydneehaley.model.Session;
import com.sydneehaley.persistence.SessionDao;


public class SessionService {

    private SessionDao dao;

    public SessionService(SessionDao dao) {
        this.dao = dao;
    }

    public void newSession (Session session) {
       dao.createSession(session.getUserId());
    }
    public Session getSession(Session session) {
        return dao.getSession(session.getUserId());
    }


}

