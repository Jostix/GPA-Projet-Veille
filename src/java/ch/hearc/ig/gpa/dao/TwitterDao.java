/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Twitter;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
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
public class TwitterDao {
    public List<Twitter> getAllTwitterMessages() throws ConnectionProblemException {
        List<Twitter> twitterMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;
        Connection c = null;

        String query = "SELECT retweet, msg_numero FROM twitter_publication";
        try {
            c = OracleConnections.getConnection();
            stmt = c.prepareStatement(query);
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {
                Integer retweet = rsMessages.getInt("retweet");
                Message message = new MessageDAOImpl().getMessageById(rsMessages.getInt("msg_numero"));

                Twitter twMessage = new Twitter(message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume(), retweet);

                twitterMessages.add(twMessage);

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
        return twitterMessages;
    }
    
    public void addTwitterMessage(Twitter message, int messageNumber){
        Connection conn = null;
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = OracleConnections.getConnection();
            String query = "insert into twitter_publication(retweet, msg_numero) values (?,?)";

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); 
            pstmt.setInt(1, message.getRetweet());
            pstmt.setInt(2, messageNumber);
            

            int count = pstmt.executeUpdate();
            
            int identifiant = getCurrentMsgSequenceValue();
            
            message.setIdentifiantTwi(identifiant);
            

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
    
    public int getCurrentMsgSequenceValue() {
        int currentValue = -1;
        PreparedStatement stmt = null;
        ResultSet rsSequence = null;
        Connection c = null;

        String query = "SELECT seq_twipub.currval FROM DUAL";
        try {
            c = OracleConnections.getConnection();
            stmt = c.prepareStatement(query);
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
}
