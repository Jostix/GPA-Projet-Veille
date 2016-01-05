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
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.FeedNotFoundException;
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
    
    public static List<TwitterMessage> findAllTwitterMessages(){
        List<TwitterMessage> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getTwitterDao().getAllTwitterMessages();
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static List<RSS> findAllRSS(){
        List<RSS> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getRSSDaoImpl().getRSSMessages();
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Service appelé lors d'un clic sur le bouton d'actualisation. Il
     * supprime toutes les données dans les tables et en rajoute des fraiches
     * @throws CommitException 
     */
    public static void UpdateAll() throws CommitException {

        try {
            AbstractDAOFactory.getDAOFactory().getRSSDaoImpl().deleteAllRSS();
            AbstractDAOFactory.getDAOFactory().getTwitterDao().deleteAllMessage();
            AbstractDAOFactory.getDAOFactory().getMessageDAO().deleteAllMessage();
            recupMessagesRSS();
            recupMessagesTwitter();
            AbstractDAOFactory.getDAOFactory().commit();
        } catch (FeedNotFoundException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Service permettant de récupérer les flux RSS et de les insérer dans la
     * base de données
     *
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

    /**
     * Ce service permet de récupérer tous les messages sur twitter et de les insérer dans la base de données
     */
    private static void recupMessagesTwitter() {

        try {
            RecuperationTwitter recup = new RecuperationTwitter();
            for (User user : AbstractDAOFactory.getDAOFactory().getUserDAOImpl().researchAll()) {
                recup.recuperationListPosts(user.getUsername_twitter());
            }
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TwitterException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CommitException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
