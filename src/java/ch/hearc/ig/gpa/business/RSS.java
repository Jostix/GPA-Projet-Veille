/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.business;

import java.sql.Date;
import ch.hearc.ig.gpa.constants.Constant;

/**
 *
 * @author thierry.hubmann
 */
public class RSS extends Message{

    private int identifiantRSS;
    private String url;
    private String categorie;
    
    public RSS(){
        super(null,null,null,null, null);
    }

    public RSS(int identifiantRSS, String message, Date date_heure_publication, Date date_heure_recup, String resume, String url, String categorie) {
        super(message, date_heure_publication, date_heure_recup, resume, Constant.RSS.toString());
        this.identifiantRSS = identifiantRSS;
        this.url = url;
        this.categorie = categorie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
