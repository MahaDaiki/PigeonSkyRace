package com.example.pigeon.service;

import com.example.pigeon.dto.ResultatDto;

import java.util.Date;
import java.util.List;

public interface ResultatService {

    List<ResultatDto> createResultsForCompetition(String competitionId, List<ResultatDto> resultatDtos);
    List<ResultatDto> getResultsByCompetitionId(String competitionId);
}
