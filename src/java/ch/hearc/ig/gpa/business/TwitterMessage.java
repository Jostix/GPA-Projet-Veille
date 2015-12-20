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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class TwitterMessage extends Message {
    
    private int identifiantTwi;
    private Integer retweet;
    private List<Hashtag> hashtags;

    /**
     *  Constructeur
     */
    public TwitterMessage() {
        super(null, null, null, null,null);
    }

    /**
     * Constructeur
     * 
     * @param identifiantTwi
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume
     * @param retweet 
     */
    public TwitterMessage(final int identifiantTwi,final String message,final Date date_heure_publication,final Date date_heure_recup,final String resume,final int retweet) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Constant.TWITTER.toString());
        this.identifiantTwi = identifiantTwi;
        this.retweet = retweet;
        hashtags = new ArrayList<>();
    }
    
    /**
     * Constructeur g�n�r� automatiquement pour respecter l'h�ritage...
     * 
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume 
     */
    public TwitterMessage(final String message,final Date date_heure_publication,final Date date_heure_recup,final String resume,final Integer retweet) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Constant.TWITTER.toString());
        this.retweet = retweet;
        hashtags = new ArrayList<>();
    }

    /**
     * Retourne l'identifiant
     * 
     * @return 
     */
    public int getIdentifiantTwi() {
        return identifiantTwi;
    }

    /**
     * Met � jour l'identifiant
     * 
     * @param identifiantTwi 
     */
    public void setIdentifiantTwi(int identifiantTwi) {
        this.identifiantTwi = identifiantTwi;
    }

    /**
     * Retourne les retweet
     * 
     * @return 
     */
    public Integer getRetweet() {
        return retweet;
    }

    /**
     * Met � jour les retweet
     * 
     * @param retweet 
     */
    public void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }
}
