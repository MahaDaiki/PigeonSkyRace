package com.example.pigeon.controller;

import com.example.pigeon.dto.LoginRequestDto;
import com.example.pigeon.dto.UtilisateurDto;
import com.example.pigeon.entity.Utilisateur;
import com.example.pigeon.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UtilisateurDto> registerUtilisateur(@RequestBody UtilisateurDto user) {
        UtilisateurDto registeredUtilisateur = userService.registerUtilisateur(user);
        return new ResponseEntity<>(registeredUtilisateur, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequest, HttpSession session) {
        String username = loginRequest.getUsername();
        String motDePasse = loginRequest.getMotDePasse();

        if (username == null || motDePasse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nom d'utilisateur ou mot de passe manquant");
        }

        Utilisateur utilisateur = userService.findByUsernameAndMotDePasse(username, motDePasse);

        if (utilisateur != null) {
            session.setAttribute("utilisateurId", utilisateur.getId());
            session.setAttribute("username", utilisateur.getUsername());
            session.setAttribute("utilisateurRole", utilisateur.getRole());
            return ResponseEntity.ok("Connexion réussie");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Déconnexion réussie");
    }

}
