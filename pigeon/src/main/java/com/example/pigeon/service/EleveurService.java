package com.example.pigeon.service;

import com.example.pigeon.entity.Eleveur;

import java.util.List;

public interface EleveurService {
    Eleveur registerEleveur(Eleveur user);
    List<Eleveur> getAllEleveurs();
}
