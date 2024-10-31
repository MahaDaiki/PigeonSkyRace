package com.example.pigeon.service;

import com.example.pigeon.entity.Utilisateur;

import java.util.List;

public interface UserService {
    Utilisateur findByUsernameAndMotDePasse(String username, String motDePasse);
    Utilisateur registerUtilisateur(Utilisateur user);
    List<Utilisateur> getAllUtilisateurs();
}
