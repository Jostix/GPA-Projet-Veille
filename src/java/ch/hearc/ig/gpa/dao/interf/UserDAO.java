/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.dao.interf;

import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Date;
import java.util.Set;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public interface UserDAO {

    /**
     * Retourne un set de tous les utilisateurs
     * 
     * @return
     * @throws ConnectionProblemException 
     */
    public Set<User> researchAll() throws ConnectionProblemException;

    /**
     * Retourne un set des utilisateurs en fonction de leur nom
     * 
     * @param name
     * @return
     * @throws ConnectionProblemException 
     */
    public Set<User> researchByName(String name) throws ConnectionProblemException;

    /**
     * Retourne un set d'utilisateur en fonction de l'objet utilisateur passé en paramètre
     * 
     * @param user
     * @return
     * @throws ConnectionProblemException 
     */
    public Set<User> research(User user) throws ConnectionProblemException;

    /**
     * Ajoute un utilisateur
     * 
     * @param name
     * @param lastname
     * @param birthdate
     * @param country
     * @param usernameTwitter
     * @return
     * @throws ConnectionProblemException 
     */
    public User insert(String name, String lastname, Date birthdate, String country, String usernameTwitter) throws ConnectionProblemException;

    /**
     * Ajoute un utilisateur
     * 
     * @param utilisateur
     * @throws ConnectionProblemException 
     */
    public void insert(User utilisateur) throws ConnectionProblemException;

    /**
     * Met à jour un utilisateur
     * 
     * @param utilisateur
     * @throws ConnectionProblemException 
     */
    public void update(User utilisateur) throws ConnectionProblemException;

    /**
     * Supprime un utilisateur
     * 
     * @param utilisateur
     * @throws ConnectionProblemException 
     */
    public void delete(User utilisateur) throws ConnectionProblemException;

    /**
     * Supprime un utilisateur
     * 
     * @param utilisateurID
     * @throws ConnectionProblemException 
     */
    public void delete(Integer utilisateurID) throws ConnectionProblemException;
}
