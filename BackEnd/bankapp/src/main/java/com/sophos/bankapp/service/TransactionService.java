package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import com.sophos.bankapp.entity.Transaction;

public interface TransactionService {

    public Transaction createTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions();
    public Optional<Transaction> getTransactionById(int id);
    public boolean deleteTransactionById(int id);
    
}
