/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.exceptions;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class ConnectionProblemException extends Exception {

    public ConnectionProblemException() {
        super();
    }

    public ConnectionProblemException(String msg) {
        super(msg);
    }

    public ConnectionProblemException(Throwable t) {
        super(t);
    }

    public ConnectionProblemException(String msg, Throwable t) {
        super(msg, t);
    }

}
