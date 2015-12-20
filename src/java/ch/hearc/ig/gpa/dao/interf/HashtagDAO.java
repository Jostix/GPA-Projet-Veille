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
 * Interface du DAO hastag
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public interface HashtagDAO {

    /**
     * Retourne un set de tous les hashtags
     *
     * @return
     * @throws ConnectionProblemException
     */
    public Set<Hashtag> researchAll() throws ConnectionProblemException;

    /**
     * Retourne un set des hashtag avec un certain libelle
     *
     * @param libelle
     * @return
     * @throws ConnectionProblemException
     */
    public Set<Hashtag> researchByName(String libelle) throws ConnectionProblemException;

    /**
     * Retourne un set de hastag avec un certain hastag
     *
     * @param hashtag
     * @return
     * @throws ConnectionProblemException
     */
    public Set<Hashtag> research(Hashtag hashtag) throws ConnectionProblemException;

    /**
     * Ajoute un hashtag via son libelle et son message
     *
     * @param libelle
     * @param message
     * @return
     * @throws ConnectionProblemException
     */
    public Hashtag insert(String libelle, Message message) throws ConnectionProblemException;

    /**
     * Ajouter un hashtag
     *
     * @param hashtag
     * @throws ConnectionProblemException
     */
    public void insert(Hashtag hashtag) throws ConnectionProblemException;

    /**
     * Met à jour un hastag
     *
     * @param hashtag
     * @throws ConnectionProblemException
     */
    public void update(Hashtag hashtag) throws ConnectionProblemException;

    /**
     * Supprime un hastag via l'objet
     *
     * @param hashtag
     * @throws ConnectionProblemException
     */
    public void delete(Hashtag hashtag) throws ConnectionProblemException;

    /**
     * Supprime un hashtag via son identifiant
     *
     * @param hashtagID
     * @throws ConnectionProblemException
     */
    public void delete(Integer hashtagID) throws ConnectionProblemException;
}
