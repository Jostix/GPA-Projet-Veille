/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao.interf;

import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.util.List;

/**
 *
 * @author thierry.hubmann
 */
public interface RSSDao {
    public void AddRSS(RSS rss, int msgNumber) throws ConnectionProblemException;
    
    public List<RSS> getRSSMessages() throws ConnectionProblemException;
    
    public List<RSS> getRSSMessagesByCategory(String categorie) throws ConnectionProblemException;
    
    public void deleteAllRSS();
}
