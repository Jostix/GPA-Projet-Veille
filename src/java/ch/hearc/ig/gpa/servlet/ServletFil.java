/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.servlet;

import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
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
            //Ajout de l'entête
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-th-list'></span> Fil d'actualité", out, request, response);

            //Récupère la liste des messages
            List<Message> listeMessage = MessageService.findAllMessage();
            Set<Message> listeMdessage;
            
            //Inlcusion du top 5
            out.println("<h2>Top 5 des actualités</h2>");

            //Affichage du tableau top 5
            HtmlHttpUtils.doTableHeader(out);
            HtmlHttpUtils.doTableRow(out, "cat", "colonne1", "colonne2", "col3");
            HtmlHttpUtils.doTableFooter(out);

            //Affichage du reste de l'actualité
            out.println("<h2>Reste de l'actualité</h2>");
            HtmlHttpUtils.doTableHeader(out);
            for (int compteur = 0; compteur < listeMessage.size(); compteur++) {
                HtmlHttpUtils.doTableRow(out, "Twitter - Veille Protection",  listeMessage.get(compteur).getResume(), listeMessage.get(compteur).getMessage() + " - " + listeMessage.get(compteur).getDate_heure_publication() , "col3");
            }
            HtmlHttpUtils.doTableFooter(out);

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
