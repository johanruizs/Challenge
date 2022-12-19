package com.sophos.bankapp.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;
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
        LocalDate birthDateConverted = ageCalculator.convertToLocalDateViaInstant(client.getBirthDate());
        LocalDate currentDateConverted = ageCalculator.convertToLocalDateViaInstant(new Date());
        int age = ageCalculator.calculateAge(birthDateConverted, currentDateConverted);
        if (age>=18){
            client.setCreationDate(new Date());
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

    @Override
    public boolean deleteClientById(int id) {
        return getClientById(id).map(client -> {
            clientRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

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
                clientToUpdate.get().setUpdateDate(new Date());
            });
            // AgeCalculator ageCalculator = new AgeCalculator();
            // LocalDate birthDateUpdated = ageCalculator.convertToLocalDateViaInstant(clientToUpdate.get().getBirthDate());
            // LocalDate currentDateConverted = ageCalculator.convertToLocalDateViaInstant(new Date());
            // int age = ageCalculator.calculateAge(birthDateUpdated, currentDateConverted);
            // if (age>=18){
            //     return clientRepository.save(clientToUpdate.get());
            // } 

            return clientRepository.save(clientToUpdate.get());
        }
        return null;

    }


}
