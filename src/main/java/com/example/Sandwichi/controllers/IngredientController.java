package com.example.Sandwichi.controllers;

import com.example.Sandwichi.entities.Ingredient;
import com.example.Sandwichi.services.IngredientService;
import com.example.Sandwichi.services.StatistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class IngredientController {
    @Autowired
    private IngredientService findIngredientById ;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private StatistiqueService statistiqueService;

    @GetMapping("/ingredient-plus-vendu")
    public String getIngredientPlusVendu(Date date) {
        return statistiqueService.findIngredientPlusVendu(date);
    }

    @GetMapping("/ingredients")
    public List<Ingredient> findAllIngredients() {
        return ingredientService.findAllIngredients();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        Optional<Ingredient> ingredient = ingredientService.findIngredientById(id);
        return ingredient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/ingredients")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.saveIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredientDetails) {
        Optional<Ingredient> optionalIngredient = ingredientService.findIngredientById(id);
        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            ingredient.setNom(ingredientDetails.getNom());
            ingredient.setPrix(ingredientDetails.getPrix());
            ingredient.setQuantite(ingredientDetails.getQuantite());
            ingredient.setPhoto(ingredientDetails.getPhoto());
            Ingredient updatedIngredient = ingredientService.saveIngredient(ingredient);
            return ResponseEntity.ok(updatedIngredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}

