package com.example.pigeon.controller;

import com.example.pigeon.entity.Eleveur;
import com.example.pigeon.service.EleveurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/eleveur")
public class EleveurController {

    @Autowired
    private EleveurService eleveurService;

    @GetMapping
    public ResponseEntity<List<Eleveur>> getAllEleveurs() {
        List<Eleveur> eleveurs = eleveurService.getAllEleveurs();
        return new ResponseEntity<>(eleveurs, HttpStatus.OK);
    }
}
