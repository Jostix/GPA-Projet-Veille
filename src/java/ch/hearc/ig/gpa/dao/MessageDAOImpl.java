/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.dao.interf.MessageDAO;
import ch.hearc.ig.gpa.business.Facebook;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.Twitter;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

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
            String query = "insert into Message(message, date_heure_publication, date_heure_recup, resume, user_numero) values (?,?,?,?)";

            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, message.getMessage());
            pstmt.setDate(2, message.getDate_heure_publication());
            pstmt.setDate(3, message.getDate_heure_recup());
            pstmt.setString(4, message.getResume());

            int count = pstmt.executeUpdate();

            int newMessageNum = getCurrentMsgSequenceValue();

            /**
             * Test del l'instance du message, si c'est un message facebook on
             * va appeler le FacebookDao pour ajouter une publication facebook,
             * si c'est un message Twitter on va bien entendu ajouter une
             * publication Twitter.
             */
            if (message instanceof Facebook) {
                new FacebookDao().addFbMessage((Facebook) message, newMessageNum);
            }
            if (message instanceof Twitter) {
                new TwitterDao().addTwitterMessage((Twitter) message, newMessageNum);
            }

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
        Connection c = null;

        String query = "SELECT message, date_heure_publication, date_heure_recup, resume FROM message  WHERE numero =?";
        try {
            c = OracleConnections.getConnection();
            stmt = c.prepareStatement(query);
            stmt.setInt(1, numero);
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {

                String message = rsMessages.getString("message");
                Date dateHeurePublication = rsMessages.getDate("date_heure_publication");
                Date dateHeureRecup = rsMessages.getDate("date_heure_recup");
                String resume = rsMessages.getString("resume");

                m = new Message(message, dateHeurePublication, dateHeureRecup, resume);

            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, null, ex);
            }
        }
        return m;
    }

    @Override
    public Set<Message> getAllMessage() throws ConnectionProblemException {
        List<Facebook> FBmessages = new ArrayList<>();
        List<Twitter> Twittermessages = new ArrayList<>();
        Set<Message> messages = new HashSet<>();
        FBmessages = new FacebookDao().getAllFBMessages();
        Twittermessages = new TwitterDao().getAllTwitterMessages();

        for (int compteur = 0; compteur < FBmessages.size(); compteur++) {
            messages.add(FBmessages.get(compteur));
        }

        for (int compteur = 0; compteur < Twittermessages.size(); compteur++) {
            messages.add(Twittermessages.get(compteur));
        }

        return messages;
    }

    @Override
    public Set<Message> getTop5Message() throws ConnectionProblemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message addFacebookMessage(Message message) throws ConnectionProblemException {
        return this.addFacebookMessage(message);
    }

    @Override
    public Message addTwitterMessage(Message message) throws ConnectionProblemException {
        return this.addTwitterMessage(message);
    }

}
