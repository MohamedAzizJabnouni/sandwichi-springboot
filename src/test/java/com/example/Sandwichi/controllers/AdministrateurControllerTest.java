package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Administrateur;
import com.example.Sandwichi.services.AdministrateurService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AdministrateurControllerTest {

    @InjectMocks
    private AdministrateurController administrateurController;

    @Mock
    private AdministrateurService administrateurService;

    public AdministrateurControllerTest() {
        MockitoAnnotations.openMocks(this); //initialize mocks
    }

    @Test
    public void testGetAllAdministrateurs() {
        Administrateur admin1 = new Administrateur("aziz", "jb", "aziz.jb@example.com");
        Administrateur admin2 = new Administrateur("ahmed", "jb", "ahmed.jb@example.com");
        when(administrateurService.findAllAdministrateurs()).thenReturn(Arrays.asList(admin1, admin2));

        List<Administrateur> result = administrateurController.getAllAdministrateurs();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(administrateurService, times(1)).findAllAdministrateurs();
    }

    @Test
    public void testGetAdministrateurById() {
        Administrateur admin = new Administrateur("aziz", "jb", "aziz.jb@example.com");
        when(administrateurService.findAdministrateurById(1L)).thenReturn(Optional.of(admin));

        Optional<Administrateur> result = administrateurController.getAdministrateurById(1L);

        assertTrue(result.isPresent());
        assertEquals("aziz", result.get().getNom());
        verify(administrateurService, times(1)).findAdministrateurById(1L);
    }

    @Test
    public void testCreateAdministrateur() {
        Administrateur admin = new Administrateur("aziz", "jb", "aziz.jb@example.com");
        when(administrateurService.saveAdministrateur(any(Administrateur.class))).thenReturn(admin);

        Administrateur result = administrateurController.createAdministrateur(admin);

        assertNotNull(result);
        assertEquals("aziz", result.getNom());
        verify(administrateurService, times(1)).saveAdministrateur(any(Administrateur.class));
    }

    @Test
    public void testDeleteAdministrateur() {
        doNothing().when(administrateurService).deleteAdministrateur(1L);

        administrateurController.deleteAdministrateur(1L);

        verify(administrateurService, times(1)).deleteAdministrateur(1L);
    }
}
