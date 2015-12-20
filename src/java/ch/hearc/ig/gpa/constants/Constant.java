/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.constants;

/**
 * Cette classe contient toutes les constantes utilisé par l'application
 *
 * @author romain.ducret
 */
public enum Constant {

    RSS("Rss"),
    FACEBOOK("Facebook"),
    TWITTER("Twitter");
    
    private String name ="";

    /**
     * Constructeur de l'enumration
     *
     * @param name
     */
    Constant(final String name) {
        this.name = name;
    }
    
    /**
     * Retourne le nom de l'enum
     * 
     * @return 
     */
    @Override
    public String toString(){
        return name;
    }

}
