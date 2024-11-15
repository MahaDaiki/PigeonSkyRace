package com.example.pigeon.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.pigeon.entity.Competition;
import com.example.pigeon.entity.Pigeon;
import com.example.pigeon.entity.Resultat;
import com.example.pigeon.entity.Utilisateur;
import com.example.pigeon.repository.CompetitionRepository;
import com.example.pigeon.repository.PigeonRepository;
import com.example.pigeon.repository.ResultatRepository;
import com.example.pigeon.repository.UtilisateurRepository;
import com.example.pigeon.service.CalculService;

import com.example.pigeon.service.PigeonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CalculServiceImplTest {


    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private ResultatRepository resultatRepository;

    @Mock
    private PigeonRepository pigeonRepository;
    @Mock
    private UtilisateurRepository utilisateurRepository;

    @InjectMocks
    private CalculServiceImpl calculService;

    private Competition competition;
    private Resultat resultat;
    private Utilisateur utilisateur;
    private Pigeon pigeon;

    @BeforeEach
    void setUp() {

        competition = new Competition();
        competition.setId("comp123");
        competition.setLatitudeLacher(34.0522);
        competition.setLongitudeLacher(-118.2437);
        competition.setDateHeureDepart(LocalDateTime.now().minusHours(6));
        competition.setEstTermine(false);

        pigeon = new Pigeon();
        pigeon.setId("pigeon1");


        utilisateur = new Utilisateur();
        utilisateur.setId("user1");
        utilisateur.setPigeons(List.of(pigeon));
        utilisateur.setLatitude(34.1);
        utilisateur.setLongitude(-6.1);


        resultat = new Resultat();
        resultat.setCompetitionId(competition.getId());
        resultat.setPigeonId(pigeon.getId());
        resultat.setHeureArrivee(LocalDateTime.now());

    }

    @Test
    void cloturerCompetitionEtCalculer_ShouldReturnTrue_WhenCompetitionIsValid() {
        when(competitionRepository.findById(competition.getId())).thenReturn(Optional.of(competition));
        when(resultatRepository.findByCompetitionId(competition.getId())).thenReturn(Collections.singletonList(resultat));
        when(utilisateurRepository.findByPigeonId(pigeon.getId())).thenReturn(Optional.of(utilisateur));

        boolean result = calculService.cloturerCompetitionEtCalculer(competition.getId());


        assertTrue(result, "The competition should be closed and the calculations should succeed.");


        verify(competitionRepository).save(competition);
        verify(resultatRepository, atLeastOnce()).save(resultat);
    }
    @Test
    void testCalculerVitesse() {
        double distance = 500.0;
        Duration duration = Duration.ofMinutes(6 * 60);
        double vitesse = calculService.calculerVitesse(distance, duration);
        System.out.println("Calculated speed: " + vitesse);
        assertEquals(1.3889, vitesse, 0.01, "Vitesse should be approximately 1.3889 meters/minute.");
    }
    @Test
    void testCalculerDistanceHaversine() {

        double lat1 = 34.0522;
        double lon1 = -118.2437;
        double lat2 = 34.0622;
        double lon2 = -118.2537;

        double distance = calculService.calculerDistanceHaversine(lat1, lon1, lat2, lon2);

        assertTrue(distance > 0, "Distance should be positive.");
    }
    @Test
    void cloturerCompetitionEtCalculer_ShouldThrowException_WhenCompetitionIsNotFound() {

        when(competitionRepository.findById("comp999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> calculService.cloturerCompetitionEtCalculer("comp999"), "Compétition non trouvée");

        verify(competitionRepository, never()).save(any());
        verify(resultatRepository, never()).save(any());
    }

    @Test
    void cloturerCompetitionEtCalculer_ShouldReturnFalse_WhenNoResultsAreFound() {

        when(competitionRepository.findById(competition.getId())).thenReturn(Optional.of(competition));
        when(resultatRepository.findByCompetitionId(competition.getId())).thenReturn(Collections.emptyList());

        assertThrows(RuntimeException.class, () -> calculService.cloturerCompetitionEtCalculer(competition.getId()), "Aucun résultat trouvé pour cette compétition.");

        verify(competitionRepository, never()).save(any());
        verify(resultatRepository, never()).save(any());
    }

    @Test
    void testCalculerVitesse_ShouldReturnCorrectSpeed() {
        double distance = 500.0;
        Duration duration = Duration.ofMinutes(6 * 60);
        double vitesse = calculService.calculerVitesse(distance, duration);
        System.out.println("Calculated speed: " + vitesse);
        assertEquals(1.3889, vitesse, 0.01, "Vitesse should be approximately 1.3889 meters/minute.");
    }

    @Test
    void testCalculerVitesse_ShouldReturnZero_WhenDurationIsZero() {
        double distance = 500.0;
        Duration duration = Duration.ZERO;
        double vitesse = calculService.calculerVitesse(distance, duration);
        assertEquals(0, vitesse, "Speed should be zero when duration is zero.");
    }

    @Test
    void testCalculerVitesse_ShouldReturnNegative_WhenDistanceIsNegative() {
        double distance = -500.0;
        Duration duration = Duration.ofMinutes(6 * 60);
        double vitesse = calculService.calculerVitesse(distance, duration);
        assertTrue(vitesse < 0, "Speed should be negative when distance is negative.");
    }

}