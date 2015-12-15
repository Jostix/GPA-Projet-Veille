/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.log.MyLogger;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public abstract class AbstractDAOOracle extends AbstractDAO {

    @Override
    protected Connection getConnection() throws ConnectionProblemException {
        return OracleConnections.getConnection();
    }

    protected void closeStmtAndRS(Statement stmt, ResultSet rs) {
        try {

            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
        }

        try {

            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
        }

    }
    
     protected void closePStmtAndRS(PreparedStatement pstmt, ResultSet rs) {
        try {

            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
        }

        try {

            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
        }

    }
}
