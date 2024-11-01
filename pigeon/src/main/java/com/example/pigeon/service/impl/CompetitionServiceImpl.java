package com.example.pigeon.service.impl;

import com.example.pigeon.dto.CompetitionDto;
import com.example.pigeon.entity.Competition;
import com.example.pigeon.entity.Pigeon;
import com.example.pigeon.repository.CompetitionRepository;
import com.example.pigeon.service.CompetitionService;
import com.example.pigeon.service.PigeonService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private PigeonService pigeonService;

    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public Competition getCompetitionById(String id) {
        return null;
    }

    @Override
    public Competition modifyStatus(String id, Boolean estTermine) {
        return null;
    }
}
