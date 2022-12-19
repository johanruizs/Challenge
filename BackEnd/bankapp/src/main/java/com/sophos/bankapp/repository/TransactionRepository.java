package com.sophos.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophos.bankapp.entity.Transaction;

@Repository

public interface TransactionRepository  extends JpaRepository <Transaction, Integer>{
    
}
