package com.example.pigeon.service;

import com.example.pigeon.dto.ResultatDto;

import java.util.List;

public interface ResultatService {

    List<ResultatDto> createResultsForCompetition(String competitionId);
    List<ResultatDto> getResultsByCompetitionId(String competitionId);
}
