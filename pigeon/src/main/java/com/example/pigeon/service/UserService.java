package com.example.pigeon.service;

import com.example.pigeon.dto.UtilisateurDto;
import com.example.pigeon.entity.Utilisateur;

import java.util.List;

public interface UserService {
    Utilisateur findByUsernameAndMotDePasse(String username, String motDePasse);
    UtilisateurDto registerUtilisateur(UtilisateurDto user);
    List<Utilisateur> getAllUtilisateurs();
}
