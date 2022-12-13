package com.sydneehaley.service;
import com.sydneehaley.persistence.AccountDao;
import com.sydneehaley.pojos.Account;

import java.util.Set;

public class AccountService {

    private AccountDao dao;


    public AccountService(AccountDao dao) {
        this.dao = dao;
    }

    public void registerNewUser(Account account) {
        //we can add in this layer other business logic
        //validation - user input
        //logging
        dao.createAccount(account);
    }


    public Set<Account> getAllAccounts() {
        return dao.getAllAccounts();
    }
}
