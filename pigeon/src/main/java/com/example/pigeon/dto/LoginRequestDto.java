package com.example.pigeon.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO pour la connexion d'un utilisateur.
 */
@Data
@NoArgsConstructor
public class LoginRequestDto {

    /** Nom d'utilisateur pour la connexion */
    @NotBlank(message = "Le nom d'utilisateur ne doit pas être vide.")
    private String username;

    /** Mot de passe pour la connexion */
    @NotBlank(message = "Le mot de passe ne doit pas être vide.")
    private String motDePasse;
}
