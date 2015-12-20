/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao.interf;

import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.util.List;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public interface MessageDAO {

    /**
     * Ajoute un message
     * 
     * @param message
     * @param userNum
     * @throws ConnectionProblemException 
     */
    public void addMessage(Message message, int userNum)  throws ConnectionProblemException;

    /**
     * Retourne la séquence des messages
     * 
     * @return
     * @throws ConnectionProblemException 
     */
    public int getCurrentMsgSequenceValue() throws ConnectionProblemException;

    /**
     * Retourne l'identifiant du message
     * 
     * @param numero
     * @return
     * @throws ConnectionProblemException 
     */
    public Message getMessageById(int numero) throws ConnectionProblemException;

    /**
     * Ajoute un message facebook
     * 
     * @param message
     * @return
     * @throws ConnectionProblemException 
     */
    public Message addFacebookMessage(Message message) throws ConnectionProblemException;

    /**
     * Ajoute un message twitter
     * 
     * @param message
     * @return
     * @throws ConnectionProblemException 
     */
    public Message addTwitterMessage(Message message) throws ConnectionProblemException;
    
    /**
     * Ajoute un message RSS
     * 
     * @param message
     * @return
     * @throws ConnectionProblemException 
     */
    public Message addRSSMessage(Message message) throws ConnectionProblemException;

    /**
     * Retourne une liste de tous les messages
     * 
     * @return
     * @throws ConnectionProblemException 
     */
    public List<Message> getAllMessage() throws ConnectionProblemException;

    /**
     * Retourne une liste du top 5 des messages
     * 
     * @return
     * @throws ConnectionProblemException 
     */
    public List<Message> getTop5Message() throws ConnectionProblemException;
}
