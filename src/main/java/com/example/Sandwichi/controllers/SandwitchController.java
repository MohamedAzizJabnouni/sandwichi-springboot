package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Sandwitch;
import com.example.Sandwichi.services.SandwitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sandwitches")
public class SandwitchController {

    @Autowired
    private SandwitchService sandwitchService;

    @GetMapping
    public List<Sandwitch> getAllSandwitches() {
        return sandwitchService.findAllSandwitches();
    }

    @GetMapping("/{id}")
    public Optional<Sandwitch> getSandwitchById(@PathVariable Long id) {
        return sandwitchService.findSandwitchById(id);
    }

    @PostMapping
    public Sandwitch createSandwitch(@RequestBody Sandwitch sandwitch) {
        return sandwitchService.saveSandwitch(sandwitch);
    }

    @DeleteMapping("/{id}")
    public void deleteSandwitch(@PathVariable Long id) {
        sandwitchService.deleteSandwitch(id);
    }
}
