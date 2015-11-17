/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.log.MyLogger;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import java.sql.Connection;
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
}
