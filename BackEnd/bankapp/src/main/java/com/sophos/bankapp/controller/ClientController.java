package com.sophos.bankapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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


    @GetMapping("/all")
    public ResponseEntity<List<Client>> getClients(){
        
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Client> createClient(@RequestBody Client client){

        if (clientService.createClient(client) != null){
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") int id){
        
        return clientService.getClientById(id)
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PatchMapping("/update/{id}")
    public ResponseEntity<Client> modifyClientInfo(@PathVariable("id") int id, @RequestBody Map<String, Object> fields) {

        Client clientUpdated = clientService.updateClientInfoByFields(id, fields);
        
        if (clientUpdated != null){
            return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Client> editClient(@PathVariable("id") int id, @RequestBody Client client){
		
        if (clientService.editClient(id, client) != null){
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);	
        }    
	}


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable("id") int id){
        if (clientService.deleteClientById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }


}

