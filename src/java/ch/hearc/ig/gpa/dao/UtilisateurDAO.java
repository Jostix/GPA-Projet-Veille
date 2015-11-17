/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

import ch.hearc.ig.gpa.business.Utilisateur;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.sql.Date;
import java.util.Set;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public interface UtilisateurDAO {

    public Set<Utilisateur> researchAll() throws ConnectionProblemException;

    public Set<Utilisateur> researchByName(String name) throws ConnectionProblemException;

    public Set<Utilisateur> research(Utilisateur user) throws ConnectionProblemException;

    public Utilisateur insert(String name, String lastname, Date birthdate, String country, String usernameTwitter) throws ConnectionProblemException;

    public void insert(Utilisateur utilisateur) throws ConnectionProblemException;

    public void update(Utilisateur utilisateur) throws ConnectionProblemException;

    public void delete(Utilisateur utilisateur) throws ConnectionProblemException;

    public void delete(Integer utilisateurID) throws ConnectionProblemException;
}
