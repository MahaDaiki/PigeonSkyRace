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
    public CompetitionDto addCompetition(CompetitionDto competitionDto) {
        Competition competition = competitionDto.toEntity();
        Competition savedCompetition = competitionRepository.save(competition);
        return CompetitionDto.toDto(savedCompetition);
    }

    @Override
    public CompetitionDto getCompetitionById(String id) {
        Competition competition = competitionRepository.findById(id).orElse(null);
        return competition != null ? CompetitionDto.toDto(competition) : null;
    }


    @Override
    public CompetitionDto modifyStatus(String id, Boolean estTermine) {
        try {
            Competition competition = competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found with id: " + id));
            competition.setEstTermine(estTermine);
            Competition updatedCompetition = competitionRepository.save(competition);
            return CompetitionDto.toDto(updatedCompetition);
        } catch (Exception e) {
            System.err.println("Error modifying competition status: " + e.getMessage());
            return null;
        }
    }

}
