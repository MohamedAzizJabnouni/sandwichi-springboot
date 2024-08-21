package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Administrateur;
import com.example.Sandwichi.services.AdministrateurService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministrateurControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdministrateurService administrateurService;

    @Test
    public void testGetAllAdministrateurs() throws Exception {
        Administrateur admin1 = new Administrateur("John", "Doe", "john.doe@example.com");
        Administrateur admin2 = new Administrateur("Jane", "Doe", "jane.doe@example.com");
        given(administrateurService.findAllAdministrateurs()).willReturn(Arrays.asList(admin1, admin2));

        mockMvc.perform(get("/administrateurs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nom").value("John"))
                .andExpect(jsonPath("$[1].nom").value("Jane"));
    }

    @Test
    public void testGetAdministrateurById() throws Exception {
        Administrateur admin = new Administrateur("John", "Doe", "john.doe@example.com");
        given(administrateurService.findAdministrateurById(1L)).willReturn(Optional.of(admin));

        mockMvc.perform(get("/administrateurs/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("John"));
    }

    @Test
    public void testCreateAdministrateur() throws Exception {
        Administrateur admin = new Administrateur("John", "Doe", "john.doe@example.com");
        given(administrateurService.saveAdministrateur(any(Administrateur.class))).willReturn(admin);

        mockMvc.perform(post("/administrateurs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nom\":\"John\",\"prenom\":\"Doe\",\"email\":\"john.doe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("John"));
    }

    @Test
    public void testDeleteAdministrateur() throws Exception {
        willDoNothing().given(administrateurService).deleteAdministrateur(1L);

        mockMvc.perform(delete("/administrateurs/1"))
                .andExpect(status().isOk());
    }
}
