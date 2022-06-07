package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.rest;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Enseignant;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.exceptions.MembreNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.repositories.EnseignantRepository;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services.GestionMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/membres/enseignants")
public class EnseignantRestController {

    @Autowired
    GestionMembre gestionMembre;

    @Autowired
    EnseignantRepository enseignantRepository;

   // TO DO : Gestion rôle SECRETAIRE

    /**
     * Création d'un enseignant
     * @param enseignant informations de l'enseignant
     * @return enseignant
     */
    @PostMapping(path = "")
    public Enseignant postEnseignant(@RequestBody Enseignant enseignant) {
        return this.gestionMembre.creerEnseignant(enseignant);
    }

    /**
     * Récupérer la liste de tous les enseignants
     * @return Ensemble des enseignants
     */
    @GetMapping(path = "")
    public Iterable<Enseignant> getListEnseignants() {
        return this.gestionMembre.listerEnseignant();
    }

    /**
     * Récupérer les informations relatives à un enseignant
     * @param num_enseignant numéro d'identification de l'enseignant
     * @return Enseignant
     */
    @GetMapping(path = "/{id}")
    public Enseignant getEnseignant(@PathVariable("id") Long num_enseignant) {
        try {
            return (Enseignant) this.gestionMembre.getMembre(num_enseignant);
        } catch (MembreNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @PutMapping(path="/{id}")
    public Optional<Enseignant> updateEnseignant(@PathVariable("id") Long id, @RequestBody Enseignant newEnseignant){
        try{
            return this.gestionMembre.updateEnseignant(id, newEnseignant);
        } catch (MembreNotFoundException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
