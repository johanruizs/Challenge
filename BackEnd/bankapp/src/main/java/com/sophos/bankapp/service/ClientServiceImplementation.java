package com.sophos.bankapp.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.sophos.bankapp.entity.Client;
import com.sophos.bankapp.repository.ClientRepository;

@Service
public class ClientServiceImplementation implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {

        AgeCalculator ageCalculator = new AgeCalculator();
        int age = ageCalculator.calculateAge(client.getBirthDate(), LocalDate.now());
        Client clientExist = clientRepository.findByNumberId(client.getNumberId());
        if (age>=18 && clientExist == null) {
            client.setCreationDate(LocalDate.now());
            client.setCreationUser("admin");
            client.setUpdateUser("admin");
            return clientRepository.save(client);
        } 
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }                                                           

    // @Override
    //  public Optional<Client> getClientByNumberId(String numberId) {
    //      return clientRepository.getByNumberId(numberId);
    // }

    @Override                                                      
    public boolean deleteClientById(int id) {
        return getClientById(id).map(client -> {
            clientRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    // @Override
    // public boolean deleteClientBynumberId(String numberId) {
    //     Optional <Client> clientExist = clientRepository.getByNumberId(numberId);
    //     if(clientExist != null) {
    //         clientRepository.deleteById(clientExist.get().getId());
    //         return true;
    //     } else {
    //         return false;
    //     }
    //     // return getClientByNumberId(numberId).map(client -> {
    //     //     clientRepository.deleteById(client.getId());
    //     //     return true;
    //     // }).orElse(false);
    // }


    // // Delete by numberId
    // @Override
    // public boolean deleteClientByNumberId(String numberId) {
    //     Client clientToDelete = clientRepository.findByNumberId(numberId);
    //     return getClientById(clientToDelete.getId()).map(client -> {
    //         clientRepository.deleteById(clientToDelete.getId());
    //         return true;
    //     }).orElse(false);
    // }


    @Override
    public Client saveClientInfo(Client client) {
        return clientRepository.save(client);
    }


    @Override
    public Client updateClientInfoByFields(int id, Map<String, Object> fields){

        Optional<Client> clientToUpdate = clientRepository.findById(id);

        if (clientToUpdate.isPresent()){

            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Client.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, clientToUpdate.get(), value);
                clientToUpdate.get().setUpdateDate(LocalDate.now());
            });
            
            return clientRepository.save(clientToUpdate.get());
        }
        return null;

    }

}
