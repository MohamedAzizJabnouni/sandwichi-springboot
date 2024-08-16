package com.example.Sandwichi.services;

import com.example.Sandwichi.entities.Commande;
import com.example.Sandwichi.entities.Ingredient;
import com.example.Sandwichi.repositories.CommandeRepository;
import com.example.Sandwichi.repositories.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional
    public Commande saveCommande(Commande commande){
        commande.getSandwitches().forEach(sandwich -> {
            sandwich.getIngredients().forEach(ingredient -> {
                Ingredient dbIngredient = ingredientRepository.findById(ingredient.getId())
                        .orElseThrow(()->new RuntimeException("Ingredient not found: "+ingredient.getId()));

                if(dbIngredient.getQuantite() < ingredient.getQuantite()){
                    throw new RuntimeException("Insufficient quantity for ingredient: "+ingredient.getId());
                }

                dbIngredient.setQuantite(dbIngredient.getQuantite()- ingredient.getQuantite());
                ingredientRepository.save(ingredient);
            });
        });
        return commandeRepository.save(commande);
    }
    public List<Commande> findAllCommandes() {
        return commandeRepository.findAll();
    }

    public Optional<Commande> findCommandeById(Long id) {
        return commandeRepository.findById(id);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}

