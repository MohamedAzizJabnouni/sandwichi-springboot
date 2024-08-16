package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Administrateur;
import com.example.Sandwichi.services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/administrateurs")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping
    public List<Administrateur> getAllAdministrateurs() {
        return administrateurService.findAllAdministrateurs();
    }

    @GetMapping("/{id}")
    public Optional<Administrateur> getAdministrateurById(@PathVariable Long id) {
        return administrateurService.findAdministrateurById(id);
    }

    @PostMapping
    public Administrateur createAdministrateur(@RequestBody Administrateur administrateur) {
        return administrateurService.saveAdministrateur(administrateur);
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrateur(@PathVariable Long id) {
        administrateurService.deleteAdministrateur(id);
    }
}
