package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.bankapp.entity.Account;
import com.sophos.bankapp.repository.AccountRepository;

@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        AccountGenerator accountGenerator = new AccountGenerator();
        int cuentaBancaria = accountGenerator.accountGenerator(account.getAccountType());
        account.setAccountNumber(cuentaBancaria);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean deleteAccountById(int id) {
        return getAccountById(id).map(account -> {
            accountRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
    
}
