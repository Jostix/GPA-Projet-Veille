/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.exceptions;

import ch.hearc.ig.gpa.log.MyLogger;
import java.util.logging.Level;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class LoggerException extends Exception {

    public LoggerException() {
        super();
    }

    public LoggerException(String msg) {
        super(msg);
        MyLogger.getInstance().log(Level.SEVERE, msg);
    }

    public LoggerException(Throwable t) {
        super(t);
    }

    public LoggerException(String msg, Throwable t) {
        super(msg, t);
        MyLogger.getInstance().log(Level.SEVERE, msg, t);
    }
}
