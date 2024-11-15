package com.example.pigeon.service.impl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.pigeon.entity.Competition;
import com.example.pigeon.entity.Resultat;
import com.example.pigeon.entity.Utilisateur;
import com.example.pigeon.repository.CompetitionRepository;
import com.example.pigeon.repository.ResultatRepository;
import com.example.pigeon.repository.UtilisateurRepository;
import com.example.pigeon.service.CalculService;

@Service
public class CalculServiceImpl implements CalculService {

    private static final double EARTH_RADIUS = 6371000;
    public static final double DEFAULT_ADMISSION_PERCENTAGE = 0.25;

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public boolean cloturerCompetitionEtCalculer(String competitionId) {
        Competition competition = competitionRepository.findById(competitionId)
                .orElseThrow(() -> new RuntimeException("Compétition non trouvée"));

        List<Resultat> resultats = resultatRepository.findByCompetitionId(competitionId);
        if (resultats.isEmpty()) {
            throw new RuntimeException("Aucun résultat trouvé pour cette compétition.");
        }

        try {
            resultats.forEach(resultat -> {
                Utilisateur utilisateur = utilisateurRepository.findByPigeonId(resultat.getPigeonId())
                        .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé pour le pigeon : " + resultat.getPigeonId()));

                double distanceParcourue = calculerDistanceHaversine(competition.getLatitudeLacher(), competition.getLongitudeLacher(), utilisateur.getLatitude(), utilisateur.getLongitude());

                Duration dureeParcourue = Duration.between(competition.getDateHeureDepart(), resultat.getHeureArrivee());
                double vitesse = calculerVitesse(distanceParcourue, dureeParcourue);

                resultat.setDistanceParcourue(distanceParcourue);
                resultat.setVitesse(vitesse);
                resultat.setTempsParcourue(dureeParcourue);

                resultatRepository.save(resultat);
            });

            resultats.sort((r1, r2) -> r1.getHeureArrivee().compareTo(r2.getHeureArrivee()));

            int totalPigeons = resultats.size();
            int admissionCount = (int) Math.ceil(totalPigeons * DEFAULT_ADMISSION_PERCENTAGE);

            int[] rank = {1};
            resultats.stream()
                    .limit(admissionCount)
                    .forEach(resultat -> {
                        resultat.setClassement(rank[0]);

                        double point;
                        if (rank[0] == 1) {
                            point = 100;
                        } else {
                            point = 100 - ((double) (rank[0] - 1) / (admissionCount - 1)) * 100;
                        }
                        resultat.setPoint(point);

                        resultatRepository.save(resultat);
                        rank[0]++;
                    });

            competition.setEstTermine(true);
            competitionRepository.save(competition);

            return true;
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde: " + e.getMessage());
            return false;
        }
    }

    public double calculerDistanceHaversine(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    public double calculerVitesse(double distance, Duration duree) {
        double dureeEnMinutes = duree.toMinutes();
        return dureeEnMinutes > 0 ? (distance / dureeEnMinutes) : 0;
    }
}