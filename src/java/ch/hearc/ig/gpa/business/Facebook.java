/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.business;

import java.sql.Date;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class Facebook extends Message {

    public String localisation;

    /**
     * Constructeur
     * 
     * @param localisation
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume 
     */
    public Facebook(String localisation, String message, Date date_heure_publication, Date date_heure_recup, String resume) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Categories.FACEBOOK.toString());
        this.localisation = localisation;
    }

    /**
     * Retourne la localisation
     * 
     * @return 
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Met � jour la localisation
     * 
     * @param localisation 
     */
    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

}
