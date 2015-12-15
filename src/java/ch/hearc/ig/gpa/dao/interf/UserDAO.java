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

    public Set<User> researchAll() throws ConnectionProblemException;

    public Set<User> researchByName(String name) throws ConnectionProblemException;

    public Set<User> research(User user) throws ConnectionProblemException;

    public User insert(String name, String lastname, Date birthdate, String country, String usernameTwitter) throws ConnectionProblemException;

    public void insert(User utilisateur) throws ConnectionProblemException;

    public void update(User utilisateur) throws ConnectionProblemException;

    public void delete(User utilisateur) throws ConnectionProblemException;

    public void delete(Integer utilisateurID) throws ConnectionProblemException;
}
