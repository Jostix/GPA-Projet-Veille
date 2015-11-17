/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public Image(Integer identifiant, String nom, String type, Blob image, Message message) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.message = message;
    }

    public Image() {
        this.identifiant = null;
        this.nom = null;
        this.type = null;
        this.image = null;
        this.message = null;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isNull() {
        return this.identifiant == null && this.nom == null && this.type == null && this.image == null && this.message == null;
    }
}
