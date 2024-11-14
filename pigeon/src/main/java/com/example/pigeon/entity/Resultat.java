package com.example.pigeon.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
@Document(collection = "resultats")
public class Resultat {

    @Getter
    @Setter
    @Id
    private String id;

    @Getter
    @Setter
    @NotNull(message = "L'ID de la compétition ne peut pas être nul")
    private String competitionId;

    @Getter
    @Setter
    @NotNull(message = "Le numéro de bague ne peut pas être nul")
    private String pigeonId;

    @Getter
    @Setter
    private double distanceParcourue;

    @Getter
    @Setter
    private double vitesse;

    @Getter
    @Setter
    private Duration tempsParcourue;

    @Getter
    @Setter
    private LocalDateTime heureArrivee;

    @Getter
    @Setter
    private double point;

    @Getter
    @Setter
    private int classement;


    public Resultat() {}

    public Resultat(String competitionId, String pigeonId, double distanceParcourue, double vitesse, Duration tempsParcourue, LocalDateTime heureArrivee, double point, int classement) {
        this.competitionId = competitionId;
        this.pigeonId = pigeonId;
        this.distanceParcourue = distanceParcourue;
        this.vitesse = vitesse;
        this.tempsParcourue = tempsParcourue;
        this.heureArrivee = heureArrivee;
        this.point = point;
        this.classement = classement;
    }
}
