package com.example.pigeon.service;

import com.example.pigeon.dto.CompetitionDto;
import com.example.pigeon.entity.Competition;

public interface CompetitionService {
   Competition addCompetition(Competition competition);
    Competition getCompetitionById(String id);
    Competition modifyStatus(String id, Boolean estTermine);

}
