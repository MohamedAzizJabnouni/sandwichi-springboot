package com.example.Sandwichi.controllers;

import com.example.Sandwichi.services.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
public class StatistiqueController {

    @Autowired
    private StatistiqueService statistiqueService;

    @GetMapping("/statistiques")
    public Map<String, Object> getStatistiques(Date date){
        return statistiqueService.getStatistiques(date);
    }
}
