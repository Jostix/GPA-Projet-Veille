/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.twitter;

import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.dao.MessageDAOImpl;
import ch.hearc.ig.gpa.dbfactory.OracleDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.*;
import twitter4j.TwitterFactory;

/**
 *
 * @author Julien Bron <julien.bron@he-arc.ch>
 */
public class RecuperationTwitter {
    Twitter twitter;
    
    public RecuperationTwitter() {
        Twitter twitter = (Twitter) TwitterFactory.getSingleton();
    }

    public void recuperationListPosts(User utilisateur) throws TwitterException, ConnectionProblemException{
        Message messageTwitter = null;
        List<Status> statuses = this.twitter.getUserTimeline(utilisateur.getUsername_twitter());
        for (Status status : statuses) {
            //setMessage
            messageTwitter.setMessage(status.getText());
            messageTwitter.setDate_heure_publication((Date) status.getCreatedAt());
            messageTwitter.setDate_heure_recup((Date) new java.util.Date());
            messageTwitter.setResume(status.getText().substring(0, 30));
        
            //AddMessage
            MessageDAOImpl messageDAO = new MessageDAOImpl();
            messageDAO.addMessage(messageTwitter, utilisateur.getIdentifiant());
            
            
            
        }
    }
    
    public void recuperationListPosts(String usernameTwitter) throws ConnectionProblemException, TwitterException, CommitException{
        
        Twitter twitter = (Twitter) TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getUserTimeline(usernameTwitter);
        for (Status status : statuses) {
            
            //Affichage status pour vérif
            System.out.println(status.getUser().getName() + ":"
                    + status.getText() + status.getRetweetCount());
            
            //setMessage
            System.out.println(status.getText());
            System.out.println("---");
                    
            Message message = new Message(status.getText(), new java.sql.Date(status.getCreatedAt().getTime()), new java.sql.Date(100000), status.getText().substring(0, 10));
            
//            //Persistence
//            OracleDAOFactory factory = OracleDAOFactory.getInstance();
//            factory.getConnection();
//            
//            MessageDAOImpl messageDAO = factory.getMessageDAO();
//            try {
//                messageDAO.addMessage(message, 1);
//            } catch (ConnectionProblemException ex) {
//                Logger.getLogger(RecuperationTwitter.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
            
            
        }
    }
    
}
