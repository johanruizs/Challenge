package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import com.sophos.bankapp.entity.Client;

public interface ClientService {

    public Client createClient(Client client);
    public List<Client> getAllClients();
    public Optional<Client> getClientById(int id);
    public boolean deleteClientById(int id);
    
}
