package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "statut", discriminatorType = DiscriminatorType.STRING)
public class Membre {

    @Id
    @GeneratedValue
    private Long numMembre;

    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String mail;
    @NotNull
    private String mdp;
    @NotNull
    private String villeRes;
    @NotNull
    private String paysRes;
    @NotNull
    private int niveau;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatutPaiement statutPaiement;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatutInscription statutInscription;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AptitudeMedicale aptitudeMedicale;
}

