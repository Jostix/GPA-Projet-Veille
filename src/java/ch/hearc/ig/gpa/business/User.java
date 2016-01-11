/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.business;

import java.sql.Date;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class User {

    private Integer identifiant;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String pays;
    private String username_twitter;

    /**
     * Constructeur
     * 
     * @param identifiant
     * @param nom
     * @param prenom
     * @param dateNaissance
     * @param pays
     * @param username_twitter 
     */
    
    public User(final Integer identifiant, final String nom, final String prenom, final Date dateNaissance, final String pays, final String username_twitter) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pays = pays;
        this.username_twitter = username_twitter;
    }

    /**
     * Constructeur
     */
    public User() {
        this.identifiant = null;
        this.nom = null;
        this.prenom = null;
        this.dateNaissance = null;
        this.pays = null;
        this.username_twitter = null;
    }
    
    public User(String username) {
        
        this.username_twitter = username;
    }

    /**
     * Retourne le nom
     * 
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met à jour le nom
     * 
     * @param nom 
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom
     * 
     * @return 
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Met à jour le prénom
     * 
     * @param prenom 
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la date de naissance
     * 
     * @return 
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Met à jour la date de naissance
     * 
     * @param dateNaissance 
     */
    public void setDateNaissance(final Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne le pays
     * 
     * @return 
     */
    public String getPays() {
        return pays;
    }

    /**
     * Met à jour le pays
     * 
     * @param pays 
     */
    public void setPays(final String pays) {
        this.pays = pays;
    }

    /**
     * Retourne le nom de l'utilisateur twitter
     * 
     * @return 
     */
    public String getUsername_twitter() {
        return username_twitter;
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
    public void setIdentifiant(final Integer identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * Met à jour le nom d'utilisateur twitter
     * 
     * @param username_twitter 
     */
    public void setUsername_twitter(final String username_twitter) {
        this.username_twitter = username_twitter;
    }

    /**
     * Vérifie si tout les attributs de l'objet sont nuls
     * 
     * @return 
     */
    public boolean isNull() {
        return this.identifiant == null && this.nom == null && this.prenom == null && this.dateNaissance == null && this.pays == null && this.username_twitter == null;
    }
}
