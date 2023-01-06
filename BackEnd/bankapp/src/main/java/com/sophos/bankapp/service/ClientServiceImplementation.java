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

            clientToUpdate.toString();
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

    @Override
    public Client editClient(int id, Client client){

        Client existingClient = clientRepository.findById(id).get();

        existingClient.setId(id);
		
		if (client.getTypeId() != null) {
			existingClient.setTypeId(client.getTypeId());

		} 
		
		if (client.getNumberId() != null) {
			existingClient.setNumberId(client.getNumberId());

		} 
		
		if (client.getName() != null) {
			existingClient.setName(client.getName());

		} 
	
		if (client.getLastName() != null) {
			existingClient.setLastName(client.getLastName());

		}
		
		if (client.getEmail() != null) {
			existingClient.setEmail(client.getEmail());
			
		}	
			
		if (client.getBirthDate() != null) {
			existingClient.setBirthDate(client.getBirthDate());
			
		}	
				
		existingClient.setUpdateDate(LocalDate.now());

        AgeCalculator ageCalculator = new AgeCalculator();
        int age = ageCalculator.calculateAge(existingClient.getBirthDate(), LocalDate.now());
        if (age>=18) {
            return clientRepository.save(existingClient);
        } 
        return null;
		
    }

}
