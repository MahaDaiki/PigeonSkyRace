package com.example.pigeon.controller;

import com.example.pigeon.entity.Eleveur;
import com.example.pigeon.service.EleveurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private EleveurService eleveurService;

    @PostMapping("/register")
    public ResponseEntity<Eleveur> registerUtilisateur(@RequestBody Eleveur user) {
        Eleveur registeredEleveur = eleveurService.registerEleveur(user);
        return new ResponseEntity<>(registeredEleveur, HttpStatus.CREATED);
    }

}
