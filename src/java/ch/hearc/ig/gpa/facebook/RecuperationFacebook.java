/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Post;
import facebook4j.ResponseList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe n'est pas utilis�e car la r�cup�ration facebook est indisponible
 * @author thierry.hubmann
 */
public class RecuperationFacebook {

    Facebook facebook;
    
    public RecuperationFacebook() {
        facebook = new FacebookFactory().getInstance();
    }

    public void recuperationPosts(){
        try {
            ResponseList<Post> results = facebook.searchPosts("watermelon");
            for (Post result : results) {
            
            }
        } catch (FacebookException ex) {
            Logger.getLogger(RecuperationFacebook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
