package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.rest;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Membre;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.exceptions.MembreNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services.GestionMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/membres")
public class MembreRestController {

    @Autowired
    GestionMembre gestionMembre;

    /**
     * Récupérer la liste de tous les membres
     * @return Liste des membres
     */
    @GetMapping("")
    public Iterable getMembres(){
        return this.gestionMembre.listerMembres();
    }

    /**
     * Récupérer l'information d'un membre spécifique
     * @param num_membre numéro d'identification du membre
     * @return informations sur le membre
     */
    @GetMapping(path = "/{id}")
    public Membre getMembre(@PathVariable("id") Long num_membre){
        try {
            return this.gestionMembre.getMembre(num_membre);
        } catch (MembreNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    // TO DO : Gestion des autorisations pour le secrétaire
    /**
     * Vérifier l'état d'inscription d'un membre
     * @param num_membre
     * @return
     */
    @GetMapping(path = "/{id}/inscription")
    public String getStatutInscription(@PathVariable("id") Long num_membre){
        try {
            return this.gestionMembre.getStatutInscription(num_membre);
        } catch (MembreNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    // TO DO : Gestion de l'autorisation pour secrétaire

    /**
     * Mettre à jour le statut d'un membre
     * @param num_membre
     * @param newStatut
     * @return
     */
    @PutMapping(path = "/{id}")
    public Membre updateMembre(@PathVariable("id") Long num_membre, @RequestParam("statut") String newStatut){
        try {
            return this.gestionMembre.updateStatut(num_membre, newStatut);
        } catch (MembreNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
