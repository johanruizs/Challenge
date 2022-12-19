package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.bankapp.entity.Transaction;
import com.sophos.bankapp.repository.TransactionRepository;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired 
    TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(int id) {
        return transactionRepository.findById(null);
    }

    @Override
    public boolean deleteTransactionById(int id) {
        return getTransactionById(id).map(transaction -> {
            transactionRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    
}
