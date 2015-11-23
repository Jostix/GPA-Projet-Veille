/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.dao;

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

    public Set<Image> researchAll() throws ConnectionProblemException;

    public Set<Image> researchByName(String nom) throws ConnectionProblemException;

    public Set<Image> research(Image image) throws ConnectionProblemException;

    public Image insert(String nom, String type, Blob image, Message message) throws ConnectionProblemException;

    public void insert(Image image) throws ConnectionProblemException;

    public void update(Image image) throws ConnectionProblemException;

    public void delete(Image image) throws ConnectionProblemException;

    public void delete(Integer imageID) throws ConnectionProblemException;
}
