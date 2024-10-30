package com.example.pigeon.service.impl;

import com.example.pigeon.entity.Eleveur;
import com.example.pigeon.exception.ResourceNotFoundException;
import com.example.pigeon.repository.EleveurRepository;
import com.example.pigeon.service.EleveurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EleveurServiceImpl implements EleveurService {

    @Autowired
    private EleveurRepository eleveurRepository;

    @Override
    public Eleveur registerEleveur(Eleveur user) {
        if (eleveurRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ResourceNotFoundException("Un éleveur avec ce nom de colombier existe déjà");
        }
        return eleveurRepository.save(user);
    }

    @Override
    public List<Eleveur> getAllEleveurs() {
        return eleveurRepository.findAll();
    }

}
