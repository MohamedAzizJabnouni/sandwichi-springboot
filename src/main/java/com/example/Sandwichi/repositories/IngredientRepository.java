package com.example.Sandwichi.repositories;

import com.example.Sandwichi.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    @Query("SELECT i.nom FROM Commande c JOIN c.sandwitches s JOIN s.ingredients i WHERE c.dateCommande = :date GROUP BY i.nom ORDER BY COUNT(i) DESC")
    String findIngredientPlusVendu(@Param("date") Date date);

}
