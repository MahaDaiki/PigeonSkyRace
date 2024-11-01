    package com.example.pigeon.service.impl;

    import com.example.pigeon.dto.UtilisateurDto;
    import com.example.pigeon.entity.Utilisateur;
    import com.example.pigeon.exception.ResourceNotFoundException;
    import com.example.pigeon.repository.UtilisateurRepository;
    import com.example.pigeon.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UtilisateurRepository utilisateurRepository;

        public Utilisateur findByUsernameAndMotDePasse(String username, String motDePasse) {
            return utilisateurRepository.findByUsernameAndMotDePasse(username, motDePasse);
        }

        @Override
        public UtilisateurDto registerUtilisateur(UtilisateurDto userDto) {
            if (utilisateurRepository.findByUsername(userDto.getUsername()).isPresent()) {
                throw new ResourceNotFoundException("Un éleveur avec ce nom de colombier existe déjà");
            }
            Utilisateur utilisateur = userDto.toEntity();
            Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
            return UtilisateurDto.toDto(savedUtilisateur);
        }


        @Override
        public List<Utilisateur> getAllUtilisateurs() {
            return utilisateurRepository.findAll();
        }
    }
