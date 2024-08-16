package com.example.Sandwichi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double prix;
    private int quantite;
    private String photo;


    public Ingredient(String nom, double prix, int quantite, String photo, Sandwitch sandwich) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.sandwich = sandwich;
    }

    @ManyToOne // Many ingredients can belong to one sandwich
    private Sandwitch sandwich;


}
