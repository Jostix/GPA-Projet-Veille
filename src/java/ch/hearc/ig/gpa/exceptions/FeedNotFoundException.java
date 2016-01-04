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
 * @author thierry.hubmann
 */
public class FeedNotFoundException extends Exception{
    public FeedNotFoundException() {
        super();
    }

    public FeedNotFoundException(String msg) {
        super(msg);
        MyLogger.getInstance().log(Level.SEVERE, msg);
    }

    public FeedNotFoundException(Throwable t) {
        super(t);
    }

    public FeedNotFoundException(String msg, Throwable t) {
        super(msg, t);
        MyLogger.getInstance().log(Level.SEVERE, msg, t);
    }
}
