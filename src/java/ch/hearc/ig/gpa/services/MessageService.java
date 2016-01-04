/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.services;

import ch.hearc.ig.gpa.RSS.RecuperationRSS;
import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Image;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.FeedNotFoundException;
import ch.hearc.ig.gpa.exceptions.RollbackException;
import ch.hearc.ig.gpa.log.MyLogger;
import ch.hearc.ig.gpa.twitter.RecuperationTwitter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import twitter4j.TwitterException;

/**
 * Ensemble de services appelé par les servlets pour l'affichage
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MessageService {

    /**
     * Retourne une liste de tous les messages
     *
     * @return
     * @throws ConnectionProblemException
     */
    public static List<Message> findAllMessage() throws ConnectionProblemException {
        List<Message> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getMessageDAO().getAllMessage();
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    /**
     * Retourne une liste du top 5 des messages
     *
     * @return
     * @throws ConnectionProblemException
     */
    public static List<Message> findTop5Message() throws ConnectionProblemException {
        List<Message> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getMessageDAO().getTop5Message();
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    /**
     * Ajout d'un message facebook
     *
     * @param message
     * @param img
     * @return
     * @throws ConnectionProblemException
     */
    public static Message addFacebookMessage(Message message, Image img) throws ConnectionProblemException {
        Message newMessage = null;
        try {
            newMessage = AbstractDAOFactory.getDAOFactory().getMessageDAO().addFacebookMessage(newMessage);
            AbstractDAOFactory.getDAOFactory().commit();
            img.setMessage(message);

        } catch (ConnectionProblemException ex) {
            try {
                AbstractDAOFactory.getDAOFactory().rollback();
            } catch (RollbackException ex1) {
                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
            }
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
            throw ex;
        } catch (CommitException ex) {
            try {
                AbstractDAOFactory.getDAOFactory().rollback();
            } catch (RollbackException ex1) {
                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
            }
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
            throw new ConnectionProblemException("A problem appeared while inserting the new message facebook in the database");
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }

        return newMessage;
    }

    /**
     * Ajout d'un message twitter
     *
     * @param message
     * @param img
     * @param hash
     * @return
     * @throws ConnectionProblemException
     */
    public static Message addTwitterMessage(Message message, Image img, Hashtag hash) throws ConnectionProblemException {
        Message newMessage = null;
        try {
            newMessage = AbstractDAOFactory.getDAOFactory().getMessageDAO().addTwitterMessage(message);
            AbstractDAOFactory.getDAOFactory().commit();
            img.setMessage(message);
            hash.setMessage(message);

        } catch (ConnectionProblemException ex) {
            try {
                AbstractDAOFactory.getDAOFactory().rollback();
            } catch (RollbackException ex1) {
                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
            }
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
            throw ex;
        } catch (CommitException ex) {
            try {
                AbstractDAOFactory.getDAOFactory().rollback();
            } catch (RollbackException ex1) {
                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
            }
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
            throw new ConnectionProblemException("A problem appeared while inserting the new message twitter in the database");
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }

        return newMessage;
    }

    /**
     * Met à jour les données de la base de donnée
     */
    public static void UpdateAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode permettant de récupérer les flux RSS et de les insérer dans la base de données
     * @throws FeedNotFoundException
     * @throws ConnectionProblemException 
     */
    public static void recupMessagesRSS() throws FeedNotFoundException, ConnectionProblemException {
        try {
            new RecuperationRSS().getRSS();
        } catch (XMLStreamException ex) {
            throw new FeedNotFoundException("Il y a eu un problème lors de la récuperation des flux RSS");
        }
    }


//Méthode test?
private static void recupMessagesTwitter(String username) {

        RecuperationTwitter recup = new RecuperationTwitter();

        try {

//            AbstractDAOFactory.getDAOFactory().getMessageDAO().deleteAllMessage();
//            AbstractDAOFactory.getDAOFactory().getHashtagDAO().deleteAllHashtag();
//            Set<User> users = AbstractDAOFactory.getDAOFactory().getUserDAO().researchAll();
            recup.recuperationListPosts(username);
//            for (User user : users) {
//                recup.recuperationListPosts(user.getUsername_twitter());
//            }

        

} catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class  

.getName()).log(Level.SEVERE, null, ex);
        } 

catch (TwitterException ex) {
            Logger.getLogger(MessageService.class  

.getName()).log(Level.SEVERE, null, ex);
        } 

catch (CommitException ex) {
            Logger.getLogger(MessageService.class  

.getName()).log(Level.SEVERE, null, ex);
        } 

catch (SQLException ex) {
            Logger.getLogger(MessageService.class  

.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
