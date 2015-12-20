/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao.interf;

import ch.hearc.ig.gpa.business.Image;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Blob;
import java.util.Set;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public interface ImageDAO {

    /**
     * Retourne un set de toutes les images
     *
     * @return
     * @throws ConnectionProblemException
     */
    public Set<Image> researchAll() throws ConnectionProblemException;

    /**
     * Retourne un set des images en fonction de leur nom
     *
     * @param nom
     * @return
     * @throws ConnectionProblemException
     */
    public Set<Image> researchByName(String nom) throws ConnectionProblemException;

    /**
     * Retoure un set des images en fonction de l'objet image
     *
     * @param image
     * @return
     * @throws ConnectionProblemException
     */
    public Set<Image> research(Image image) throws ConnectionProblemException;

    /**
     * Ajoute une image
     *
     * @param nom
     * @param type
     * @param image
     * @param message
     * @return
     * @throws ConnectionProblemException
     */
    public Image insert(String nom, String type, Blob image, Message message) throws ConnectionProblemException;

    /**
     * Ajoute une image
     *
     * @param image
     * @throws ConnectionProblemException
     */
    public void insert(Image image) throws ConnectionProblemException;

    /**
     * Met à jour une image
     *
     * @param image
     * @throws ConnectionProblemException
     */
    public void update(Image image) throws ConnectionProblemException;

    /**
     * Supprime une image
     *
     * @param image
     * @throws ConnectionProblemException
     */
    public void delete(Image image) throws ConnectionProblemException;

    /**
     * Supprime une image
     *
     * @param imageID
     * @throws ConnectionProblemException
     */
    public void delete(Integer imageID) throws ConnectionProblemException;
}
