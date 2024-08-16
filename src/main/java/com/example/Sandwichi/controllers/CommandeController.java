package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Commande;
import com.example.Sandwichi.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.findAllCommandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long id) {
        Optional<Commande> commande = commandeService.findCommandeById(id);
        return commande.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.saveCommande(commande);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Optional<Commande> optionalCommande = commandeService.findCommandeById(id);
        if (optionalCommande.isPresent()) {
            Commande commande = optionalCommande.get();
            commande.setDateCommande(commandeDetails.getDateCommande());
            commande.setStatut(commandeDetails.getStatut());
            commande.setSandwitches(commandeDetails.getSandwitches());
            commande.setClient(commandeDetails.getClient());
            Commande updatedCommande = commandeService.saveCommande(commande);
            return ResponseEntity.ok(updatedCommande);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}
