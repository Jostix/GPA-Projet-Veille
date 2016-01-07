/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thierry.hubmann
 */
public class StringServices {

    /**
     * Méthode servant à limiter la taille d'un String pour faire un résumé par exemple
     * @param value
     * @param length
     * @return 
     */
    public static String limit(String value, int length) {
        StringBuilder buf = new StringBuilder(value);
        if (buf.length() > length) {
            buf.setLength(length);
            buf.append("...");
        }

        return buf.toString();
    }
    
    /**
     * Cette méthode retourne un boolean en selon si un ou des mots clés sont
     * retrouvés dans le message
     * @param message le message
     * @param keywords le ou les mots clés
     * @return 
     */
    public static boolean containsKey(String message, String[] keywords){
        StringBuilder regex = new StringBuilder();
        //Création de la regex
        for (String keyword : keywords) {
            regex.append("(");
            regex.append(keyword.toUpperCase());
            regex.append(")|");
        }
        regex.setLength(regex.length()-1);
        Pattern p = Pattern.compile(regex.toString());
        //Recherche dans le message
        Matcher m = p.matcher(message.toUpperCase()); 
        //Retourne true si on a au moins une correspondance
        return m.find();
    }
}
