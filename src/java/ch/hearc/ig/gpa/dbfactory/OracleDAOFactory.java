/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dbfactory;

import ch.hearc.ig.gpa.dao.interf.HashtagDAO;
import ch.hearc.ig.gpa.dao.HashtagDAOImpl;
import ch.hearc.ig.gpa.dao.interf.ImageDAO;
import ch.hearc.ig.gpa.dao.ImageDAOImpl;
import ch.hearc.ig.gpa.dao.MessageDAOImpl;
import ch.hearc.ig.gpa.dao.RelationTwiHashDAO;
import ch.hearc.ig.gpa.dao.interf.UserDAO;
import ch.hearc.ig.gpa.dao.UserDAOImpl;
import ch.hearc.ig.gpa.dbfactory.OracleConnections;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.exceptions.RollbackException;
import java.sql.Connection;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class OracleDAOFactory extends AbstractDAOFactory {

    private static OracleDAOFactory instance;

    private OracleDAOFactory() {
    }

    public static OracleDAOFactory getInstance() {
        if (instance == null) {
            instance = new OracleDAOFactory();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws ConnectionProblemException {
        return OracleConnections.getConnection();
    }

    @Override
    public void closeConnection() {
        OracleConnections.closeConnection();
    }

    @Override
    public void commit() throws CommitException {
        OracleConnections.commit();
    }

    @Override
    public void rollback() throws RollbackException {
        OracleConnections.rollback();
    }

    @Override
    public ImageDAO getImageDAO() {
        return new ImageDAOImpl();
    }

    @Override
    public HashtagDAO getHashtagDAO() {
        return new HashtagDAOImpl();
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public MessageDAOImpl getMessageDAO() {
        return new MessageDAOImpl();
    }
    
    @Override
    public RelationTwiHashDAO getRelationTwiHashDAO() {
        return new RelationTwiHashDAO();
    }

}
