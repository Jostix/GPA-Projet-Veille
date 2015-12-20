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

    public TwitterMessage() {
        super(null, null, null, null,null);
    }

    public TwitterMessage(int identifiantTwi, String message, Date date_heure_publication, Date date_heure_recup, String resume, int retweet) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Constant.TWITTER.toString());
        this.identifiantTwi = identifiantTwi;
        this.retweet = retweet;
        hashtags = new ArrayList<>();
    }
    
    /**
     * Constructeur généré automatiquement pour respecter l'héritage...
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume 
     */
    
    
    
    public TwitterMessage(String message, Date date_heure_publication, Date date_heure_recup, String resume, Integer retweet) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Constant.TWITTER.toString());
        this.retweet = retweet;
        hashtags = new ArrayList<>();
    }

    public int getIdentifiantTwi() {
        return identifiantTwi;
    }

    public void setIdentifiantTwi(int identifiantTwi) {
        this.identifiantTwi = identifiantTwi;
    }

    public Integer getRetweet() {
        return retweet;
    }

    public void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }
}
