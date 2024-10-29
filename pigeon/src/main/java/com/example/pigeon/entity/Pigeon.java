package com.example.pigeon.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "pigeons")
public class Pigeon {
    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @NotBlank(message = "Le numéro de bague ne peut pas être vide")
    private String numeroBague;

    @Getter
    @Setter
    @NotBlank(message = "La couleur ne peut pas être vide")
    private String couleur;

    @Getter
    @Setter
    @NotBlank(message = "L'age ne peut pas être vide")
    private int age;

    @Getter
    @Setter
    @NotNull(message = "L'ID de l'éleveur ne peut pas être nul")
    private String eleveurId;



    public Pigeon() {}

    public Pigeon(String numeroBague, String couleur, int age, String eleveurId) {
        this.numeroBague = numeroBague;
        this.couleur = couleur;
        this.age = age;
        this.eleveurId = eleveurId;

    }

}
