package com.sophos.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.bankapp.entity.Account;
import com.sophos.bankapp.service.AccountService;;

@RestController
@RequestMapping("/accounts")

public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(){
        
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    // @PostMapping
    // public ResponseEntity<Account> createAccount(@PathVariable("numberId")String numberId, @RequestBody Account account){

    //     if(accountService.createAccount(numberId, account) != null){
    //         return new ResponseEntity<>(HttpStatus.CREATED);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    //     }
    //     // return new ResponseEntity<>(accountService.createAccount(numberId, account), HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        if (accountService.createAccount(account) != null){
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") int id){
        
        return accountService.getAccountById(id)
                .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAccountById(@PathVariable("id") int id){
        if (accountService.deleteAccountById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
