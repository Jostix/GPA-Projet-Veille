/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MessageDAOImpl extends AbstractDAOOracle implements MessageDAO {

    @Override
    public void addMessage(Message message, int userNum) {
        Connection conn = null;
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = OracleConnections.getConnection();
            String query = "insert into Message(message, date_heure_publication, date_heure_recup, resume, user_numero) values (?,?,?,?)";

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query);
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
                //new TwitterDao().addTwitterMessage((Twitter)message, newMessageNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public int getCurrentMsgSequenceValue() {
        int currentValue = -1;
        PreparedStatement stmt = null;
        ResultSet rsSequence = null;
        Connection c = null;

        String query = "SELECT seq_msg.currval FROM DUAL";
        try {
            c = OracleConnections.getConnection();
            stmt = c.prepareStatement(query);
            rsSequence = stmt.executeQuery();

            while (rsSequence.next()) {
                currentValue = rsSequence.getInt("seq_msg.currval");
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
        Set<Message> messages = new HashSet<>();

        FacebookDao fbdao = new FacebookDao();

        messages = fbdao.getAllMessage();

       //Ajouter les messages de twitter ici.
        //Tester conversion List / Set sinon tout passer en Set
        return messages;
    }

    @Override
    public Set<Message> getTop5Message() throws ConnectionProblemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message addFacebookMessage(Message message) throws ConnectionProblemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Message addTwitterMessage(Message message) throws ConnectionProblemException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
