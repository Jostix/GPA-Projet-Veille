/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.RSS;

import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.business.RSSFeed;
import ch.hearc.ig.gpa.constants.Categories;
import ch.hearc.ig.gpa.constants.Constants;
import ch.hearc.ig.gpa.dao.MessageDAOImpl;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.services.StringServices;
import java.text.ParseException;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author thierry.hubmann
 */
public class RecuperationRSS {

    /**
     * Récupère la liste des flux RSS du lien donné en paramètre
     *
     * @param rssFeed le lien du flux RSS
     * @return une liste de flux RSS
     * @throws XMLStreamException
     * @throws ConnectionProblemException
     */
    public List<RSS> getRSS(String rssFeed) throws XMLStreamException, ConnectionProblemException, ParseException {
        FeedReader parser = new FeedReader(rssFeed);
        RSSFeed feed = parser.readFeed();
      
        MessageDAOImpl msgDao = AbstractDAOFactory.getDAOFactory().getMessageDAO();
        for (RSS message : feed.getMessages()) {
            //Verification si le message contient un des mots clés
            if (StringServices.containsKey(message.getMessage(), Constants.KEYWORDS)){
            message.setCategorie(Categories.RSS.toString());
            msgDao.addMessageRSS(message, 1);
            }
        }
        return feed.getMessages();
    }
}
