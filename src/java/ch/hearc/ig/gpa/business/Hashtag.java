/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    
    
    public Hashtag(final Integer identifiant, final String libelle, final Message message) {
        this.libelle = libelle;
        this.message = message;
        this.identifiant = identifiant;
        twitters = new ArrayList<>();
    }

    public Hashtag() {
        this.libelle = null;
        this.message = null;
        this.identifiant = null;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getLibelle() {
        return libelle;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

    public boolean isNull() {
        return this.libelle == null && this.message == null && this.identifiant == null;
    }

}
