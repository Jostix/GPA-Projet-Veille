/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.twitter;

import ch.hearc.ig.gpa.business.Twitter;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;
import twitter4j.TwitterFactory;

/**
 *
 * @author Julien Bron <julien.bron@he-arc.ch>
 */
public class RecuperationTwitter {
    Twitter twitter;
    
    public RecuperationTwitter() {
        Twitter twitter = (Twitter) TwitterFactory.getSingleton();
        System.out.println(twitter.getRetweet());
    }

    public void recuperationPosts(){

    }
    
}
