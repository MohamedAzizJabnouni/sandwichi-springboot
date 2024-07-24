package com.example.Sandwichi.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client extends Utilisateur{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Client(String nom, String prenom, String email, List<Commande> commandes) {
        super(nom, prenom, email);
        this.commandes = commandes;

    }
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commande> commandes;
}
