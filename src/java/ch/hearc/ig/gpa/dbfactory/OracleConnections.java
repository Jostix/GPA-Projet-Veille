/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dbfactory;

import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.RollbackException;
import ch.hearc.ig.gpa.log.MyLogger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author romain.ducret1
 */
public class OracleConnections {
    //private static OracleDataSource ods = null;

    private static final String USER = "julien_bron";
    private static final String PASSWORD = "julien_bron";
    private static final String HOST = "db.ig.he-arc.ch";
    private static final String PORT = "1521";
    private static final String SID = "ens2";

    private static Connection con = null;

    private static void createConnection() throws ConnectionProblemException {
        try {
            // Se connecter à la BD Oracle
            String DBURL = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID;
            con = DriverManager.getConnection(DBURL, USER, PASSWORD);     
            con.setAutoCommit(false);

        } catch (SQLException ex) {
            throw new ConnectionProblemException(ex);
        }
    }

    public static Connection getConnection() throws ConnectionProblemException {
        if (con == null) {
            createConnection();
        }        
        return con;
    }

    public static void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
        } finally {
            con = null;
        }
    }

    public static void commit() throws CommitException {
        try {
            con.commit();
        } catch (SQLException ex) {
            throw new CommitException(ex);
        }
    }

    public static void rollback() throws RollbackException {
        try {
            con.rollback();
        } catch (SQLException ex) {
            throw new RollbackException(ex);
        }
    }

    /*
     public static Connection getConnection() throws SQLException{
     if(ods == null){
     String url = "jdbc:oracle:thin:@" + HOST + ":" + PORT + ":" + SID;
            
     ods = new OracleDataSource();
     ods.setUser(USER);
     ods.setPassword(PASSWORD);
     ods.setURL(url);
     }
        
     Connection c = ods.getConnection();
     c.setAutoCommit(false);
        
     return c;
     }*/
}
