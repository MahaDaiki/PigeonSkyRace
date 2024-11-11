package com.example.pigeon.controller;

import com.example.pigeon.dto.ResultatDto;
import com.example.pigeon.entity.Role;
import com.example.pigeon.service.ResultatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;


    @PostMapping("/{competitionId}")
    public ResponseEntity<List<ResultatDto>> createResultsForCompetition(
            @PathVariable String competitionId,
            HttpSession session,
            @RequestBody List<ResultatDto> resultatDtos) {

        String userId = (String) session.getAttribute("utilisateurId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Role role = (Role) session.getAttribute("utilisateurRole");
        if (role != Role.organisateur) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        System.out.println(resultatDtos + " results ");
        List<ResultatDto> resultats = new ArrayList<>();

        for (ResultatDto resultatDto : resultatDtos) {
            List<ResultatDto> createdResults = resultatService.createResultsForCompetition(competitionId,  resultatDtos);
            resultats.addAll(createdResults);
        }

        if (resultats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(resultats, HttpStatus.CREATED);
    }


    @GetMapping("/{competitionId}")
    public ResponseEntity<List<ResultatDto>> getResultsByCompetitionId(@PathVariable String competitionId) {
        List<ResultatDto> resultats = resultatService.getResultsByCompetitionId(competitionId);
        if (resultats.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(resultats, HttpStatus.OK);
    }
}