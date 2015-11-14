/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.business;

import java.sql.Date;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class Twitter extends Message {
    /**
     * Constructeur généré automatiquement pour respecter l'héritage...
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume 
     */
    public Twitter(String message, Date date_heure_publication, Date date_heure_recup, String resume) {
        super(message, date_heure_publication, date_heure_recup, resume);
    }

    
}
