package com.example.pigeon.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "competitions")
public class Competition {
    @Id
    private String id;

    @Getter
    @Setter
    @NotBlank(message = "Le nom de la course ne peut pas être vide")
    private String nomCourse;


    @Getter
    @Setter
    @NotNull(message = "Les coordonnées GPS du point de lâcher ne peuvent pas être nulles")
    private double latitudeLacher;

    @Getter
    @Setter
    @NotNull(message = "Les coordonnées GPS du point de lâcher ne peuvent pas être nulles")
    private double longitudeLacher;

    @Getter
    @Setter
    @NotNull(message = "La date et l'heure de départ ne peuvent pas être nulles")
    private LocalDateTime dateHeureDepart;

    @Getter
    @Setter
    @NotNull(message = "La distance prévisionnelle ne peut pas être nulle")
    private double distancePrevisionnelle;

    @Getter
    @Setter
    private String season;

    @Getter
    @Setter
    private Boolean estTermine;
    @Getter
    @Setter
    @DBRef

    private List<Pigeon> pigeons;


    public Competition() {}

    public Competition(String nomCourse, double latitudeLacher, double longitudeLacher, LocalDateTime dateHeureDepart, double distancePrevisionnelle, String season, Boolean estTermine , List<Pigeon> pigeons) {
        this.nomCourse = nomCourse;
        this.latitudeLacher = latitudeLacher;
        this.longitudeLacher = longitudeLacher;
        this.dateHeureDepart = dateHeureDepart;
        this.distancePrevisionnelle = distancePrevisionnelle;
        this.season = season;
        this.estTermine = estTermine;
        this.pigeons = pigeons;
    }
}
