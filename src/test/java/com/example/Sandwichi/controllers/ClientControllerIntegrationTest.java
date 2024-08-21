package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Client;
import com.example.Sandwichi.repositories.ClientRepository;
import com.example.Sandwichi.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testGetAllClients() throws Exception {
        // Setup test data
        Client client = new Client("John", "Doe", "john.doe@example.com", Collections.emptyList());
        clientRepository.save(client);

        // Perform GET request
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("John"))
                .andExpect(jsonPath("$[0].prenom").value("Doe"));
    }

    @Test
    public void testGetClientById() throws Exception {
        // Setup test data
        Client client = new Client("Jane", "Doe", "jane.doe@example.com", Collections.emptyList());
        client = clientRepository.save(client);

        // Perform GET request
        mockMvc.perform(get("/clients/{id}", client.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Jane"))
                .andExpect(jsonPath("$.prenom").value("Doe"));
    }

    @Test
    public void testCreateClient() throws Exception {
        // Create a client object
        String clientJson = "{\"nom\":\"Michael\",\"prenom\":\"Smith\",\"email\":\"michael.smith@example.com\",\"commandes\":[]}";

        // Perform POST request
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Michael"))
                .andExpect(jsonPath("$.prenom").value("Smith"));
    }

    @Test
    public void testDeleteClient() throws Exception {
        // Setup test data
        Client client = new Client("Mike", "Johnson", "mike.johnson@example.com", Collections.emptyList());
        client = clientRepository.save(client);

        // Perform DELETE request
        mockMvc.perform(delete("/clients/{id}", client.getId()))
                .andExpect(status().isNoContent());

        // Verify the client is deleted
        mockMvc.perform(get("/clients/{id}", client.getId()))
                .andExpect(status().isNotFound());
    }
}
