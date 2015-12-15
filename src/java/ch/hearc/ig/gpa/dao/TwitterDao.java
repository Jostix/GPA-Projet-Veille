/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class TwitterDao extends MessageDAOImpl {

    public List<TwitterMessage> getAllTwitterMessages() throws ConnectionProblemException {
        List<TwitterMessage> twitterMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rsMessagesTwitter = null;

        String query = "SELECT retweet, msg_numero FROM twitter_publication";
        try {
            stmt = getConnection().prepareStatement(query);
            rsMessagesTwitter = stmt.executeQuery();
            Integer retweet;
            Message message;
            TwitterMessage twMessage;

            while (rsMessagesTwitter.next()) {

                retweet = rsMessagesTwitter.getInt("retweet");
                message = new MessageDAOImpl().getMessageById(rsMessagesTwitter.getInt("msg_numero"));
                twMessage = new TwitterMessage(message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume(), retweet);

                twitterMessages.add(twMessage);              
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading all twitter message", sqlE);
        } finally {
            closePStmtAndRS(stmt, rsMessagesTwitter);
        }
        return twitterMessages;
    }

    public void addTwitterMessage(TwitterMessage message, int messageNumber) {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        

        try {

            String query = "insert into twitter_publication(retweet, msg_numero) values (?,?)";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, message.getRetweet());
            pstmt.setInt(2, messageNumber);
            

            int count = pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closePStmtAndRS(pstmt, rs);
        }
    }

    public int getCurrentMsgSequenceValue() {
        int currentValue = -1;
        PreparedStatement stmt = null;
        ResultSet rsSequence = null;
        Connection c = null; //beurk

        String query = "SELECT seq_twipub.currval FROM DUAL";
        try {

            stmt = getConnection().prepareStatement(query);
            rsSequence = stmt.executeQuery();

            while (rsSequence.next()) {
                currentValue = rsSequence.getInt("seq_twipub.currval");
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return currentValue;
    }

    public List<TwitterMessage> getTopRetweeted(int nbMessages) throws ConnectionProblemException {
        List<TwitterMessage> twitterMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;

        String query = "SELECT retweet, msg_numero FROM (SELECT retweet, msg_numero FROM twitter_publication ORDER BY retweet desc) WHERE ROWNUM <=" + nbMessages;
        try {

            stmt = getConnection().prepareStatement(query);
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {
                Integer retweet = rsMessages.getInt("retweet");
                Message message = new MessageDAOImpl().getMessageById(rsMessages.getInt("msg_numero"));

                TwitterMessage twMessage = new TwitterMessage(message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume(), retweet);

                twitterMessages.add(twMessage);

            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared while getting MsgSequenceValue", sqlE);
        } finally {
            closePStmtAndRS(stmt, rsMessages);
        }
        return twitterMessages;
    }
    
    public TwitterMessage getTwitterMessage(TwitterMessage message) throws ConnectionProblemException {
        TwitterMessage twitterMessage = null;
        PreparedStatement stmt = null;
        ResultSet rsMessagesTwitter = null;

        String query = "SELECT numero, retweet, msg_numero FROM twitter_publication where retweet=? and msg_numero=?";
        try {
            stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, message.getRetweet());
            stmt.setInt(2, message.getIdentifiant());
            rsMessagesTwitter = stmt.executeQuery();

            while (rsMessagesTwitter.next()) {

                int numero = rsMessagesTwitter.getInt("numero");
                int retweet = rsMessagesTwitter.getInt("retweet");
                int msgNumero = rsMessagesTwitter.getInt("msg_numero");
                twitterMessage = new TwitterMessage(numero, message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume(), retweet);

            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading all twitter message", sqlE);
        } finally {
            closePStmtAndRS(stmt, rsMessagesTwitter);
        }
        return twitterMessage;
    }
}
