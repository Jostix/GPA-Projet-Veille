/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.services;

import ch.hearc.ig.gpa.RSS.RecuperationRSS;
import ch.hearc.ig.gpa.business.Facebook;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.constants.Constants;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.FeedNotFoundException;
import ch.hearc.ig.gpa.log.MyLogger;
import ch.hearc.ig.gpa.twitter.RecuperationTwitter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import twitter4j.TwitterException;

/**
 * Ensemble de services appel� par les servlets pour l'affichage
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MessageService {

    /**
     * Retourne une liste des messages en fonction de ce qui est demand� en
     * param�tre - getTop5 limite la recherche au top5, getMessageTechno limite
     * la recherche � la veille technologique, getMessageProtection limite la
     * recherche � la veille protection
     *
     * @param getTop5
     * @param getMessageTechno
     * @param getMessageProtection
     * @return
     * @throws ConnectionProblemException
     */
    public static List<Message> findAllMessage(final Boolean getTop5, final Boolean getMessageTechno, final Boolean getMessageProtection) throws ConnectionProblemException {
        //Initialisation des listes de messages
        List<Message> messages = new ArrayList<>(); // Stocke les messages FB
        List<Facebook> facebookMessages = new ArrayList<>(); // Stocke les messages FB
        List<TwitterMessage> twitterMessages = new ArrayList<>(); // Stocke les messages Twitter
        List<RSS> rssMessages = new ArrayList<>(); // Stocke les messages RSS

        try {
            //R�cup�ration des messages Twitter
            if (getMessageProtection) {

                //Limite la recherche au top 5
                if (getTop5) {
                    twitterMessages = AbstractDAOFactory.getDAOFactory().getTwitterDao().getTopRetweeted(5);
                } else {
                    twitterMessages = findAllTwitterMessages();
                }

                //Concat�nation messages twitter
                for (TwitterMessage twitterMessage : twitterMessages) {
                    messages.add(twitterMessage);
                }

            }

            //R�cup�ration des messages RSS
            if (getMessageTechno) {

                //Limite la recherche au top 5
                if (getTop5) {
                    rssMessages = AbstractDAOFactory.getDAOFactory().getRSSDaoImpl().getTopRss(5);
                } else {
                    rssMessages = findAllRSS();
                }

                //messages RSS
                for (RSS rssMessage : rssMessages) {
                    messages.add(rssMessage);
                }

            }

            //Tri des donn�es sur le champ date
            Collections.sort(messages, new Comparator<Message>() {
                @Override
                public int compare(Message message1, Message message2) {
                    return message1.getDate_heure_publication().compareTo(message2.getDate_heure_publication());
                }
            });

            //Trie descendant des messages par leur date.
            Collections.reverse(messages);

        } finally {
            //Fermeture de la connexion
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return messages;
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
     * Retourne la liste de tous les messages Twitter
     *
     * @return
     */
    public static List<TwitterMessage> findAllTwitterMessages() {
        List<TwitterMessage> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getTwitterDao().getAllTwitterMessages();
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Retourne la liste de tous les messages RSS
     *
     * @return
     */
    public static List<RSS> findAllRSS() {
        List<RSS> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getRSSDaoImpl().getRSSMessages();
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    /**
     * Service appel� lors d'un clic sur le bouton d'actualisation. Il supprime
     * toutes les donn�es dans les tables et en rajoute des fraiches
     *
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
            AbstractDAOFactory.getDAOFactory().closeConnection();
            System.out.println("Update completed successfully");
        } catch (FeedNotFoundException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Service permettant de r�cup�rer les flux RSS et de les ins�rer dans la
     * base de donn�es
     *
     * @throws FeedNotFoundException
     * @throws ConnectionProblemException
     */
    private static void recupMessagesRSS() throws FeedNotFoundException, ConnectionProblemException {
        try {
            for (String feed : Constants.RSSFEEDS) {
                new RecuperationRSS().getRSS(feed);
            }
        } catch (XMLStreamException ex) {
            throw new FeedNotFoundException("Il y a eu un probl�me lors de la r�cuperation des flux RSS");
        } catch (ParseException ex) {
            Logger.getLogger(MessageService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ce service permet de r�cup�rer tous les messages sur twitter et de les
     * ins�rer dans la base de donn�es
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
