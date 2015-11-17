/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.log;

import ch.hearc.ig.gpa.exceptions.LoggerException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class MyLogger {

    private static MyLogger instance = null;
    
    private Logger logger;
    
    private MyLogger() throws LoggerException {
        logger = Logger.getLogger(MyLogger.class.getName());
        Handler[] handlers = logger.getHandlers();
        
        for (int i = 0; i < handlers.length; i++) {
            logger.removeHandler(handlers[i]);
        }
        
        try {
            FileHandler filehandler = new FileHandler("ProjetVeille.log");
            filehandler.setFormatter(new java.util.logging.SimpleFormatter());
            logger.addHandler(filehandler);
        } catch (Exception e) {
            System.out.println("Erreur pendant la création du logger: " + e);
            throw new LoggerException("Erreur pendant la création du logger" + e);
        }
    }
    
    public static MyLogger getInstance() {
        if (instance == null) {
            try {
                instance = new MyLogger();
            } catch (LoggerException ex) {
                Logger.getLogger(MyLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return instance;
    }
    
    public void log(Level level, String message) {
        logger.log(level, message);
    }
    
    public void log(Level level, String message, Throwable throwable) {
        logger.log(level, message, throwable);
    }
}
