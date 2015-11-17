/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
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
                System.out.println(result.getMessage());
            }
        } catch (FacebookException ex) {
            Logger.getLogger(RecuperationFacebook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}