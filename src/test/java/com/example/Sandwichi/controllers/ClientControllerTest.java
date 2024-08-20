package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Client;
import com.example.Sandwichi.entities.Commande;
import com.example.Sandwichi.services.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class ClientControllerTest {

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    public ClientControllerTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllClients() {
        Client client1 = new Client("aziz", "jb", "aziz.jb@example.com", null);
        when(clientService.findAllClients()).thenReturn(Arrays.asList(client1));

        List<Client> result = clientController.getAllClients();

        assertNotNull(result);
        assertEquals(1,result.size());

    }

    @Test
    void tesGetClientById() {
        Client client = new Client("aziz", "jb", "aziz.jb@example.com", null);
        when(clientService.findClientById(1L)).thenReturn(Optional.of(client));

        Optional<Client> result = clientController.getClientById(1L);

        assertTrue(result.isPresent());
        assertEquals("aziz", result.get().getNom());

    }

    @Test
    void testcreateClient() {
        Client client = new Client("aziz", "jb", "aziz.jb@example.com", null);
        when(clientService.saveClient(any(Client.class))).thenReturn(client);

        Client result = clientController.createClient(client);

        assertNotNull(result);
        assertEquals("aziz",result.getNom());

    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientService).deleteClient(1L);

        clientController.deleteClient(1L);

    }
}