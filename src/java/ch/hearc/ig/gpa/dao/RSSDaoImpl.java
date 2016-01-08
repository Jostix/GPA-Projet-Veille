/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.dao.interf.RSSDao;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thierry.hubmann
 */
public class RSSDaoImpl extends MessageDAOImpl implements RSSDao{

    @Override
    public void AddRSS(RSS rss, int msgNumber) throws ConnectionProblemException{
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            String query = "insert into rss(url,categorie, msg_numero) values (?,?,?)";
            pstmt = getConnection().prepareStatement(query);
            pstmt.setString(1, rss.getUrl());
            pstmt.setString(2, rss.getCategorie());
            pstmt.setInt(3, msgNumber);
            
            pstmt.executeQuery();

        } catch (ConnectionProblemException ex) {
            throw new ConnectionProblemException("Problem while inserting rss message in DB");
        } catch (SQLException ex) {
            Logger.getLogger(RSSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closePStmtAndRS(pstmt, rs);
        }

    }

    @Override
    public List<RSS> getRSSMessages() throws ConnectionProblemException {
        List<RSS> rssMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = "SELECT numero, categorie, url, msg_numero FROM rss";
        try {
            stmt = getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rssMessages.add(getRSS(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RSSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionProblemException ex) {
            throw new ConnectionProblemException("A problem apeared while loading all RSS feeds");
        } finally {
            closePStmtAndRS(stmt, rs);
        }
        return rssMessages;
    }

    @Override
    public List<RSS> getRSSMessagesByCategory(String categorie) throws ConnectionProblemException{
        List<RSS> rssMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = "SELECT numero, categorie, url, msg_numero FROM rss WHERE categorie =?";
        try {
            stmt = getConnection().prepareStatement(query);
            stmt.setString(1, categorie);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rssMessages.add(getRSS(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RSSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionProblemException ex) {
            throw new ConnectionProblemException("A problem apeared while loading all RSS feeds");
        } finally {
            closePStmtAndRS(stmt, rs);
        }
        return rssMessages;
    }

    /**
     * Supprime tous les messages RSS
     */
    @Override
    public void deleteAllRSS() {
        PreparedStatement stmt = null;
        ResultSet rsMessages = null;

        String query = "delete from rss";
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

    /**
     * Retourne les 5 meilleurs
     * @param nbMessages
     * @return 
     */
    public List getTopRss(int nbMessages){
        List<RSS> rssMessages = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = "SELECT numero, categorie, url, msg_numero FROM rss";
        try {
            stmt = getConnection().prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                rssMessages.add(getRSS(rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(RSSDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConnectionProblemException ex) {
            throw new ConnectionProblemException("A problem apeared while loading all RSS feeds");
        } finally {
            closePStmtAndRS(stmt, rs);
        }
        return rssMessages;
    }
    private RSS getRSS(ResultSet rs) throws SQLException, ConnectionProblemException {
        int numero = rs.getInt("NUMERO");
        String url = rs.getString("URL");
        String categorie = rs.getString("categorie");
        Message message = new MessageDAOImpl().getMessageById(rs.getInt("msg_numero"));
        RSS rss = new RSS(numero, message.getMessage(), message.getDate_heure_publication(), message.getDate_heure_recup(), message.getResume(), url, categorie);

        return rss;
    }
    
    

}
