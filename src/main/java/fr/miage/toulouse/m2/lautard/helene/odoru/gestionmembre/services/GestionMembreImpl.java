package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.*;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.exceptions.MembreNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.repositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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
    @PersistenceContext
    EntityManager em;
    @Autowired
    private PlatformTransactionManager transactionManager;

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


    @Override
    public String getStatutInscription(Long num_membre) throws MembreNotFoundException {
        try{
            Membre m = this.getMembre(num_membre);
            return m.getStatutInscription().toString();

        } catch (MembreNotFoundException ex){
            throw new MembreNotFoundException("Le membre n'existe pas");
        }
    }

    @Override
    public Membre updateStatut(Long num_membre, String newStatut) throws MembreNotFoundException {
        try{
            Membre m = this.getMembre(num_membre);
            TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
            transactionTemplate.execute(transactionStatus -> {
                em.createQuery("UPDATE Membre SET statut = ?1 WHERE numMembre = ?2")
                        .setParameter(1, newStatut.toUpperCase())
                        .setParameter(2, num_membre)
                        .executeUpdate();
                transactionStatus.flush();
                return null;
            });
            return m;
        } catch(MembreNotFoundException e){
            throw new MembreNotFoundException("Le membre n'existe pas");
        }
    }

    @Override
    public Iterable<Adherent> listerAdherent() {
        return this.adherentRepository.findAll();
    }

    @Override
    public Optional<Adherent> updateAdherent(Long id, Adherent newAdherent) throws MembreNotFoundException {
       try {
           this.getMembre(id);
           return this.adherentRepository.findById(id)
                        .map(adherent -> {
                            if (newAdherent.getStatutInscription() != null) {
                                adherent.setStatutInscription(newAdherent.getStatutInscription());
                            }
                            if (newAdherent.getAptitudeMedicale() != null) {
                                adherent.setAptitudeMedicale(newAdherent.getAptitudeMedicale());
                            }
                            if (newAdherent.getStatutPaiement() != null) {
                                adherent.setStatutPaiement(newAdherent.getStatutPaiement());
                            }
                            if (newAdherent.getNiveau() != adherent.getNiveau() && (newAdherent.getNiveau() > 0 && newAdherent.getNiveau() <= 5)) {
                                adherent.setNiveau(newAdherent.getNiveau());
                            }
                            return this.adherentRepository.save(adherent);
                        });
            } catch (MembreNotFoundException exp) {
                throw new MembreNotFoundException("L'adhérent n'existe pas");
            }

    }

    @Override
    public Iterable<Enseignant> listerEnseignant() {
        return this.enseignantRepository.findAll();
    }

    @Override
    public Optional<Enseignant> updateEnseignant(Long id, Enseignant newEnseignant) throws MembreNotFoundException {
        try {
            this.getMembre(id);
            return this.enseignantRepository.findById(id)
                    .map(enseignant -> {

                        if (newEnseignant.getAptitudeMedicale() != null) {
                            enseignant.setAptitudeMedicale(newEnseignant.getAptitudeMedicale());
                        }
                        if (newEnseignant.getStatutPaiement() != null) {
                            enseignant.setStatutPaiement(newEnseignant.getStatutPaiement());
                        }
                        if (newEnseignant.getStatutInscription() != null) {
                            enseignant.setStatutInscription(newEnseignant.getStatutInscription());
                        }
                        if (enseignant.getNiveau() != newEnseignant.getNiveau() && (newEnseignant.getNiveau() > 0 && newEnseignant.getNiveau() <= 5)) {
                            enseignant.setNiveau(newEnseignant.getNiveau());
                        }
                        return this.enseignantRepository.save(enseignant);
                    });
        } catch (MembreNotFoundException exp) {
            throw new MembreNotFoundException("L'enseignant n'existe pas");
        }
    }

    @Override
    public Iterable<Adherent> getAdherentByNiveau(int niveau) {
        return this.adherentRepository.getAdherentsByNiveau(niveau);
    }

}
