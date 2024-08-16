package com.example.Sandwichi.services;

import com.example.Sandwichi.entities.Administrateur;
import com.example.Sandwichi.repositories.AdministrateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    public List<Administrateur> findAllAdministrateurs() {
        return administrateurRepository.findAll();
    }

    public Optional<Administrateur> findAdministrateurById(Long id) {
        return administrateurRepository.findById(id);
    }

    public Administrateur saveAdministrateur(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    public void deleteAdministrateur(Long id) {
        administrateurRepository.deleteById(id);
    }
}
