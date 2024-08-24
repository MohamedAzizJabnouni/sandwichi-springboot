package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Client;
import com.example.Sandwichi.services.ClientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/{id}")
    public Optional<Client> getClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        try {
            clientService.deleteClient(id);
            return ResponseEntity.noContent().build();  // 204 No Content
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }
}
