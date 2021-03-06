/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.servlet;

import ch.hearc.ig.gpa.business.Message;
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

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class ServletFilVeilleTechno extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            //Ajout de l'ent�te
            HtmlHttpUtils.doHeader("<span class='glyphicon glyphicon-th-list'></span> Fil d'actualit� - veille technologique", out, request, response);

            // ---------------------------  TOP 5
            // R�cup�re la liste du top 5 des messages
            List<Message> listeMessageTop5 =  MessageService.findAllMessage(true,true,false);

            //Bouton qui met � jour les donn�es
            out.println("<a class='btn btn-default' href='updateLoading.jsp'>Update Data</a>");

            // Titre
            out.println("<h2>Top 5 des actualit�s</h2>");

            // Affiche le tableau et l'alimente
            HtmlHttpUtils.doTableHeader(out);
            for (int compteur = 0; compteur < listeMessageTop5.size(); compteur++) {
                HtmlHttpUtils.doTableRow(out, listeMessageTop5.get(compteur), "top5" + compteur);
            }

            HtmlHttpUtils.doTableFooter(out);

            // ---------------------------  Reste de l'actualit�
            //R�cup�re la liste de tout les messages
            List<Message> listeMessage = MessageService.findAllMessage(false,true,false);

            // Titre
            out.println("<h2>Reste de l'actualit�</h2>");

            // Affiche le tableau et l'alimente
            HtmlHttpUtils.doTableHeader(out); // En-t�te du tableau
            for (int compteur = 0; compteur < listeMessage.size(); compteur++) {
                HtmlHttpUtils.doTableRow(out, listeMessage.get(compteur), "allMessages" + compteur);
            }
            HtmlHttpUtils.doTableFooter(out); // Fin du tableau
            HtmlHttpUtils.doFooter(out);

        } catch (ConnectionProblemException ex) {
            Logger.getLogger(ServletFilVeilleTechno.class.getName()).log(Level.SEVERE, null, ex);
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
