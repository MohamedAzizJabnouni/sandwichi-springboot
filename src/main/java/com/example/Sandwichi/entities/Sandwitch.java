package com.example.Sandwichi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Sandwitch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String description;
    private String photo;
    private double prix;

    public Sandwitch(String nom, String description, String photo, double prix, Commande commande, List<Ingredient> ingredients) {
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.prix = prix;
        this.commande = commande;
        this.ingredients = ingredients;
    }

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @OneToMany(mappedBy = "sandwich", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingredient> ingredients;


}
