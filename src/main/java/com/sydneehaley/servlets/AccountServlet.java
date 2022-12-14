package com.sydneehaley.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sydneehaley.persistence.AccountDao;
import com.sydneehaley.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sydneehaley.pojos.Account;
import com.sydneehaley.service.AccountService;
import com.sydneehaley.persistence.AccountDao;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class AccountServlet extends HttpServlet {

    private AccountService service;

    public void init() throws ServletException {
        this.service = new AccountService(new AccountDao());
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Account> users = service.getAllAccounts();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(users);
        resp.setStatus(200);
        resp.getWriter().println(json);

    }
}
