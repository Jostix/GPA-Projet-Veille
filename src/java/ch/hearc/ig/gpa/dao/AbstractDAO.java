/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
