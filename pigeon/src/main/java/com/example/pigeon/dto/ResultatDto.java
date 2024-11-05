package com.example.pigeon.dto;

import com.example.pigeon.entity.Resultat;
import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResultatDto {

        private String id;
        private String competitionId;
        private String pigeonId;
        private double distanceParcourue;
        private double vitesse;
        private Date tempsParcourue;
        private Date heureArrivee;
        private int point;


    public static ResultatDto toDto(Resultat entity) {
        return ResultatDto.builder()
                .id(entity.getId())
                .competitionId(entity.getCompetitionId())
                .pigeonId(entity.getPigeonId())
                .distanceParcourue(entity.getDistanceParcourue())
                .vitesse(entity.getVitesse())
                .tempsParcourue(entity.getTempsParcourue())
                .heureArrivee(entity.getHeureArrivee())
                .point(entity.getPoint())
                .build();
    }

    public Resultat toEntity() {
        Resultat resultat = new Resultat();
        resultat.setId(this.id);
        resultat.setCompetitionId(this.competitionId);
        resultat.setPigeonId(this.pigeonId);
        resultat.setDistanceParcourue(this.distanceParcourue);
        resultat.setVitesse(this.vitesse);
        resultat.setTempsParcourue(this.tempsParcourue);
        resultat.setHeureArrivee(this.heureArrivee);
        resultat.setPoint(this.point);
        return resultat;
    }

}
