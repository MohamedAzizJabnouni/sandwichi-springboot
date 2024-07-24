package com.example.Sandwichi.services;

import com.example.Sandwichi.entities.Sandwitch;
import com.example.Sandwichi.repositories.SandwitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SandwitchService {

    @Autowired
    private SandwitchRepository sandwitchRepository;

    public List<Sandwitch> findAllSandwitches() {
        return sandwitchRepository.findAll();
    }

    public Optional<Sandwitch> findSandwitchById(Long id) {
        return sandwitchRepository.findById(id);
    }

    public Sandwitch saveSandwitch(Sandwitch sandwitch) {
        return sandwitchRepository.save(sandwitch);
    }

    public void deleteSandwitch(Long id) {
        sandwitchRepository.deleteById(id);
    }
}
