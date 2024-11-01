package com.example.pigeon.controller;


import com.example.pigeon.dto.CompetitionDto;
import com.example.pigeon.entity.Competition;
import com.example.pigeon.entity.Pigeon;
import com.example.pigeon.entity.Role;
import com.example.pigeon.service.CompetitionService;
import com.example.pigeon.service.PigeonService;
import jakarta.servlet.http.HttpSession;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/competition")
public class CompetitionController {

    @Autowired
    CompetitionService competitionService;
    @Autowired
    private PigeonService pigeonService;
    @PostMapping("/add")
    public ResponseEntity<String> addCompetition(@RequestBody CompetitionDto competitionDto, HttpSession session) {
        System.out.println("Received CompetitionDto: " + competitionDto);
        String userId = (String) session.getAttribute("utilisateurId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }


        Role role = (Role) session.getAttribute("utilisateurRole");
        if (role != Role.organisateur) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : rôle 'organisateur' requis");
        }


        List<String> pigeonIds = competitionDto.getPigeonIds();
        System.out.println("Pigeon IDs requested: " + pigeonIds);


        if (pigeonIds == null || pigeonIds.isEmpty()) {
            return ResponseEntity.badRequest().body("La liste des IDs de pigeon est vide");
        }


        List<Pigeon> existingPigeons = pigeonService.getPigeonsByIds(pigeonIds);
        System.out.println("Found pigeons: " + existingPigeons);


        if (existingPigeons.size() != pigeonIds.size()) {
            return ResponseEntity.badRequest().body("Un ou plusieurs IDs de pigeon sont invalides");
        }


        Competition competition = new Competition();
        competition.setNomCourse(competitionDto.getNomCourse());
        competition.setLatitudeLacher(competitionDto.getLatitudeLacher());
        competition.setLongitudeLacher(competitionDto.getLongitudeLacher());
        competition.setDateHeureDepart(competitionDto.getDateHeureDepart());
        competition.setDistancePrevisionnelle(competitionDto.getDistancePrevisionnelle());
        competition.setSeason(competitionDto.getSeason());
        competition.setEstTermine(competitionDto.getEstTermine());
        competition.setPigeons(existingPigeons);

        // Save the competition
        competitionService.addCompetition(competition);
        return ResponseEntity.ok("Compétition ajoutée avec succès");
    }




}
