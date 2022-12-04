package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sophos.bankapp.entity.client;
import com.sophos.bankapp.repository.ClientRepository;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public client createClient(client client) {
        
        return clientRepository.save(client);
    }

    @Override
    public List<client> getAllClients() {
        
        return clientRepository.findAll();
    }

    @Override
    public Optional<client> getClientById(int id) {
        
        return clientRepository.findById(id);
    }

    @Override
    public boolean deleteClientById(int id) {
    
        return getClientById(id).map(client -> {
            clientRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    
    
}
