package com.sophos.bankapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.bankapp.entity.Account;
import com.sophos.bankapp.entity.Client;
import com.sophos.bankapp.repository.AccountRepository;
import com.sophos.bankapp.repository.ClientRepository;

import jakarta.persistence.Id;


@Service
public class AccountServiceImplementation implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;


    @Override
    public Account createAccount(int clientId, Account account) {

        Client client = clientRepository.findById(clientId).get();
        

        if (account.getAccountType().equalsIgnoreCase("Saving") || account.getAccountType().equalsIgnoreCase("Checking")) {

                AccountGenerator accountGenerator = new AccountGenerator();
                String newAccountNumber = accountGenerator.accountGenerator(account.getAccountType());
                Account accountExist = accountRepository.findByAccountNumber(newAccountNumber); // Do while !!


                if (accountExist == null){ 
                    account.setAccountNumber(newAccountNumber);
                    account.setCreationDate(LocalDate.now());
                    account.setCreationUser("admin");
                    account.setUpdateUser("admin");
                    account.setAccountHolder(client);
                    if (account.getAccountType().equalsIgnoreCase("Saving")){
                        account.setAccountStatus("Active");
                    }
                    
                    return accountRepository.save(account);
                }
        } 
        return null;
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

    @Override
    public Account activeInactiveAccount(int id, Account account){

        Account accountToUpdateStatus = accountRepository.findById(id).get();

        if(accountToUpdateStatus.getAccountStatus().equalsIgnoreCase("Active")){
            accountToUpdateStatus.setAccountStatus("Inactive");
            return accountRepository.save(accountToUpdateStatus);
        } else if (accountToUpdateStatus.getAccountStatus().equalsIgnoreCase("Inactive")) {
            accountToUpdateStatus.setAccountStatus("Active");
            return accountRepository.save(accountToUpdateStatus);
        } else if (accountToUpdateStatus.getAccountStatus() == null){
            accountToUpdateStatus.setAccountStatus("Active");
            return accountRepository.save(accountToUpdateStatus);
        } else {    
            return null;
        }

    }
    
}
