package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.rest;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.President;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services.GestionMembre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membres/presidents")
public class PresidentRestController {


    @Autowired
    GestionMembre gestionMembre;


    @PostMapping(path = "")
    public President postPresident(@RequestBody President president) {
        return this.gestionMembre.creerPresident(president);
    }

}
