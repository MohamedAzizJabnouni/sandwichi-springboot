package com.example.Sandwichi.repositories;

import com.example.Sandwichi.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT COUNT(c) FROM Commande c JOIN c.sandwitches s WHERE c.dateCommande = :date")
    int findNombreSandichsVendus(@Param("date") Date date);

    @Query("SELECT SUM(s.prix) FROM Commande c JOIN c.sandwitches s WHERE c.dateCommande = :date")
    double findBenefices(@Param("date") Date date);
}
