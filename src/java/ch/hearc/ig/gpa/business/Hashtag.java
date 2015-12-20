/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class Hashtag {

    private Integer identifiant;
    private String libelle;
    private Message message;
    private List<TwitterMessage> twitters;

    /**
     * Constructeur
     * 
     * @param identifiant
     * @param libelle
     * @param message 
     */
    public Hashtag(final Integer identifiant, final String libelle, final Message message) {
        this.libelle = libelle;
        this.message = message;
        this.identifiant = identifiant;
        twitters = new ArrayList<>();
    }

    /**
     * Constructeur
     * 
     */
    public Hashtag() {
        this.libelle = null;
        this.message = null;
        this.identifiant = null;
    }

    /**
     * Retourne le message
     * 
     * @return 
     */
    public Message getMessage() {
        return message;
    }

    /**
     * Met à jour le message
     * 
     * @param message 
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Retourne le libelle
     * 
     * @return 
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Retourne l'identifiant
     * 
     * @return 
     */
    public Integer getIdentifiant() {
        return identifiant;
    }

    /**
     * Met à jour l'identifiant
     * 
     * @param identifiant 
     */
    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Met à jour le libelle
     * 
     * @param libelle 
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Véfifie si les attributs de l'objet sont tous null
     * 
     * @return 
     */
    public boolean isNull() {
        return this.libelle == null && this.message == null && this.identifiant == null;
    }

}
