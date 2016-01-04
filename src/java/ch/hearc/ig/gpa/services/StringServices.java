/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.services;

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
}
