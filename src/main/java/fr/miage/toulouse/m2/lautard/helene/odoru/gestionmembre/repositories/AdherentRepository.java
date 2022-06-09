package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.repositories;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Adherent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdherentRepository extends MembreBaseRepository<Adherent> {

    public List<Adherent> getAdherentByNiveau(int niveau);
}
