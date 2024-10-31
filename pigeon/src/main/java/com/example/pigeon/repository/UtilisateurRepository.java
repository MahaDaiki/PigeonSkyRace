package com.example.pigeon.repository;

import com.example.pigeon.entity.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {
    Utilisateur findByUsernameAndMotDePasse(String username, String motDePasse);
    Optional<Utilisateur> findByUsername(String username);

}