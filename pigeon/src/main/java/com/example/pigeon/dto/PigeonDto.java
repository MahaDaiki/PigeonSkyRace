package com.example.pigeon.dto;

import com.example.pigeon.entity.Pigeon;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PigeonDto {
    private String id;

    @NotBlank(message = " Le numéro de bague ne peut pas être vide")
    private String numeroBague;

    @NotBlank(message = "La couleur ne peut pas être vide")
    private String couleur;

    @NotNull(message = "L'age ne peut pas être vide")
    private int age;

    @NotNull(message = "L'ID de l'éleveur ne peut pas être nul")
    private String eleveurId;

    public PigeonDto(String numeroBague, String couleur, int age, String eleveurId) {
        this.numeroBague = numeroBague;
        this.couleur = couleur;
        this.age = age;
        this.eleveurId = eleveurId;
    }

    public static PigeonDto toDto(Pigeon pigeon) {
        PigeonDto pigeonDto = new PigeonDto();
        pigeonDto.setId(pigeon.getId());
        pigeonDto.setNumeroBague(pigeon.getNumeroBague());
        pigeonDto.setCouleur(pigeon.getCouleur());
        pigeonDto.setAge(pigeon.getAge());
        pigeonDto.setEleveurId(pigeon.getEleveurId());
        return pigeonDto;
    }

    public Pigeon toEntity() {
        Pigeon pigeon = new Pigeon();
        pigeon.setId(this.id);
        pigeon.setNumeroBague(this.numeroBague);
        pigeon.setCouleur(this.couleur);
        pigeon.setAge(this.age);
        pigeon.setEleveurId(this.eleveurId);
        return pigeon;
    }
}
