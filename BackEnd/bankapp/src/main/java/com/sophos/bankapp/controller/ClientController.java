package com.sophos.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sophos.bankapp.entity.Client;
import com.sophos.bankapp.service.ClientService;

@RestController
@RequestMapping("/clients")


public class ClientController {

    @Autowired
    ClientService clientService;


    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){

        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.OK);

    }

    
}
