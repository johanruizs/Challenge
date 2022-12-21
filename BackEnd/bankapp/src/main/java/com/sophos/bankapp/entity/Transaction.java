package com.sophos.bankapp.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNumber; // Vincular con el accounNumber de Account
    private LocalDate transactionDate;
    private String transactionType;
    private String description;
    private Number transactionValue;
    private String movementType;
    private Number balance; // De la clase account
    private Number availableBalance; // De la clase Account 


    public Transaction() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public LocalDate getTransactionDate() {
        return transactionDate;
    }


    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }


    public String getTransactionType() {
        return transactionType;
    }


    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Number getTransactionValue() {
        return transactionValue;
    }


    public void setTransactionValue(Number transactionValue) {
        this.transactionValue = transactionValue;
    }


    public String getMovementType() {
        return movementType;
    }


    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }


    public Number getBalance() {
        return balance;
    }


    public void setBalance(Number balance) {
        this.balance = balance;
    }


    public Number getAvailableBalance() {
        return availableBalance;
    }


    public void setAvailableBalance(Number availableBalance) {
        this.availableBalance = availableBalance;
    }

    
}
