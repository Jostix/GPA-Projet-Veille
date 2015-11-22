/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public User(final Integer identifiant, final String nom, final String prenom, final Date dateNaissance, final String pays, final String username_twitter) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.pays = pays;
        this.username_twitter = username_twitter;
    }

    public User() {
        this.identifiant = null;
        this.nom = null;
        this.prenom = null;
        this.dateNaissance = null;
        this.pays = null;
        this.username_twitter = null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(final Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(final String pays) {
        this.pays = pays;
    }

    public String getUsername_twitter() {
        return username_twitter;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(final Integer identifiant) {
        this.identifiant = identifiant;
    }

    public void setUsername_twitter(final String username_twitter) {
        this.username_twitter = username_twitter;
    }

    public boolean isNull() {
        return this.identifiant == null && this.nom == null && this.prenom == null && this.dateNaissance == null && this.pays == null && this.username_twitter == null;
    }
}
