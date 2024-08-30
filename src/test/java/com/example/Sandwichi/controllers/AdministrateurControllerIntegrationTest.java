package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Administrateur;
import com.example.Sandwichi.repositories.AdministrateurRepository;
import com.example.Sandwichi.services.AdministrateurService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Testcontainers
public class AdministrateurControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Container
    //@ServiceConnection
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres");

    @Autowired
    private AdministrateurRepository administrateurRepository;

    @Autowired
    private AdministrateurService administrateurService;

    private List<Administrateur> administrateurs;

    @DynamicPropertySource
    static void dynamicConfiguration(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);


    }

    @BeforeEach
    public void setup() {
        // Clear existing data and set up test data
        administrateurRepository.deleteAll(); // Clear the repository
        administrateurs = new ArrayList<>();
        administrateurs.add(administrateurService.saveAdministrateur(new Administrateur("John", "Doe", "john.doe@example.com")));
        administrateurs.add(administrateurService.saveAdministrateur(new Administrateur("Jane", "Doe", "jane.doe@example.com")));
    }

    @Test
    public void testGetAllAdministrateurs() throws Exception {
        mockMvc.perform(get("/administrateurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nom").value("John"))
                .andExpect(jsonPath("$[1].nom").value("Jane"));
    }

    @Test
    public void testGetAdministrateurById() throws Exception {
        Administrateur admin = administrateurs.get(0);

        mockMvc.perform(get("/administrateurs/{id}", admin.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("John"));
    }

    @Test
    public void testCreateAdministrateur() throws Exception {
        String adminJson = "{\"nom\":\"Paul\",\"prenom\":\"Smith\",\"email\":\"paul.smith@example.com\"}";

        mockMvc.perform(post("/administrateurs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(adminJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("Paul"));
    }

    @Test
    public void testDeleteAdministrateur() throws Exception {


        // Arrange
        Administrateur admin = new Administrateur("mohamed", "jabnouni", "mohamed.jabnouni@example.com");
        admin = administrateurRepository.save(admin); // Save and get the generated ID

        log.info("id de l'administrateur {}", admin.getId());

        // Perform delete and expect a successful deletion

        mockMvc.perform(delete("/administrateurs/{id}", admin.getId()))
                .andExpect(status().isNoContent()); // or status().isOk() depending on your API
        log.info("getting administrateur {}",administrateurRepository.findById(admin.getId()));
        assertTrue(administrateurRepository.findById(admin.getId()).isEmpty());

    }

}
