/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.gpa.servlet;

import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.exceptions.CommitException;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import ch.hearc.ig.gpa.services.MessageService;
import ch.hearc.ig.gpa.twitter.RecuperationTwitter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.TwitterException;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
@WebServlet(name = "fil_actualite", urlPatterns = {"/fil_actualite"})
public class fil_actualite extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws ch.hearc.ig.gpa.exceptions.ConnectionProblemException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ConnectionProblemException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);

            //Header
            getServletContext().getRequestDispatcher("/includes/header.jsp").include(request, response); // Inlcude header

            //Navbar
            getServletContext().getRequestDispatcher("/includes/navbar.jsp").include(request, response); // Inlcude navbar

            //Content
            out.println("Fil d'actualité Servlet");

            Set<Message> searchResultsList = MessageService.findAllMessage();
            RequestDispatcher disp;
            disp = getServletContext().getRequestDispatcher("/includes/filactualite.jsp");
            request.setAttribute("my.search.results", searchResultsList);
            disp.forward(request, response);

            //Footer
            getServletContext().getRequestDispatcher("/includes/footer.jsp").include(request, response);

           
        }    }

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
        try {
            processRequest(request, response);
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(fil_actualite.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ConnectionProblemException ex) {
            Logger.getLogger(fil_actualite.class.getName()).log(Level.SEVERE, null, ex);
        }
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
