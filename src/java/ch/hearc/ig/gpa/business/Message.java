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
    protected String sourceType;

    /**
     * Retourne la source de données
     * 
     * @return 
     */
    public String getSourceType() {
        return sourceType;
    }

    /**
     * Met à jour la source de données
     * 
     * @param sourceType 
     */
    public void setSourceType(final String sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * Constructeur
     * 
     * @param identifiant
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume
     * @param sourceType 
     */
    public Message(final int identifiant, final String message,final Date date_heure_publication,final Date date_heure_recup,final String resume, final String sourceType) {
        this(message, date_heure_publication, date_heure_recup, resume, sourceType);
        this.identifiant = identifiant;
    }

    /**
     * Constructeur
     * 
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume
     * @param sourceType 
     */
    public Message(final String message, final Date date_heure_publication, final Date date_heure_recup, final String resume, final String sourceType) {
        this.message = message;
        this.date_heure_publication = date_heure_publication;
        this.date_heure_recup = date_heure_recup;
        this.resume = resume;
        this.sourceType = sourceType;
    }

    /**
     * Retourne l'identifiant
     * 
     * @return 
     */
    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Met à jour l'identifiant
     * 
     * @param numero 
     */
    public void setIdentifiant(final int numero) {
        this.identifiant = numero;
    }

    /**
     * Retourne le message
     * 
     * @return 
     */
    public String getMessage() {
        return message;
    }

    /**
     * Met à jour le message
     * 
     * @param message 
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Retourne la date de publication
     * 
     * @return 
     */
    public Date getDate_heure_publication() {
        return date_heure_publication;
    }

    /**
     * Met à jour la date de publication
     * 
     * @param date_heure_publication 
     */
    public void setDate_heure_publication(final Date date_heure_publication) {
        this.date_heure_publication = date_heure_publication;
    }

    
    /**
     * REtourne la date de récupèration
     * 
     * @return 
     */
    public Date getDate_heure_recup() {
        return date_heure_recup;
    }

    /**
     * Met à jour la date de récupèration
     * 
     * @param date_heure_recup 
     */
    public void setDate_heure_recup(final Date date_heure_recup) {
        this.date_heure_recup = date_heure_recup;
    }

    /**
     * Retourne le résumé
     * 
     * @return 
     */
    public String getResume() {
        return resume;
    }

    /**
     * Met à jour le résumé
     * 
     * @param resume 
     */
    public void setResume(final String resume) {
        this.resume = resume;
    }
}
