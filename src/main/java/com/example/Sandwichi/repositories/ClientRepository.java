package com.example.Sandwichi.repositories;

import com.example.Sandwichi.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
