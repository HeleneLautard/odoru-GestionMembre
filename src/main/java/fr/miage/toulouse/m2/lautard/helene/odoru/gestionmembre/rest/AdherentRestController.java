package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.rest;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Adherent;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.exceptions.MembreNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services.GestionMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/membres/adherents")
public class AdherentRestController {


    @Autowired
    GestionMembre gestionMembre;

    /**
     * Création d'un nouvel adhérent
     * @param adherent Adherent à créer
     * @return Adherent
     */
    @PostMapping(path = "")
    public Adherent postAdherent(@RequestBody Adherent adherent) {
        return this.gestionMembre.creerAdherent(adherent);
    }


    /**
     * Lister l'ensemble des adherents
     * @return liste de adherents
     */
    @GetMapping(path = "")
    public Iterable<Adherent> getAdherents() {
        return this.gestionMembre.listerAdherent();
    }

    /**
     * Récupérer les informations d'un adherent
     * @param num_adherent numéro d'identifiaction de l'adherent
     * @return les informations relatives à l'adherent
     */
    @GetMapping(path = "/{id}")
    public Adherent getAdherent(@PathVariable("id") Long num_adherent) {
        try {
            return (Adherent) this.gestionMembre.getMembre(num_adherent);
        } catch (MembreNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    // TO DO : Gestion des rôles (uniquement secrétaire)
    @PutMapping(path = "/{id}")
    public Optional<Adherent> updateAdherent(@PathVariable("id") Long id, @RequestBody final Adherent newAdherent) {
        try {
            return this.gestionMembre.updateAdherent(id, newAdherent);
        } catch (MembreNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/niveau/{niveau}")
    public Iterable<Adherent> getAdherentByNiveau(@PathVariable("niveau") int niveau){
        return this.gestionMembre.getAdherentByNiveau(niveau);
    }
}
