package com.sophos.bankapp.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="transactions")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account linkedAccount; 

    private String accountToTransfer;

    private LocalDate transactionDate;
    private String transactionType;
    private String description;
    private Number transactionValue;
    private String movementType;


    public Transaction() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
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


    public Account getLinkedAccount() {
        return linkedAccount;
    }


    public void setLinkedAccount(Account linkedAccount) {
        this.linkedAccount = linkedAccount;
    }

    public String getAccountToTransfer() {
        return accountToTransfer;
    }


    public void setAccountToTransfer(String accountToTransfer) {
        this.accountToTransfer = accountToTransfer;
    }



}
