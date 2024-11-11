package com.example.pigeon.service.impl;

import com.example.pigeon.dto.ResultatDto;
import com.example.pigeon.entity.Competition;
import com.example.pigeon.entity.Pigeon;
import com.example.pigeon.entity.Resultat;
import com.example.pigeon.repository.CompetitionRepository;
import com.example.pigeon.repository.ResultatRepository;
import com.example.pigeon.service.ResultatService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResultatServiceImpl implements ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;


    @Autowired
    private CompetitionRepository competitionRepository;


    @Override
    public List<ResultatDto> createResultsForCompetition(String competitionId, List<ResultatDto> resultatDtos) {
        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition == null || competition.getPigeons() == null) {
            return new ArrayList<>();
        }

        List<ResultatDto> createdResults = new ArrayList<>();
        for (ResultatDto resultatDto : resultatDtos) {
            Resultat resultat = new Resultat();
            resultat.setCompetitionId(competitionId);
            resultat.setPigeonId(resultatDto.getPigeonId());
            resultat.setDistanceParcourue(0);
            resultat.setVitesse(0);
            resultat.setTempsParcourue(null);
            resultat.setHeureArrivee(resultatDto.getHeureArrivee());
            resultat.setPoint(0);

            Resultat savedResultat = resultatRepository.save(resultat);
            createdResults.add(ResultatDto.toDto(savedResultat));
        }

        return createdResults;
    }


    @Override
    public List<ResultatDto> getResultsByCompetitionId(String competitionId) {
        List<Resultat> resultats = resultatRepository.findByCompetitionId(competitionId);
        List<ResultatDto> resultatDtos = new ArrayList<>();
        for (Resultat resultat : resultats) {
            resultatDtos.add(ResultatDto.toDto(resultat));
        }
        return resultatDtos;
    }
}
