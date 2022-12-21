package com.sophos.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.sophos.bankapp.entity.Client;

@Repository

public interface ClientRepository extends JpaRepository <Client, Integer> {

    Client findByNumberId(String numberId);
            
}
