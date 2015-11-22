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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author thierry.hubmann
 */
public class FacebookDao extends MessageDAOImpl{

    public List<Facebook> getAllFBMessages() throws ConnectionProblemException {
        List<Facebook> facebookMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;
        Connection c = null;

        String query = "SELECT localisation, msg_numero FROM facebook_publication";
        try {
            c = OracleConnections.getConnection();
            stmt = c.prepareStatement(query);
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {
                String localisation = rsMessages.getString("localisation");
                Message message = new MessageDAOImpl().getMessageById(rsMessages.getInt("msg_numero"));

                Facebook fbMessage = new Facebook(localisation, message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume());

                facebookMessages.add(fbMessage);

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
        return facebookMessages;
    }
    
    public void addFbMessage(Facebook message, int messageNumber){
        Connection conn = null;
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = OracleConnections.getConnection();
            String query = "insert into facebook_publication(localisation, msg_numero) values (?,?)";

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); 
            pstmt.setString(1, message.getLocalisation());
            pstmt.setInt(2, messageNumber);
            

            int count = pstmt.executeUpdate();
            

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

}
