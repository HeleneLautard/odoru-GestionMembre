package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.repositories;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.Membre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MembreBaseRepository<T extends Membre> extends CrudRepository<T, Long> {
}
