/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Connection;

/**
 * Interface pour r�cup�rer une connection Oracle
 * 
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public abstract class AbstractDAO {
    /**
     * Constructeur
     * 
     * @return
     * @throws ConnectionProblemException 
     */
    protected abstract Connection getConnection() throws ConnectionProblemException;
}
