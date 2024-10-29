package com.example.pigeon.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "utilisateurs")
public class Utilisateur {

    @Setter
    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    @Indexed(unique = true)
    private String nom;


    @Getter
    @Setter
    @NotNull(message = "Le mot de passe ne peut pas être nul")
    private String motDePasse;

    public Utilisateur() {}

    public Utilisateur( String nom, String motDePasse) {
    this.nom = nom;
    this.motDePasse = motDePasse;
    }



}
