package com.sophos.bankapp.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountType;
    private Number accountNumber;
    private String accountStatus;
    private Number Balance;
    private Number availableBalance;
    private Boolean TaxFree;
    private Date creationDate;
    private String creationUser;
    private Date updateDate;
    private String updateUser;
    
    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Number getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Number accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Number getBalance() {
        return Balance;
    }

    public void setBalance(Number balance) {
        Balance = balance;
    }

    public Number getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Number availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Boolean getTaxFree() {
        return TaxFree;
    }

    public void setTaxFree(Boolean taxFree) {
        TaxFree = taxFree;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUser() {
        return creationUser;
    }

    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    

   
}
