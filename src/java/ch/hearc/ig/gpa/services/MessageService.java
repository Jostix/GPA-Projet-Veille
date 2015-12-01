/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.services;

import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Image;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.RollbackException;
import ch.hearc.ig.gpa.log.MyLogger;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MessageService {

    public static Set<Message> findAllMessage() throws ConnectionProblemException {
        Set<Message> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getMessageDAO().getAllMessage();
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }

    public static List<Message> findTop5Message() throws ConnectionProblemException {
        List<Message> list = null;
        try {
            list = AbstractDAOFactory.getDAOFactory().getMessageDAO().getTop5Message();
        } catch (ConnectionProblemException e) {
            MyLogger.getInstance().log(Level.SEVERE, null, e);
            throw e;
        } finally {
            AbstractDAOFactory.getDAOFactory().closeConnection();
        }
        return list;
    }
    
   public static Message addFacebookMessage(Message message, Image img) throws ConnectionProblemException {
        Message newMessage = null;
        try {
            newMessage = AbstractDAOFactory.getDAOFactory().getMessageDAO().addFacebookMessage(newMessage);
            AbstractDAOFactory.getDAOFactory().commit();
            img.setMessage(message);
            
            
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

        return newMessage;
    }
   
    public static Message addTwitterMessage(Message message, Image img, Hashtag hash) throws ConnectionProblemException {
        Message newMessage = null;
        try {
            newMessage = AbstractDAOFactory.getDAOFactory().getMessageDAO().addTwitterMessage(message);
            AbstractDAOFactory.getDAOFactory().commit();
            img.setMessage(message);
            hash.setMessage(message);

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

        return newMessage;
    }
}
