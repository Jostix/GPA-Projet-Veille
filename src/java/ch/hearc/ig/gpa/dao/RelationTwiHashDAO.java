/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Facebook;
import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import static ch.hearc.ig.gpa.dbfactory.OracleConnections.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Julien Bron <julien.bron@he-arc.ch>
 */
public class RelationTwiHashDAO extends AbstractDAOOracle{
    
    public void insert(TwitterMessage message, Hashtag hash) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
//            String query = "set constraints all deferred";
//            
//            pstmt = getConnection().prepareStatement(query);
//            pstmt.executeQuery();
            
            String query = "insert into rel_twit_hash(hsht_numero, twipub_numero) values (?,?)";

            pstmt = getConnection().prepareStatement(query); 
            pstmt.setInt(1, hash.getIdentifiant());
            pstmt.setInt(2, message.getIdentifiantTwi());
            
            int count = pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStmtAndRS(pstmt, rs);
        }
    }
    
}
