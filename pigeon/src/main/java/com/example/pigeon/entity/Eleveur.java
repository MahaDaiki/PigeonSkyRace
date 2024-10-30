package com.example.pigeon.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "eleveurs")
public class Eleveur extends Utilisateur {


    @Setter
    @Getter
    @NotBlank(message = "Le nom du colombier ne peut pas être vide")
    private String nomColombier;

    @Setter
    @Getter
    @NotNull(message = "La latitude ne peut pas être nulle")
    private double latitude;

    @Setter
    @Getter
    @NotNull(message = "La longitude ne peut pas être nulle")
    private double longitude;


    @Getter
    @Setter
    @DBRef
    private List<Pigeon> pigeons;

    public Eleveur() {
        super();
    }
    public Eleveur(String username ,String motDePasse,String nomColombier, double latitude, double longitude, List<Pigeon> pigeons ) {
        super(username, motDePasse);

        this.nomColombier = nomColombier;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pigeons = pigeons;
    }

}
