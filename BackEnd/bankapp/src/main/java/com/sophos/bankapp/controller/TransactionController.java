package com.sophos.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.bankapp.entity.Transaction;
import com.sophos.bankapp.service.TransactionService;

@RestController
@RequestMapping("/transactions")

public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(){

        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction){

        return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") int id){
        
        return transactionService.getTransactionById(id)
                .map(transaction -> new ResponseEntity<>(transaction, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/consignment/{accountId}")
    public ResponseEntity<Transaction> makeConsignment(@PathVariable int accountId, @RequestBody Transaction consignment){

        if (transactionService.makeConsignment(accountId, consignment) != null){
            return new ResponseEntity<>(consignment, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/withdrawal/{accountId}")
    public ResponseEntity<Transaction> makeWithdraw(@PathVariable int accountId, @RequestBody Transaction withdrawal){

        if (transactionService.makeWithdrawal(accountId, withdrawal) != null){
            return new ResponseEntity<>(withdrawal, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/transfer/{accountId}")
    public ResponseEntity<Transaction> makeTransfer(@PathVariable int accountId,  @RequestBody Transaction transfer){ 

        if (transactionService.makeTransfer(accountId, transfer) != null){ 
            return new ResponseEntity<>(transfer, HttpStatus.CREATED);
        } else {
            System.out.println("Account to transfer does not exits");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }


    
}
