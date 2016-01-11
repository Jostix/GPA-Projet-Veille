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
    private User autor = null;

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
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Categories.TWITTER.toString());
        this.identifiantTwi = identifiantTwi;
        this.retweet = retweet;
        hashtags = new ArrayList<>();
    }
    
    /**
     * Constructeur généré automatiquement pour respecter l'héritage...
     * 
     * @param message
     * @param date_heure_publication
     * @param date_heure_recup
     * @param resume 
     */
    public TwitterMessage(final String message,final Date date_heure_publication,final Date date_heure_recup,final String resume,final Integer retweet, final User autor) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Categories.TWITTER.toString());
        this.retweet = retweet;
        hashtags = new ArrayList<>();
        this.autor = autor;
    }
    
    public TwitterMessage(final String message,final Date date_heure_publication,final Date date_heure_recup,final String resume,final Integer retweet) {
        super(message, date_heure_publication, date_heure_recup, resume,ch.hearc.ig.gpa.constants.Categories.TWITTER.toString());
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
     * Met à jour l'identifiant
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
     * Met à jour les retweet
     * 
     * @param retweet 
     */
    public void setRetweet(Integer retweet) {
        this.retweet = retweet;
    }
    
    /**
     * Retourne les hashtags
     * 
     * @return 
     */
    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    /**
     * Met à jour les hashtags
     * 
     * @param hashtags 
     */
    public void setHashtags(final List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * Retourne l'utilisateur du compte tweeter
     * 
     * @return 
     */
    public User getUser() {
        return autor;
    }

    /**
     * Met à jour l'user du compte Tweeter
     * 
     * @param user 
     */
    public void setUser(final User user) {
        this.autor = user;
    }

    
}
