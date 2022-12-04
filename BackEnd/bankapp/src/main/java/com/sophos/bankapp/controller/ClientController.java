package com.sophos.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.bankapp.entity.client;
import com.sophos.bankapp.service.ClientService;

@RestController
@RequestMapping("/clients")

public class ClientController {

    @Autowired
    ClientService clientService;


    @GetMapping
    public ResponseEntity<List<client>> getClients(){
        
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    
}
