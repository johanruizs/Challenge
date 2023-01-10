package com.sophos.bankapp.entity;

import java.time.LocalDate;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="clients")

public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeId;
    private String numberId;
    private String name;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private LocalDate creationDate;
    private String creationUser;
    private LocalDate updateDate;
    private String updateUser;
    
    
    @OneToMany(mappedBy = "accountHolder", orphanRemoval = true)
    private List<Account> accountsList;

    
    public Client() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTypeId() {
        return typeId;
    }


    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }


    public String getNumberId() {
        return numberId;
    }


    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    public String getCreationUser() {
        return creationUser;
    }


    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }


    public LocalDate getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }


    public String getUpdateUser() {
        return updateUser;
    }


    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    public List<Account> getAccounts() {
        return accountsList;
    }


    public void setAccounts(List<Account> accountList) {
        this.accountsList = accountList;
    }
  
}
