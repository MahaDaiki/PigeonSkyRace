package com.example.pigeon.controller;

import com.example.pigeon.dto.PigeonDto;
import com.example.pigeon.entity.Pigeon;
import com.example.pigeon.entity.Role;
import com.example.pigeon.service.PigeonService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pigeons")
public class PigeonController {

    @Autowired
    private PigeonService pigeonService;

    @PostMapping("/add")
    public ResponseEntity<String> addPigeon(@RequestBody PigeonDto pigeonDto, HttpSession session) {


        String userId = (String) session.getAttribute("utilisateurId");
        System.out.println("utilisateurId: " + userId);


        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non authentifié");
        }

       Role role = (Role) session.getAttribute("utilisateurRole");
        System.out.println("Role: " + role);

        if (role != Role.eleveur) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Accès refusé : rôle 'eleveur' requis");
        }
        Pigeon pigeon = pigeonDto.toEntity();
        pigeon.setEleveurId(userId);
        pigeonService.addPigeon(PigeonDto.toDto(pigeon));
        return ResponseEntity.ok("Pigeon ajouté avec succès");
    }


    @GetMapping
    public ResponseEntity<List<Pigeon>> getAllPigeons() {
        List<Pigeon> pigeons = pigeonService.getAllPigeons();
        return ResponseEntity.ok(pigeons);
    }


    @GetMapping("/user")
    public ResponseEntity<List<Pigeon>> getPigeonsByUserId(HttpSession session) {
        String userId = (String) session.getAttribute("utilisateurId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Pigeon> pigeons = pigeonService.getPigeonsByUserId(userId);
        return ResponseEntity.ok(pigeons);
    }

}
