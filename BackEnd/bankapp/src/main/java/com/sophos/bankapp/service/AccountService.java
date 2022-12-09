package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import com.sophos.bankapp.entity.Account;

public interface AccountService {

    public Account createAccount(Account account);
    public List<Account> getAllAccounts();
    public Optional<Account> getAccountById(int id);
    public boolean deleteAccountById(int id);
    
}
