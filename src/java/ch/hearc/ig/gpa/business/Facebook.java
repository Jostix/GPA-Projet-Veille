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
public class Facebook extends Message {

    public String localisation;

    public Facebook(String localisation, String message, Date date_heure_publication, Date date_heure_recup, String resume) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constant.MessageCategorie.FACEBOOK);
        this.localisation = localisation;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

}
