/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dbfactory;

import ch.hearc.ig.gpa.dao.HashtagDAOImpl;
import ch.hearc.ig.gpa.dao.interf.HashtagDAO;
import ch.hearc.ig.gpa.dao.interf.ImageDAO;
import ch.hearc.ig.gpa.dao.MessageDAOImpl;
import ch.hearc.ig.gpa.dao.RelationTwiHashDAO;
import ch.hearc.ig.gpa.dao.TwitterDao;
import ch.hearc.ig.gpa.dao.interf.UserDAO;
import ch.hearc.ig.gpa.dbfactory.OracleDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.RollbackException;
import java.sql.Connection;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public abstract class AbstractDAOFactory {
    
    public abstract MessageDAOImpl getMessageDAO();
      
    public abstract ImageDAO getImageDAO();

    public abstract HashtagDAOImpl getHashtagDAO();
    
    public abstract UserDAO getUserDAO();
    
    public abstract TwitterDao getTwitterDao();
    
    public abstract RelationTwiHashDAO getRelationTwiHashDAO();

    public abstract Connection getConnection() throws ConnectionProblemException;

    public abstract void closeConnection();

    public abstract void commit() throws CommitException;

    public abstract void rollback() throws RollbackException;

    public static AbstractDAOFactory getDAOFactory() {

        return OracleDAOFactory.getInstance();

    }
}
