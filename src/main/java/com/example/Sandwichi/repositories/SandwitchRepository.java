package com.example.Sandwichi.repositories;


import com.example.Sandwichi.entities.Sandwitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;


@RestResource

public interface SandwitchRepository extends JpaRepository<Sandwitch, Long> {
    @Query("SELECT SUM(s.prix) FROM Commande c JOIN c.sandwitches s WHERE c.dateCommande = :date")
    double calculateBenefice(@Param("date") Date date);
}
