/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.business;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class Hashtag {

    private Integer identifiant;
    private String libelle;
    private Integer msg_numero;

    public Hashtag(final Integer identificant, final String libelle, final Integer msg_numero) {
        this.libelle = libelle;
        this.msg_numero = msg_numero;
        this.identifiant = identifiant;
    }

    public Hashtag() {
        this.libelle = null;
        this.msg_numero = null;
        this.identifiant = null;
    }

    public int getMsg_numero() {
        return msg_numero;
    }

    public void setMsg_numero(Integer msg_numero) {
        this.msg_numero = msg_numero;
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
        return this.libelle == null && this.msg_numero == null && this.identifiant == null;
    }

}
