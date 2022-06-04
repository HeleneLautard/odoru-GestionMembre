package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.repositories;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Membre;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
public interface MembreRepository extends MembreBaseRepository<Membre>{
}
