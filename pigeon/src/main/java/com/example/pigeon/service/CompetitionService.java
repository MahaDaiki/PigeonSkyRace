package com.example.pigeon.service;

import com.example.pigeon.dto.CompetitionDto;
import com.example.pigeon.entity.Competition;

public interface CompetitionService {
    CompetitionDto addCompetition(CompetitionDto competitionDto);
    CompetitionDto getCompetitionById(String id);
    CompetitionDto modifyStatus(String id, Boolean estTermine);

}
