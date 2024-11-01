package com.example.pigeon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CompetitionDto {
    private String id;

    @NotBlank(message = "Le nom de la course ne peut pas être vide")
    private String nomCourse;

    @NotNull(message = "Les coordonnées GPS du point de lâcher ne peuvent pas être nulles")
    private double latitudeLacher;

    @NotNull(message = "Les coordonnées GPS du point de lâcher ne peuvent pas être nulles")
    private double longitudeLacher;

    @NotNull(message = "La date et l'heure de départ ne peuvent pas être nulles")
    private Date dateHeureDepart;

    @NotNull(message = "La distance prévisionnelle ne peut pas être nulle")
    private double distancePrevisionnelle;

    private String season;
    private Boolean estTermine;

    @JsonProperty("pigeons")
    private List<String> pigeonIds;
}
