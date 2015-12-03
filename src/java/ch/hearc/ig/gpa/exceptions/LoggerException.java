/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
