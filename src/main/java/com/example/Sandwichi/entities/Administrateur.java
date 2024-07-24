package com.example.Sandwichi.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
public class Administrateur extends Utilisateur {

    public Administrateur(String nom, String prenom, String email) {
        super(nom, prenom, email);
    }
}