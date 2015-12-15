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
public class Message {
    private int identifiant;
    protected String message;
    protected Date date_heure_publication;
    protected Date date_heure_recup;
    protected String resume;

    public Message(int identifiant, String message, Date date_heure_publication, Date date_heure_recup, String resume) {
        this.identifiant = identifiant;
        this.message = message;
        this.date_heure_publication = date_heure_publication;
        this.date_heure_recup = date_heure_recup;
        this.resume = resume;
    }

    public Message(String message, Date date_heure_publication, Date date_heure_recup, String resume) {
        this.message = message;
        this.date_heure_publication = date_heure_publication;
        this.date_heure_recup = date_heure_recup;
        this.resume = resume;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(int numero) {
        this.identifiant = numero;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public Date getDate_heure_publication() {
        return date_heure_publication;
    }

    public void setDate_heure_publication(final Date date_heure_publication) {
        this.date_heure_publication = date_heure_publication;
    }

    public Date getDate_heure_recup() {
        return date_heure_recup;
    }

    public void setDate_heure_recup(final Date date_heure_recup) {
        this.date_heure_recup = date_heure_recup;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(final String resume) {
        this.resume = resume;
    }
}
