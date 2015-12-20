/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.business;

import java.sql.Blob;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class Image {

    private Integer identifiant;
    private String nom;
    private String type;
    private Blob image;
    private Message message;

    /**
     * Contructeur
     * 
     * @param identifiant
     * @param nom
     * @param type
     * @param image
     * @param message 
     */
    public Image(final Integer identifiant,final String nom,final String type,final Blob image,final Message message) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.message = message;
    }

    /**
     * Constructeur
     */
    public Image() {
        this.identifiant = null;
        this.nom = null;
        this.type = null;
        this.image = null;
        this.message = null;
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
     * Retourne le nom de l'image
     * 
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met à jour de le nom de l'image
     * 
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le type
     * 
     * @return 
     */
    public String getType() {
        return type;
    }

    /**
     * Met à jour le type
     * 
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retourne le blob (l'image)
     * 
     * @return 
     */
    public Blob getImage() {
        return image;
    }

    /**
     * Met à jour le blob (l'image)
     * 
     * @param image 
     */
    public void setImage(Blob image) {
        this.image = image;
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
     * Véfifie si tout les attributs sont nuls
     * 
     * @return 
     */
    public boolean isNull() {
        return this.identifiant == null && this.nom == null && this.type == null && this.image == null && this.message == null;
    }
}
