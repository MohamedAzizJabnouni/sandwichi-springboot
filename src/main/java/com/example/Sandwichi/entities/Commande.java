package com.example.Sandwichi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCommande;
    private String statut;

    public Commande(Date dateCommande, String statut, List<Sandwitch> sandwitches,Client client) {
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.sandwitches = sandwitches;
        this.client = client;
    }

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL) // Assuming a OneToMany relationship with Sandwitch entities
    private List<Sandwitch> sandwitches; // Adjust type as per your entity setup

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
