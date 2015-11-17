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
public class RollbackException extends Exception {

    public RollbackException() {
        super();
    }

    public RollbackException(String msg) {
        super(msg);
    }

    public RollbackException(Throwable t) {
        super(t);
    }

    public RollbackException(String msg, Throwable t) {
        super(msg, t);
    }
}
