/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class HashtagDAOImpl extends AbstractDAOOracle implements HashtagDAO {

    @Override
    public Set<Hashtag> researchAll() throws ConnectionProblemException {
        Statement stmt = null;
        ResultSet rs = null;

        Set<Hashtag> hashtags = new HashSet<>();

        try {
            stmt = getConnection().createStatement();
            String query = "SELECT NUMERO, LIBELLE FROM HASHTAG";
            rs = stmt.executeQuery(query);

            //On ajoute les hashtags à la liste
            while (rs.next()) {
                hashtags.add(getHashtag(rs));
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appared with loading all hashtag", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }

        return hashtags;
    }

    @Override
    public Set<Hashtag> researchByName(String libelle) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Set<Hashtag> hashtags = new HashSet<>();
        Hashtag currentHashtag = null;

        try {

            String query = "SELECT NUMERO, LIBELLE WHERE UPPER(libelle) LIKE UPPER(?)";

            stmt = getConnection().prepareStatement(query);
            libelle = "%" + libelle + "%";
            stmt.setString(1, libelle);

            rs = stmt.executeQuery();

            // On ajoute les hashtags à la liste
            while (rs.next()) {
                currentHashtag = getHashtag(rs);
                hashtags.add(currentHashtag);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while loading hashtags by name", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
        return hashtags;
    }

    @Override
    public Set<Hashtag> research(Hashtag hashtag) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean and = false;

        Set<Hashtag> hashtags = new HashSet<>();
        Hashtag currentHashtag = null;

        try {

            StringBuilder query = new StringBuilder("SELECT NUMERO, LIBELLE FROM HASHTAG");

            if (!hashtag.isNull()) {
                query.append(" where ");
                if (hashtag.getIdentifiant() != -1) {
                    query.append("NUMERO = '");
                    query.append(hashtag.getIdentifiant());
                    query.append("'");
                    and = true;
                }

                if (hashtag.getLibelle() != null) {
                    if (and) {
                        query.append(" and ");
                    }
                    query.append("LIBELLE = '");
                    query.append(hashtag.getLibelle());
                    query.append("'");
                    and = true;
                }
            }

            stmt = getConnection().prepareStatement(query.toString());

            rs = stmt.executeQuery();

            // On ajoute les hashtags à la liste
            while (rs.next()) {
                currentHashtag = getHashtag(rs);
                hashtags.add(currentHashtag);
            }
        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while loading hashtags by name", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
        return hashtags;
    }

    @Override
    public Hashtag insert(String libelle, Message message) throws ConnectionProblemException {
        Hashtag hashtag = new Hashtag(null, libelle, message);

        this.insert(hashtag);

        return hashtag;
    }

    @Override
    public void insert(Hashtag hashtag) throws ConnectionProblemException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        final String generatedColumns[] = {"numero"};

        try {
            String query = "INSERT INTO hashtag (libelle) VALUES (?)";
            stmt = getConnection().prepareStatement(query, generatedColumns);

            stmt.setString(1, hashtag.getLibelle());

            int rowCount = stmt.executeUpdate();

            int identifiant = getCurrentMsgSequenceValue();
            
            hashtag.setIdentifiant(identifiant);
            
            if (rowCount != 1) {
                throw new ConnectionProblemException("A problem appeared while inserting an hashtag !");
            }

            rs = stmt.getGeneratedKeys();
            
            if (rs.next()) {
                hashtag.setIdentifiant(rs.getInt(1));
            }

        } catch (ConnectionProblemException ex) {
            throw ex;
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while inserting an hashtag", sqlE);
        } finally {
            closeStmtAndRS(stmt, rs);
        }
    }

    @Override
    public void update(Hashtag hashtag) throws ConnectionProblemException {
        PreparedStatement stmt = null;

        try {
            String query = "UPDATE hashtag SET libelle = ? WHERE numero = ?";
            stmt = getConnection().prepareStatement(query);

            stmt.setString(1, hashtag.getLibelle());
            stmt.setInt(2, hashtag.getMessage().getIdentifiant());
            stmt.setInt(3, hashtag.getIdentifiant());
            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("Error : update statement returned " + rowCount + " rows instead of 1.");
            }
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while updating an hashtag", sqlE);
        } finally {
            closeStmtAndRS(stmt, null);
        }
    }

    @Override
    public void delete(Hashtag hashtag) throws ConnectionProblemException {
        this.delete(hashtag.getIdentifiant());
    }

    @Override
    public void delete(Integer hashtagID) throws ConnectionProblemException {
        PreparedStatement stmt = null;

        try {
            String query = "DELETE FROM hashtag WHERE numero = ?";
            stmt = getConnection().prepareStatement(query);

            stmt.setInt(1, hashtagID);
            int rowCount = stmt.executeUpdate();

            if (rowCount != 1) {
                throw new ConnectionProblemException("Error : delete statement returned " + rowCount + " rows instead of 1.");
            }
        } catch (SQLException sqlE) {
            throw new ConnectionProblemException("A problem appeared while deleting an hashtag", sqlE);
        } finally {
            closeStmtAndRS(stmt, null);
        }
    }

    private Hashtag getHashtag(ResultSet rs) throws SQLException, ConnectionProblemException {
        Hashtag hashtag = new Hashtag();
        hashtag.setIdentifiant(rs.getInt("NUMERO"));
        hashtag.setLibelle(rs.getString("LIBELLE"));
       
        //hashtag.setMessage(MessageDAO.getMessageById(rs.getInt("MSG_NUMERO")));
  
        return hashtag;
    }
    
    @Override
    public int getCurrentMsgSequenceValue() {
        int currentValue = -1;
        PreparedStatement stmt = null;
        ResultSet rsSequence = null;
        Connection c = null;

        String query = "SELECT seq_hsht.currval FROM DUAL";
        try {
            c = OracleConnections.getConnection();
            stmt = c.prepareStatement(query);
            rsSequence = stmt.executeQuery();

            while (rsSequence.next()) {
                currentValue = rsSequence.getInt("seq_hsht.currval");
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
