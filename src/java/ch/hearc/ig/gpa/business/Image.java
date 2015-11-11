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
    private Integer msg_numero;

    public Image(Integer identifiant, String nom, String type, Blob image, Integer msg_numero) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.msg_numero = msg_numero;
    }

    public Image() {
        this.identifiant = null;
        this.nom = null;
        this.type = null;
        this.image = null;
        this.msg_numero = null;
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

    public Integer getMsg_numero() {
        return msg_numero;
    }

    public void setMsg_numero(Integer msg_numero) {
        this.msg_numero = msg_numero;
    }

    public boolean isNull() {
        return this.identifiant == null && this.nom == null && this.type == null && this.image == null && this.msg_numero == null;
    }
}
