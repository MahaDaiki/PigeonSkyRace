package com.example.pigeon.entity;


import com.example.pigeon.dto.UtilisateurDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Getter
@Data
@Builder
@Document(collection = "utilisateurs")
public class Utilisateur {

    @Setter
    @Id
    private String id;

    @Setter
    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    @Indexed(unique = true)
    private String username;


    @Setter
    @NotNull(message = "Le mot de passe ne peut pas être nul")
    private String motDePasse;

    @Setter
    @Getter
    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    @Setter
    @Getter
    private double latitude;

    @Setter
    @Getter
    private double longitude;

    @Setter
    @Getter
    @NotBlank(message = "Ou est le roooole ?????")
    private Role role;


    @Getter
    @Setter
    @DBRef
    private List<Pigeon> pigeons;

    public Utilisateur() {}

    public Utilisateur(String id, String username, String motDePasse, String nom, double latitude, double longitude, Role role, List<Pigeon> pigeons) {
        this.id = id;
        this.username = username;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.role = role;
        this.pigeons = pigeons;
    }

}
