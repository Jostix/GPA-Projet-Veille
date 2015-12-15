/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.dao.interf.MessageDAO;
import ch.hearc.ig.gpa.business.Facebook;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MessageDAOImpl extends AbstractDAOOracle implements MessageDAO {

    @Override
    public void addMessage(Message message, int userNum) throws ConnectionProblemException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "insert into Message(user_numero, message, date_heure_publication, date_heure_recup, resume) values (?,?,?,?,?)";

            pstmt = getConnection().prepareStatement(query);
            pstmt.setInt(1, userNum);
            pstmt.setString(2, message.getMessage());
            pstmt.setDate(3, message.getDate_heure_publication());
            pstmt.setDate(4, message.getDate_heure_recup());
            pstmt.setString(5, message.getResume());

            int count = pstmt.executeUpdate();

            /**
             * Test del l'instance du message, si c'est un message facebook on
             * va appeler le FacebookDao pour ajouter une publication facebook,
             * si c'est un message Twitter on va bien entendu ajouter une
             * publication Twitter.
             */
//            if (message instanceof Facebook) {
//                new FacebookDao().addFbMessage((Facebook) message, newMessageNum);
//            }
//            if (message instanceof TwitterMessage) {
//                new TwitterDao().addTwitterMessage((TwitterMessage) message, newMessageNum);
//            }

        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with adding message", sqlE);
        } finally {
            closePStmtAndRS(pstmt, rs);
        }
    }

    @Override
    public int getCurrentMsgSequenceValue() throws ConnectionProblemException {
        int currentValue = -1;
        PreparedStatement stmt = null;
        ResultSet rsSequence = null;

        String query = "SELECT seq_msg.currval FROM DUAL";
        try {
            stmt = getConnection().prepareStatement(query);
            rsSequence = stmt.executeQuery();

            while (rsSequence.next()) {
                currentValue = rsSequence.getInt("seq_msg.currval");
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared while getting MsgSequenceValue", sqlE);
        } finally {
            closePStmtAndRS(stmt, rsSequence);
        }
        return currentValue;
    }

    @Override
    public Message getMessageById(int numero) throws ConnectionProblemException {
        Message m = null;
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;

        String query = "SELECT message, date_heure_publication, date_heure_recup, resume FROM message  WHERE numero =?";
        try {
            stmt = getConnection().prepareStatement(query);
            stmt.setInt(1, numero);
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {

                String message = rsMessages.getString("message");
                Date dateHeurePublication = rsMessages.getDate("date_heure_publication");
                Date dateHeureRecup = rsMessages.getDate("date_heure_recup");
                String resume = rsMessages.getString("resume");

                m = new Message(message, dateHeurePublication, dateHeureRecup, resume);

            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading one message", sqlE);
        } finally {
            closePStmtAndRS(stmt, rsMessages);
        }
        return m;
    }

    @Override
    public List<Message> getAllMessage() throws ConnectionProblemException {
        List<Facebook> FBmessages = new ArrayList<>();
        List<TwitterMessage> Twittermessages = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        //FBmessages = new FacebookDao().getAllFBMessages();
        Twittermessages = new TwitterDao().getAllTwitterMessages();

        /*  for (int compteur = 0; compteur < FBmessages.size(); compteur++) {
         messages.add(FBmessages.get(compteur));
         }*/
        for (int compteur = 0; compteur < Twittermessages.size(); compteur++) {
            messages.add(Twittermessages.get(compteur));
        }

        return messages;
    }

    @Override
    public List<Message> getTop5Message() throws ConnectionProblemException {
        List topMessages = new TwitterDao().getTopRetweeted(5); //Si le client veut un top 6, entrer 6 ici
        return topMessages;
    }

    @Override
    public Message addFacebookMessage(Message message) throws ConnectionProblemException {
        return this.addFacebookMessage(message);
    }

    @Override
    public Message addTwitterMessage(Message message) throws ConnectionProblemException {
        return this.addTwitterMessage(message);
    }

    public Message getMessage(Message message) {
        Message m = null;
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;

        String query = "SELECT numero, user_numero, message, date_heure_publication, date_heure_recup, resume FROM message  WHERE message=? and date_heure_publication=?";
        try {
            stmt = getConnection().prepareStatement(query);
            stmt.setString(1, message.getMessage());
            stmt.setDate(2, message.getDate_heure_publication());
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {

                int numero = rsMessages.getInt("numero");
                String messageText = rsMessages.getString("message");
                Date dateHeurePublication = rsMessages.getDate("date_heure_publication");
                Date dateHeureRecup = rsMessages.getDate("date_heure_recup");
                String resume = rsMessages.getString("resume");

                m = new Message(numero, messageText, dateHeurePublication, dateHeureRecup, resume);

            }
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePStmtAndRS(stmt, rsMessages);
        }
        return m;
    }
    
    public void deleteAllMessage(){
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;

        String query = "delete from message";
        try {
            stmt = getConnection().prepareStatement(query);
            
            stmt.executeQuery();

        
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(MessageDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePStmtAndRS(stmt, rsMessages);
        }
    }

}