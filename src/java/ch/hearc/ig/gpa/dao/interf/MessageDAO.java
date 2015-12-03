/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public void addMessage(Message message, int userNum)  throws ConnectionProblemException;

    public int getCurrentMsgSequenceValue() throws ConnectionProblemException;

    public Message getMessageById(int numero) throws ConnectionProblemException;

    public Message addFacebookMessage(Message message) throws ConnectionProblemException;

    public Message addTwitterMessage(Message message) throws ConnectionProblemException;

    public List<Message> getAllMessage() throws ConnectionProblemException;

    public List<Message> getTop5Message() throws ConnectionProblemException;
}
