package fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.services;

import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.entities.*;
import fr.miage.toulouse.m2.lautard.helene.odoru.gestionmembre.exceptions.MembreNotFoundException;


public interface GestionMembre {

    /**
     * Créer un membre de type adhérent
     * @param adherent
     * @return adherent
     */
    Adherent creerAdherent(Adherent adherent);

    /**
     * Créer un membre de type enseignant
     * @param enseignant
     * @return enseignant
     */
    Enseignant creerEnseignant(Enseignant enseignant);

    /**
     * Créer un membre de type secrétaire
     * @param secretaire
     * @return secrétaire
     */
    Secretaire creerSecretaire(Secretaire secretaire);

    /**
     * Créer un membre de type président
     * @param president
     * @return
     */
    President creerPresident(President president);

    /**
     * Recherche d'un membre selon son numéro
     * @param numMembre numéro du membre recherché
     * @return Membre correspondant
     * @throws MembreNotFoundException
     */
    Membre getMembre(Long numMembre) throws MembreNotFoundException;

    /**
     * Lister l'ensemble des membres du système
     * @return Liste des membres
     */
    Iterable<Membre> listerMembres();

    /**
     * Vérifier le statut d'inscription d'un membre
     * @param num_membre numéro d'identification
     * @return Etat de son inscription
     * @throws MembreNotFoundException
     */
    String getStatutInscription(Long num_membre) throws MembreNotFoundException;

    /**
     * Modifier le statut d'un membre
     * @param num_membre numéro d'identification du membre
     * @param newStatut nouveau statut
     * @return Membre mis à jour
     * @throws MembreNotFoundException
     */
    Membre updateStatut(Long num_membre, String newStatut) throws MembreNotFoundException;

}
