package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.bankapp.entity.Account;
import com.sophos.bankapp.entity.Client;
import com.sophos.bankapp.repository.AccountRepository;
import com.sophos.bankapp.repository.ClientRepository;


@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;


    // @Override
    // public Account createAccount(String numberId, Account account) {
    //     Client client = clientRepository.findByNumberId(numberId);
    //     if (client != null){
    //         account.setClient(client);
    //         AccountGenerator accountGenerator = new AccountGenerator();
    //         String newAccountNumber = accountGenerator.accountGenerator(account.getAccountType());
    //         account.setAccountNumber(newAccountNumber);
    //         return accountRepository.save(account);
    //     }
    //     return null;  
    // }

    @Override
    public Account createAccount(Account account) {
        AccountGenerator accountGenerator = new AccountGenerator();
        String newAccountNumber = accountGenerator.accountGenerator(account.getAccountType());
        account.setAccountNumber(newAccountNumber);
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
