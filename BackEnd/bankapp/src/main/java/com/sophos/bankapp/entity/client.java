package com.sophos.bankapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="clients")

public class client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeId;
    private Long numberId;
    private String name;
    private String lastName;
    private String email;
    private String birthDate;
    private String creationDate;
    private String creationUser;
    private String updateDate;
    private String updateUser;

    
    public client() {
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


    public Long getNumberId() {
        return numberId;
    }


    public void setNumberId(Long numberId) {
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


    public String getBirthDate() {
        return birthDate;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public String getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    public String getCreationUser() {
        return creationUser;
    }


    public void setCreationUser(String creationUser) {
        this.creationUser = creationUser;
    }


    public String getUpdateDate() {
        return updateDate;
    }


    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }


    public String getUpdateUser() {
        return updateUser;
    }


    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
}
