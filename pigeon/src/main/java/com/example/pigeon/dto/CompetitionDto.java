package com.example.pigeon.dto;

import com.example.pigeon.entity.Competition;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public static CompetitionDto toDto(Competition entity) {
        CompetitionDto dto = new CompetitionDto();
        dto.setId(entity.getId());
        dto.setNomCourse(entity.getNomCourse());
        dto.setLatitudeLacher(entity.getLatitudeLacher());
        dto.setLongitudeLacher(entity.getLongitudeLacher());
        dto.setDateHeureDepart(entity.getDateHeureDepart());
        dto.setDistancePrevisionnelle(entity.getDistancePrevisionnelle());
        dto.setSeason(entity.getSeason());
        dto.setEstTermine(entity.getEstTermine());
        dto.setPigeonIds(entity.getPigeons() != null
                ? entity.getPigeons().stream()
                .map(pigeon -> pigeon.getId())
                .collect(Collectors.toList())
                : null);
        return dto;
    }

    public Competition toEntity() {
        Competition competition = new Competition();
        competition.setId(this.id);
        competition.setNomCourse(this.nomCourse);
        competition.setLatitudeLacher(this.latitudeLacher);
        competition.setLongitudeLacher(this.longitudeLacher);
        competition.setDateHeureDepart(this.dateHeureDepart);
        competition.setDistancePrevisionnelle(this.distancePrevisionnelle);
        competition.setSeason(this.season);
        competition.setEstTermine(this.estTermine);

        return competition;
    }

    public void setMessage(String s) {

    }
}
