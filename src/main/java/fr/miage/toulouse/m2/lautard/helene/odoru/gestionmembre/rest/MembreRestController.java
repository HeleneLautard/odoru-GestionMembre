package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.rest;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services.GestionMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membres")
public class MembreRestController {

    @Autowired
    GestionMembre gestionMembre;

    @GetMapping("")
    public Iterable getMembres(){
        return this.gestionMembre.listerMembres();
    }
}
