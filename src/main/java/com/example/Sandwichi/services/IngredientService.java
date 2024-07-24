package com.example.Sandwichi.services;

import com.example.Sandwichi.entities.Ingredient;
import com.example.Sandwichi.repositories.IngredientRepository;
import com.example.Sandwichi.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> findIngredientById(Long id) { return ingredientRepository.findById(id);
    }
    public Ingredient saveIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id){
        ingredientRepository.deleteById(id);
    }
}
