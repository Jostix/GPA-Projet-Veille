/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.servlet;

import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.log.MyLogger;
import ch.hearc.ig.gpa.services.MessageService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class ServletFil extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
//            MessageService.recupMessagesTwitter();
            //Ajout de l'ent�te
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-th-list'></span> Fil d'actualit�", out, request, response);

            // ---------------------------  TOP 5
            // R�cup�re la liste du top 5 des messages
            List<Message> listeMessageTop5 = MessageService.findTop5Message();

            // Titre
            out.println("<h2>Top 5 des actualit�s</h2>");

            //Affiche l'accordeon
            HtmlHttpUtils.doAccordeonHeader(out, "AccordeonTop5");
            for (int compteur = 0; compteur < listeMessageTop5.size(); compteur++) {
                HtmlHttpUtils.doAccordeoRow(out, "AccordeonTop5", compteur, listeMessageTop5.get(compteur).getResume(), listeMessageTop5.get(compteur).getMessage());
            }
            HtmlHttpUtils.doAccordeonFooter(out);

            // Affiche le tableau et l'alimente
            HtmlHttpUtils.doTableHeader(out);
            for (Message message : listeMessageTop5) {
                HtmlHttpUtils.doTableRow(out, "Twitter - Veille Protection", message.getResume(), message.getMessage() + " - " + message.getDate_heure_publication(), "col3");
            }
            HtmlHttpUtils.doTableFooter(out);

            // ---------------------------  Reste de l'actualit�
            //R�cup�re la liste de tout les messages
            List<Message> listeMessage = MessageService.findAllMessage();

            // Titre
            out.println("<h2>Reste de l'actualit�</h2>");

            // Affiche le tableau et l'alimente
            HtmlHttpUtils.doTableHeader(out); // En-t�te du tableau
            for (Message message : listeMessage) {
                HtmlHttpUtils.doTableRow(out, "Twitter - Veille Protection", message.getResume(), message.getMessage() + " - " + message.getDate_heure_publication(), "col3");
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
