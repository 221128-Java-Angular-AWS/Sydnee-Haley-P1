package com.sydneehaley.service;
import com.sydneehaley.exceptions.PasswordIncorrectException;
import com.sydneehaley.exceptions.UserNotFoundException;

import java.util.Set;

public class AccountService {

    private AccountDao dao;


    public AccountService(AccountDao dao) {
        this.dao = dao;
    }

    public void registerNewAccount(Account account) {
        dao.createAccount(account);
    }


    public Account authenticateUser(Account account) throws UserNotFoundException, PasswordIncorrectException  {
        return dao.auth(account.getEmail(), account.getPassword());
    }

    public Account authenticateUser(String email, String password) throws UserNotFoundException, PasswordIncorrectException {
        return dao.auth(email, password);
    }


    public Set<Account> getAllAccounts() {
        return dao.getAllAccounts();
    }
}
