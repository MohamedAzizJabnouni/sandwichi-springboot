package com.example.Sandwichi.services;

import com.example.Sandwichi.entities.Commande;
import com.example.Sandwichi.entities.Ingredient;
import com.example.Sandwichi.repositories.CommandeRepository;
import com.example.Sandwichi.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StatistiqueService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public String findIngredientPlusVendu(Date date ){
        return ingredientRepository.findIngredientPlusVendu(date);
    }

    public Map<String, Object> getStatistiques(Date date){
       Map<String,Object> stats = new HashMap<>();
       stats.put("Nombre de sandwichs vendus",commandeRepository.findNombreSandichsVendus(date));
       stats.put("Benefices", commandeRepository.findBenefices(date));
       stats.put("Ingredient le plus vendu", ingredientRepository.findIngredientPlusVendu(date));
       return stats;
    }
}
