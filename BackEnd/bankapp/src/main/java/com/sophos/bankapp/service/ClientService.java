package com.sophos.bankapp.service;

import java.util.List;
import java.util.Optional;

import com.sophos.bankapp.entity.client;

public interface ClientService {

    public client createClient(client client);
    public List<client> getAllClients();
    public Optional<client> getClientById(int id);
    public boolean deleteClientById(int id);
    
}
