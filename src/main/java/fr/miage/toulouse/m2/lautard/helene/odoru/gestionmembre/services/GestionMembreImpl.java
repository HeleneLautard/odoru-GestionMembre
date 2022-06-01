package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.*;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.exceptions.MembreNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionMembreImpl implements GestionMembre {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    MembreRepository membreRepository;
    @Autowired
    SecretaireRepository secretaireRepository;
    @Autowired
    PresidentRepository presidentRepository;
    @Autowired
    EnseignantRepository enseignantRepository;
    @Autowired
    AdherentRepository adherentRepository;


    @Override
    public Adherent creerAdherent(Adherent adherent) {
        String motDePass = passwordEncoder.encode(adherent.getMdp());
        adherent.setMdp(motDePass);
        return adherentRepository.save(adherent);
    }

    @Override
    public Enseignant creerEnseignant(Enseignant enseignant) {
        String motDePass = passwordEncoder.encode(enseignant.getMdp());
        enseignant.setMdp(motDePass);
        return enseignantRepository.save(enseignant);
    }

    @Override
    public Secretaire creerSecretaire(Secretaire secretaire) {
        String motDePass = passwordEncoder.encode(secretaire.getMdp());
        secretaire.setMdp(motDePass);
        return secretaireRepository.save(secretaire);
    }

    @Override
    public President creerPresident(President president) {
        String motDePass = passwordEncoder.encode(president.getMdp());
        president.setMdp(motDePass);
        return presidentRepository.save(president);
    }

    @Override
    public Membre getMembre(Long numMembre) throws MembreNotFoundException {
        if(membreRepository.findById(numMembre).isPresent()){
            return membreRepository.findById(numMembre).get();
        } else {
            throw new MembreNotFoundException("ERREUR: Le membre spécifié n'existe pas.");
        }
    }

    @Override
    public Iterable<Membre> listerMembres() {
        return membreRepository.findAll();
    }

}
