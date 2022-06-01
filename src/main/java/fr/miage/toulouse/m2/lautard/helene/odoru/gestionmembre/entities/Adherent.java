package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString(callSuper = true)
@DiscriminatorValue("ADHERENT")
public class Adherent extends Membre{
}
