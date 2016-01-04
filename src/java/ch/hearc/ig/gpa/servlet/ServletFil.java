/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.servlet;

import ch.hearc.ig.gpa.RSS.RecuperationRSS;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.services.MessageService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;

/**
 * Servlet utilisé sur la page d'index pour affiche le top5 et le reste de
 * l'actu
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class ServletFil extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            //Ajout de l'entête
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-th-list'></span> Fil d'actualité", out, request, response);
            
            
            // ---------------------------  TOP 5
            // Récupère la liste du top 5 des messages
            List<Message> listeMessageTop5 = MessageService.findTop5Message();

            // Titre
            out.println("<h2>Top 5 des actualités</h2>");

            // Affiche le tableau et l'alimente
            HtmlHttpUtils.doTableHeader(out);
            for (int compteur = 0; compteur < listeMessageTop5.size(); compteur++) {
                HtmlHttpUtils.doTableRow(out, listeMessageTop5.get(compteur).getSourceType(), listeMessageTop5.get(compteur).getResume(), listeMessageTop5.get(compteur).getMessage() + " - " + listeMessageTop5.get(compteur).getDate_heure_publication(), "top5" + compteur);
            }

            HtmlHttpUtils.doTableFooter(out);

            // ---------------------------  Reste de l'actualité
            //Récupère la liste de tout les messages
            List<Message> listeMessage = MessageService.findAllMessage();

            // Titre
            out.println("<h2>Reste de l'actualité</h2>");

            // Affiche le tableau et l'alimente
            HtmlHttpUtils.doTableHeader(out); // En-tête du tableau
            for (Message message : listeMessage) {
                HtmlHttpUtils.doTableRow(out, message.getSourceType(), message.getResume(), message.getMessage() + " - " + message.getDate_heure_publication(), "col3");
            }
            HtmlHttpUtils.doTableFooter(out); // Fin du tableau

            throw new ConnectionProblemException("Test");
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(ServletFil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
