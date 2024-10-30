package com.example.pigeon.repository;

import com.example.pigeon.entity.Eleveur;
import com.example.pigeon.entity.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveurRepository extends MongoRepository<Eleveur, String> {
    Optional<Eleveur> findByUsername(String username);
}
