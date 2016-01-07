/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.twitter;

import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.constants.Constants;
import ch.hearc.ig.gpa.dao.HashtagDAOImpl;
import ch.hearc.ig.gpa.dao.MessageDAOImpl;
import ch.hearc.ig.gpa.dao.RelationTwiHashDAO;
import ch.hearc.ig.gpa.dao.TwitterDao;
import ch.hearc.ig.gpa.dao.UserDAOImpl;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.dbfactory.OracleDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.services.StringServices;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import twitter4j.*;
import twitter4j.TwitterFactory;

/**
 *
 * @author Julien Bron <julien.bron@he-arc.ch>
 */
public class RecuperationTwitter {

    private static final int RESUMELIMIT = 60;
    Twitter twitter;

    public RecuperationTwitter() {
        Twitter twitter = (Twitter) TwitterFactory.getSingleton();
    }

//    public void recuperationListPosts(User utilisateur) throws TwitterException, ConnectionProblemException{
//        Message messageTwitter = null;
//        List<Status> statuses = this.twitter.getUserTimeline(utilisateur.getUsername_twitter());
//        for (Status status : statuses) {
//            //setMessage
//            messageTwitter.setMessage(status.getText());
//            messageTwitter.setDate_heure_publication((Date) status.getCreatedAt());
//            messageTwitter.setDate_heure_recup((Date) new java.util.Date());
//            messageTwitter.setResume(status.getText().substring(0, 30));
//        
//            //AddMessage
//            MessageDAOImpl messageDAO = new MessageDAOImpl();
//            //messageDAO.addMessage(messageTwitter, utilisateur.getIdentifiant());
//            
//            
//            
//        }
//    }
    public void recuperationListPosts(String usernameTwitter) throws ConnectionProblemException, TwitterException, CommitException, SQLException {

        Twitter twitter = (Twitter) TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getUserTimeline(usernameTwitter);
        UserDAOImpl userDao = AbstractDAOFactory.getDAOFactory().getUserDAOImpl();
        MessageDAOImpl msgDao = AbstractDAOFactory.getDAOFactory().getMessageDAO();
        for (Status status : statuses) {
        
          
            TwitterMessage twitMessage = new TwitterMessage(status.getText(), new java.sql.Date(status.getCreatedAt().getTime()), new java.sql.Date(100000), StringServices.limit(status.getText(), RESUMELIMIT) + " [...]", status.getRetweetCount());

            if (StringServices.containsKey(twitMessage.getMessage(), Constants.KEYWORDS)) {
                msgDao.addMessageTwitter(twitMessage, userDao.getIdByUsername(usernameTwitter));
            }

//            //Persistence
//            OracleDAOFactory factory = OracleDAOFactory.getInstance();
//            factory.getConnection();
//            
//            MessageDAOImpl messageDAO = factory.getMessageDAO();
//           /* try {
//               messageDAO.addMessage(message, 1);
//            } catch (ConnectionProblemException ex) {
//                Logger.getLogger(RecuperationTwitter.class.getName()).log(Level.SEVERE, null, ex);
//            }*/
//            
//            TwitterDao twitterDao = factory.getTwitterDao();
//            twitMessage.setIdentifiantTwi(messageDAO.getMessage(message).getIdentifiant());
//            twitterDao.addTwitterMessage(twitMessage, messageDAO.getMessage(message).getIdentifiant());
//            System.out.println(twitMessage.getIdentifiantTwi());
//            
//            
//            
//            HashtagDAOImpl hashtagDAO = factory.getHashtagDAO();
//            RelationTwiHashDAO relationTwiHashDAO = factory.getRelationTwiHashDAO();
//            
//            HashtagEntity[] hashtags = status.getHashtagEntities();
//            for (HashtagEntity hashtag : hashtags) {
//                Hashtag hash = new Hashtag();
//                hash.setLibelle(hashtag.getText());
//                if (!hashtagDAO.exist(hash)) {
//                    hashtagDAO.insert(hash);
//                }
//                
//                hash = hashtagDAO.getHashtag(hash);
//                System.out.println("Hash:" + hash.getIdentifiant());
//                System.out.println("pk twi_pub:" + twitMessage.getIdentifiantTwi());
//                relationTwiHashDAO.insert(twitMessage, hash);
//            }
        }

    }

}
