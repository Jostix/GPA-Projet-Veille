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
    private int msg_numero;

    public Image(String nom, String type, Blob image, final int msg_numero) {
        this.nom = nom;
        this.type = type;
        this.image = image;
        this.msg_numero = msg_numero;
    }

    public String getNom() {
        return nom;
    }

    public int getMsg_numero() {
        return msg_numero;
    }

    public void setMsg_numero(final int msg_numero) {
        this.msg_numero = msg_numero;
    }

    public void setNom(final String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(final Blob image) {
        this.image = image;
    }
}
