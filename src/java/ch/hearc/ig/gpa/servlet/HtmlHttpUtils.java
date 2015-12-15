/*
 *  Translait SA vous pr�sente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( �w� )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class HtmlHttpUtils extends HttpServlet {

    private String col1 = null;

    //Cette m�thode est appell� sur en en-t�te sur chaque servlet
    public static void doHeader(String titre, PrintWriter out, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Projet Veille </title>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css'></link>");
        out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css'></link>");
        out.println("<script type='text/javascript' href='http://code.jquery.com/jquery-2.1.4.min.js'></script>");
        out.println("<script type='text/javascript' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");

        //Inlcusion de la navigation
        request.getRequestDispatcher("includes/navbar.jsp").include(request, response);

        out.println("<h1 class='page-header'>" + titre + "</h1><br>");
    }

    //M�thode qui permet d'afficher l'en-t�te d'un accordeon
    public static void doAccordeonHeader(PrintWriter out, final String id) {
        out.println("<div class='panel-group' id=" + id + " role='tablist' aria-multiselectable='true'>");
    }

    public static void doAccordeoRow(PrintWriter out, final String parentId, final int rowNumber, final String menuTitle, final String content) {
        out.println("<div class='panel panel-default'>");
        out.println("   <div class='panel-heading' role='tab' id='heading" + rowNumber + "'>");
        out.println("       <h4 class='panel-title'>");
        out.println("         <a class='collapsed' role='button' data-toggle='collapse' data-parent=" + parentId + " href='#collapse" + rowNumber + "' aria-expanded='false' aria-controls='collapse" + rowNumber + "'>");
        out.println(menuTitle);
        out.println("         </a>");
        out.println("       </h4>");
        out.println("   </div>");
        out.println("   <div id='collapse" + rowNumber + "' class='panel-collapse collapse' role='tabpanel' aria-labelledby='heading" + rowNumber + "'>");
        out.println("        <div class='panel-body'>");
        out.println(content);
        out.println("        </div>");
        out.println("   </div>");

        out.println("</div>");
       
    }

    //M�thode qui permet d'affiche le footer d'un accordeon
    public static void doAccordeonFooter(PrintWriter out) {
        out.println("</div>");
    }

    //M�thode qui permet d'afficher l'ent�te d'un tableau
    public static void doTableHeader(PrintWriter out) {
        out.println("<table class=\"table table-striped\">");
        out.println("  <thead>");
        out.println("    <tr>");
        out.println("      <td>Cat�gorie</td>");
        out.println("      <td>R�sum�</td>");
        out.println("      <td>Description</td>");
        out.println("      <td>Action</td>");
        out.println("    </tr>");
        out.println("  </thead>");
    }

    //M�thode qui permet d'afficher une ligne dans un tableau
    public static void doTableRow(PrintWriter out, final String cat, final String col1, final String col2, final String actionLink) {
        out.println("<tr>");
        out.println("<td>" + cat + "</td>");
        out.println("<td>" + col1 + "</td>");
        out.println("<td>" + col2 + "</td>");
        out.println("<td><a class='btn-xs btn btn-primary' href='" + actionLink + "'><span class='glyphicon glyphicon-search' aria-hidden='true'></span> D�tail</a></td>");
        out.println("</tr>");
    }

    //M�thode qui affiche le footer d'un tableau
    public static void doTableFooter(PrintWriter out) {
        out.println("</tbody>");
        out.println("</table>");
    }

    //Cette m�thode permet d'afficher les boutons en bas de tous les servlet
    public static void doFooter(PrintWriter out) {
        out.println("<div><a class='btn btn-info' href='ServletListePersonne'><span class='glyphicon glyphicon-th-list'></span> Accueil</a> &nbsp;<a class='btn btn-warning' href='ServletLogout'><span class='glyphicon glyphicon-log-out'></span> D�connexion</a></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
