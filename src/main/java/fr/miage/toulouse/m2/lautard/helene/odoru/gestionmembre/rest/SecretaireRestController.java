package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.rest;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Secretaire;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services.GestionMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membres/secretaires")
public class SecretaireRestController {

    @Autowired
    GestionMembre gestionMembre;

    @PostMapping(path = "")
    public Secretaire postSecretaire(@RequestBody Secretaire secretaire) {
        return this.gestionMembre.creerSecretaire(secretaire);
    }
}
