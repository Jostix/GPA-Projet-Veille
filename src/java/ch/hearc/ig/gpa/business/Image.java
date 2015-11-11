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
    private String nom;
    private String type;
    private Blob image;

    public Image(String nom, String type, Blob image) {
        this.nom = nom;
        this.type = type;
        this.image = image;
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
}
