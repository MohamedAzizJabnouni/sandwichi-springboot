package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Client;
import com.example.Sandwichi.repositories.ClientRepository;
import com.example.Sandwichi.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(ClientController.class)
public class ClientControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

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
                        .contentType("application/json")
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
