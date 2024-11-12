package com.example.pigeon.controller;


import com.example.pigeon.dto.CompetitionDto;
import com.example.pigeon.dto.CompetitionRequestDto;
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
import org.springframework.web.bind.annotation.*;

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


        Competition competition = competitionDto.toEntity(existingPigeons);

        System.out.println("Competition with pigeons: " + competition);

        CompetitionDto savedCompetitionDto = competitionService.addCompetition(competitionDto);
        return ResponseEntity.ok("Compétition ajoutée avec succès");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDto> getCompetitionById(@PathVariable String id) {
        CompetitionDto competitionDto = competitionService.getCompetitionById(id);
        if (competitionDto != null) {
            return ResponseEntity.ok(competitionDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> modifyStatus(
            @PathVariable String id,
            @RequestBody CompetitionRequestDto requestDto,
            HttpSession session) {


        System.out.println("Received request to update status for competition ID: " + id + " with estTermine: " + requestDto.getEstTermine());


        String userId = (String) session.getAttribute("utilisateurId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }

        Role role = (Role) session.getAttribute("utilisateurRole");
        if (role != Role.organisateur) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : rôle 'organisateur' requis");
        }


        Boolean estTermine = requestDto.getEstTermine();
        System.out.println("Updating status for competition ID: " + id + " to estTermine: " + estTermine);

        CompetitionDto updatedCompetition = competitionService.modifyStatus(id, estTermine);
        if (updatedCompetition != null) {
            return ResponseEntity.ok("Statut de la compétition mis à jour avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compétition non trouvée");
        }
    }


}












