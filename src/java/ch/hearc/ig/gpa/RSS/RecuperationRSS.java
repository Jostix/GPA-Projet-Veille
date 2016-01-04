/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.RSS;

import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.business.RSSFeed;
import static ch.hearc.ig.gpa.constants.Constant.RSS;
import ch.hearc.ig.gpa.dao.MessageDAOImpl;
import ch.hearc.ig.gpa.dao.interf.RSSDao;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thierry.hubmann
 */
public class RecuperationRSS {
    
    public List<RSS> getRSS() throws XMLStreamException, ConnectionProblemException{
        FeedReader parser = new FeedReader("https://www.mercedes-benz.com/en/ressort/mercedes-benz/innovation/feed/");
        RSSFeed feed = parser.readFeed();
        System.out.println(feed);
        for (RSS message : feed.getMessages()) {
           MessageDAOImpl msgDao = AbstractDAOFactory.getDAOFactory().getMessageDAO();
           msgDao.addRSSMessage(message);

        }
        return feed.getMessages();
    }
}
