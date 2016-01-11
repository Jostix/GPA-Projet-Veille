/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.services;

import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.RollbackException;
import ch.hearc.ig.gpa.log.MyLogger;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public abstract class UserService {

    public static List<User> findAllUser() throws ConnectionProblemException {
        List<User> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getUserDAOImpl().researchAll();
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    public static List<User> findUsersByName(String name) throws ConnectionProblemException {
        List<User> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getUserDAOImpl().researchByName(name);
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    
    /**
     * Not used yet
     */
//    public static User createUser(String name, String lastname, Date birthdate, String country, String usernameTwitter) throws ConnectionProblemException {
//        User newUser = null;
//        try {
//            newUser = AbstractDAOFactory.getDAOFactory().getUserDAOImpl().insert(name, lastname, birthdate, country, usernameTwitter);
//            AbstractDAOFactory.getDAOFactory().commit();
//
//        } catch (ConnectionProblemException ex) {
//            try {
//                AbstractDAOFactory.getDAOFactory().rollback();
//            } catch (RollbackException ex1) {
//                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
//            }
//            MyLogger.getInstance().log(Level.SEVERE, null, ex);
//            throw ex;
//        } catch (CommitException ex) {
//            try {
//                AbstractDAOFactory.getDAOFactory().rollback();
//            } catch (RollbackException ex1) {
//                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
//            }
//            MyLogger.getInstance().log(Level.SEVERE, null, ex);
//            throw new ConnectionProblemException("A problem appeared while inserting the new user in the database");
//        } finally {
//            AbstractDAOFactory.getDAOFactory().closeConnection();
//        }
//
//        return newUser;
//    }

}
