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
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public abstract class AbstractDAO {
    protected abstract Connection getConnection() throws ConnectionProblemException;
}
