/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Facebook;
import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.Twitter;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OraclePreparedStatement;

/**
 *
 * @author Julien Bron <julien.bron@he-arc.ch>
 */
public class RelationTwiHashDAO {
    
    public void insert(Twitter message, Hashtag hash) {
        Connection conn = null;
        OraclePreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = OracleConnections.getConnection();
            String query = "insert into rel_twit_hash(hsht_numero, twipub_numero) values (?,?)";

            pstmt = (OraclePreparedStatement) conn.prepareStatement(query); 
            pstmt.setInt(1, message.getIdentifiantTwi());
            pstmt.setInt(2, hash.getIdentifiant());

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
