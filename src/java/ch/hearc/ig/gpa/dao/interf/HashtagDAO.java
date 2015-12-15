/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao.interf;

import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.util.Set;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public interface HashtagDAO {
        
    public Set<Hashtag> researchAll() throws ConnectionProblemException;

    public Set<Hashtag> researchByName(String libelle) throws ConnectionProblemException;

    public Set<Hashtag> research(Hashtag hashtag) throws ConnectionProblemException;

    public Hashtag insert(String libelle, Message message) throws ConnectionProblemException;

    public void insert(Hashtag hashtag) throws ConnectionProblemException;

    public void update(Hashtag hashtag) throws ConnectionProblemException;

    public void delete(Hashtag hashtag) throws ConnectionProblemException;

    public void delete(Integer hashtagID) throws ConnectionProblemException;
}
