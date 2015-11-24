/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Facebook;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author thierry.hubmann
 */
public class FacebookDao extends MessageDAOImpl {

    public List<Facebook> getAllFBMessages() throws ConnectionProblemException {
        List<Facebook> facebookMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;

        String query = "SELECT localisation, msg_numero FROM facebook_publication";
        try {
            stmt = getConnection().prepareStatement(query);
            rsMessages = stmt.executeQuery();

            while (rsMessages.next()) {
                String localisation = rsMessages.getString("localisation");
                Message message = new MessageDAOImpl().getMessageById(rsMessages.getInt("msg_numero"));

                Facebook fbMessage = new Facebook(localisation, message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume());

                facebookMessages.add(fbMessage);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading all facebook message", sqlE);
        } finally {
            closePStmtAndRS(stmt, rsMessages);
        }
        return facebookMessages;
    }

    public void addFbMessage(Facebook message, int messageNumber) throws ConnectionProblemException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "insert into facebook_publication(localisation, msg_numero) values (?,?)";

            pstmt = (OraclePreparedStatement) getConnection().prepareStatement(query);
            pstmt.setString(1, message.getLocalisation());
            pstmt.setInt(2, messageNumber);

            int count = pstmt.executeUpdate();

        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with adding new facebook message", sqlE);
        } finally {
            closePStmtAndRS(pstmt, rs);
        }
    }

}
