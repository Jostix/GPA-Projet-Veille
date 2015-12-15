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
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public abstract class UserService {

    public static Set<User> findAllUser() throws ConnectionProblemException {
        Set<User> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getUserDAO().researchAll();
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    public static Set<User> findUsersByName(String name) throws ConnectionProblemException {
        Set<User> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getUserDAO().researchByName(name);
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    public static User createUser(String name, String lastname, Date birthdate, String country, String usernameTwitter) throws ConnectionProblemException {
        User newUser = null;
        try {
            newUser = AbstractDAOFactory.getDAOFactory().getUserDAO().insert(name, lastname, birthdate, country, usernameTwitter);
            AbstractDAOFactory.getDAOFactory().commit();

        } catch (ConnectionProblemException ex) {
            try {
                AbstractDAOFactory.getDAOFactory().rollback();
            } catch (RollbackException ex1) {
                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
            }
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
            throw ex;
        } catch (CommitException ex) {
            try {
                AbstractDAOFactory.getDAOFactory().rollback();
            } catch (RollbackException ex1) {
                MyLogger.getInstance().log(Level.SEVERE, null, ex1);
            }
            MyLogger.getInstance().log(Level.SEVERE, null, ex);
            throw new ConnectionProblemException("A problem appeared while inserting the new user in the database");
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }

        return newUser;
    }

}
