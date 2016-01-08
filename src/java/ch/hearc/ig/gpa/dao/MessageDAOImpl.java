/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.dao.interf.MessageDAO;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MessageDAOImpl extends AbstractDAOOracle implements MessageDAO {

    @Override
    public void addMessageRSS(RSS message, int userNum) throws ConnectionProblemException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            //Step 1 On ajoute le message dans la table message
            String query = "insert into Message(user_numero, message, date_heure_publication, date_heure_recup, resume) values (?,?,?,?,?)";

            pstmt = getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userNum);
            pstmt.setString(2, message.getMessage());
            pstmt.setDate(3, message.getDate_heure_publication());
            pstmt.setDate(4, message.getDate_heure_recup());
            pstmt.setString(5, message.getResume());

            int count = pstmt.executeUpdate();

            //Step 2 : On récupère le numéro de séquence
            Statement stmt = getConnection().createStatement();
            String seqQuery = "select seq_msg.currval from dual";
            rs = stmt.executeQuery(seqQuery);
            rs.next();

            int currentSequence = rs.getInt(1);

            //On ajoute le message dans la table RSSMessage
            new RSSDaoImpl().AddRSS(message, currentSequence);


        } catch (ConnectionProblemException ex) {
            ex.printStackTrace();
           // throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with adding message", sqlE);
        } finally {
            closePStmtAndRS(pstmt, rs);
        }
    }
    
    public void addMessageTwitter(TwitterMessage message, int userNum) throws ConnectionProblemException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            
            //Step 1 On ajoute le message dans la table message
            String query = "insert into Message(user_numero, message, date_heure_publication, date_heure_recup, resume) values (?,?,?,?,?)";

            pstmt = getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, userNum);
            pstmt.setString(2, message.getMessage());
            pstmt.setDate(3, message.getDate_heure_publication());
            pstmt.setDate(4, message.getDate_heure_recup());
            pstmt.setString(5, message.getResume());

            int count = pstmt.executeUpdate();

            //Step 2 : On récupère le numéro de séquence
            Statement stmt = getConnection().createStatement();
            String seqQuery = "select seq_msg.currval from dual";
            rs = stmt.executeQuery(seqQuery);
            rs.next();

            int currentSequence = rs.getInt(1);

            //On ajoute le message dans la table TwitterMessage
            new TwitterDao().addTwitterMessage(message, currentSequence);


        } catch (ConnectionProblemException ex) {
            ex.printStackTrace();
           // throw ex;
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

                m = new Message(message, dateHeurePublication, dateHeureRecup, resume, null);

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

    /**
     * Cette méthode récupère le top 5 des messages twitter et des messages
     * RSS mélange le tout et retourne les 5 premiers messages. 
     * @return le top 5 des messages
     * @throws ConnectionProblemException 
     */
    @Override
    public List<Message> getTop5Message() throws ConnectionProblemException {
        List topTwitter = AbstractDAOFactory.getDAOFactory().getTwitterDao().getTopRetweeted(5);
        List topRss = AbstractDAOFactory.getDAOFactory().getRSSDaoImpl().getTopRss(5);
        
        //Merge des deux listes
        List topMessages = topTwitter;
        topMessages.addAll(topRss);
        
        //Mélange
        Collections.shuffle(topMessages);
        
        //Limiter la liste aux 5 premiers
        topMessages = topMessages.subList(0, 5);
        
        return topMessages;
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

                m = new Message(numero, messageText, dateHeurePublication, dateHeureRecup, resume, null);

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

    public void deleteAllMessage() {
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
