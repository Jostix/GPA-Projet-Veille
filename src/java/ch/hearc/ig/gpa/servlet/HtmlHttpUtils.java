/*
 *  Translait SA vous présente son projet de gestion de Projet Agile.
 *  Copyleft 2015 Translait.
 *   /\_/\
 * =( °w° )=
 *   )   (  //
 *  (__ __)//
 */
package ch.hearc.ig.gpa.servlet;

import ch.hearc.ig.gpa.business.Hashtag;
import ch.hearc.ig.gpa.business.Message;
import ch.hearc.ig.gpa.business.RSS;
import ch.hearc.ig.gpa.business.TwitterMessage;
import ch.hearc.ig.gpa.business.User;
import ch.hearc.ig.gpa.dbfactory.AbstractDAOFactory;
import ch.hearc.ig.gpa.exceptions.ConnectionProblemException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ensemble d'utilitaires pour l'affichage HTML
 *
 * @author Romain Ducret <romain.ducret1@he-arc.ch>
 */
public class HtmlHttpUtils extends HttpServlet {

    /**
     * Cette méthode est appellé sur en en-tête sur chaque servlet
     *
     * @param titre
     * @param out
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public static void doHeader(String titre, PrintWriter out, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Projet Veille </title>");
        out.println("<script src=\"http://code.jquery.com/jquery-2.1.4.min.js\"></script>");
        out.println("");

        out.println("          <!-- Jquery -->\n"
                + "        <script src=\"http://code.jquery.com/jquery-2.1.4.min.js\"></script>\n"
                + "        \n"
                + "        <!-- Latest compiled and minified CSS -->\n"
                + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\" integrity=\"sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==\" crossorigin=\"anonymous\">\n"
                + "\n"
                + "        <!-- Optional theme -->\n"
                + "        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css\" integrity=\"sha384-aUGj/X2zp5rLCbBxumKTCw2Z50WgIr1vs/PFN4praOTvYXWlVyh2UtNUU0KAUhAX\" crossorigin=\"anonymous\">\n"
                + "\n"
                + "        <link href=\"includes/style.css\" rel=\"stylesheet\"></link>\n"
                + "        \n"
                + "        <!-- Latest compiled and minified JavaScript -->\n"
                + "        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js\" integrity=\"sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==\" crossorigin=\"anonymous\"></script>\n"
                + "        \n"
                + "");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='container'>");

        //Inlcusion de la navigation
        request.getRequestDispatcher("includes/navbar.jsp").include(request, response);

        out.println("<h1 class='page-header'>" + titre + "</h1><br>");
    }

    /**
     * Méthode qui permet d'afficher l'en-tête d'un accordeon html
     *
     * @param out
     * @param id
     */
    public static void doAccordeonHeader(PrintWriter out, final String id) {
        out.println("<div class='panel-group' id=" + id + " role='tablist' aria-multiselectable='true'>");
    }

    /**
     * Affiche le contenu d'un accordeon html
     *
     * @param out
     * @param parentId
     * @param rowNumber
     * @param menuTitle
     * @param content
     */
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

    /**
     * Méthode qui permet d'affiche le footer d'un accordeon
     *
     * @param out
     */
    public static void doAccordeonFooter(PrintWriter out) {
        out.println("</div>");
    }

    /**
     * Affiche l'entête d'un tableau html
     *
     * @param out
     */
    public static void doTableHeader(PrintWriter out) {
        out.println("<table class=\"table table-striped\">");
        out.println("  <thead>");
        out.println("    <tr>");
        out.println("      <td class='text-center'>Source</td>");
        out.println("      <td>Résumé</td>");
        out.println("      <td>Date</td>");
        out.println("      <td>Action</td>");
        out.println("    </tr>");
        out.println("  </thead>");
    }

    /**
     * Méthode qui permet d'afficher une ligne dans un tableau
     *
     * @param out
     * @param message
     * @param actionLink
     */
    public static void doTableRow(PrintWriter out, final Message message, final String actionLink) {
        //Attributs classe message
        String cat = message.getSourceType();
        String resume = message.getResume();
        String date = message.getDate_heure_publication().toString();
        String contenuMessage = message.getMessage();
        
        //Rend les url du contenu des messages cliquable
        contenuMessage.replaceAll("(\\A|\\s)((http|https|ftp|mailto):\\S+)(\\s|\\z)","$1<a href=\"$2\">$2</a>$4");
        resume.replaceAll("(\\A|\\s)((http|https|ftp|mailto):\\S+)(\\s|\\z)","$1<a href=\"$2\">$2</a>$4");
        
        //Attributs classe RSS
        String rssURL = "";

        //Attribut classe Twitter
        User auteur = null;
        List<Hashtag> hashtags = null;

        //Récupère les attributs RSS
        if (message.getClass().equals(RSS.class)) {
            RSS rss = (RSS) message;
            rssURL = rss.getUrl();
        }

        //Récupère les attributs Twitter
        if (message.getClass().equals(TwitterMessage.class)) {
            TwitterMessage tweet = (TwitterMessage) message;
            try {
                auteur = AbstractDAOFactory.getDAOFactory().getMessageDAO().getUserByMessage(message);
                
                        } catch (ConnectionProblemException ex) {
                Logger.getLogger(HtmlHttpUtils.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HtmlHttpUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
            hashtags = tweet.getHashtags();
//            auteur = tweet.getUser();
        }

        out.println("<tr class='" + getCategoryColor(cat) + "'>");

        //Test conditionel pour afficher le logo de la catégorie
        if (cat.equals(ch.hearc.ig.gpa.constants.Categories.TWITTER.toString())) {
            out.println("<td class='text-center'><img src='includes/image/twitter.png' style='height:25px;width:25px;' alt='" + cat + "' /></td>");
        } else if (cat.equals(ch.hearc.ig.gpa.constants.Categories.RSS.toString())) {
            out.println("<td class='text-center'><img src='includes/image/rss.png' style='height:25px;width:25px;' alt='" + cat + "' /></td>");
        } else {
            out.println("<td>" + cat + "</td>");
        }

        out.println("<td>" + resume + "</td>");
        out.println("<td>" + date + "</td>");
        out.println("<td><button class='btn-xs btn btn-primary' type='button' data-toggle=\"modal\" data-target='#" + actionLink + "'><span class='glyphicon glyphicon-search' aria-hidden='true'></span> Détail</button></td>");
        out.println("</tr>");

        //Construction de la chaine qui sera utilisé dans la description de la fenêtre modal
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<b>Date: </b>");
        stringBuilder.append(date);
        stringBuilder.append("<br/>");

        if (message.getClass().equals(RSS.class)) {

            //Affichage de l'url
            stringBuilder.append("<b>URL: </b>");
            stringBuilder.append("<a target='_blank' href='");
            stringBuilder.append(rssURL);
            stringBuilder.append("'>Lien</a>");
            stringBuilder.append("<br/>");
        }
        if (message.getClass().equals(TwitterMessage.class)) {

            //Affichage des hashtags
            stringBuilder.append("<b>Hashtags: </b>");
            if (hashtags != null && hashtags.size() > 0) {
                for (Hashtag hashtag : hashtags) {
                    stringBuilder.append(hashtag);
                    stringBuilder.append(", ");
                }
            } else {
                stringBuilder.append("Aucun Hashtag");
            }
            stringBuilder.append("<br/>");

            //Affichage de l'utilisateur
            stringBuilder.append("<b>Auteur: </b>");
            if (auteur == null) {
                stringBuilder.append("Aucun auteur");
            } else {
                stringBuilder.append(auteur.getUsername_twitter());
            }
            stringBuilder.append("<br/>");
        }

        stringBuilder.append(contenuMessage);

        //Appelle la méthode qui crée la fenêtre modal
        doModal(out, actionLink, resume, stringBuilder.toString());
    }

    /**
     * Méthode qui permet d'ajouter une fenêtre modal qui s'ouvre lordque l'on
     * clique sur le bouton détail de chaque ligne.
     *
     * @param out
     * @param modalId
     * @param modalTitle
     * @param modalBody
     */
    private static void doModal(PrintWriter out, final String modalId, final String modalTitle, final String modalBody) {
        out.println(
                "<div class=\"modal fade\" id='" + modalId + "' tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\">\n"
                + "  <div class=\"modal-dialog\" role=\"document\">\n"
                + "    <div class=\"modal-content\">\n"
                + "      <div class=\"modal-header\">\n"
                + "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n"
                + "        <h4 class=\"modal-title\" id=\"myModalLabel\">" + modalTitle + "</h4>\n"
                + "      </div>\n"
                + "      <div class=\"modal-body\">\n"
                + modalBody
                + "      </div>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>");
    }

    /**
     * Méthode qui permet de récupèrer la class qui donnera la couleur à la
     * ligne du tableau
     *
     * @param sourceType
     * @return
     */
    private static String getCategoryColor(final String sourceType) {
        String rowColor = "";

        if (sourceType.equals(ch.hearc.ig.gpa.constants.Categories.valueOf("TWITTER").toString())) {
            rowColor = "success";
        } else if (sourceType.equals(ch.hearc.ig.gpa.constants.Categories.valueOf("FACEBOOK").toString())) {
            rowColor = "info";

        } else if (sourceType.equals(ch.hearc.ig.gpa.constants.Categories.valueOf("RSS").toString())) {
            rowColor = "danger";
        }

        return rowColor;
    }

    /**
     * Méthode qui affiche le footer d'un tableau
     *
     * @param out
     */
    public static void doTableFooter(PrintWriter out) {
        out.println("</tbody>");
        out.println("</table>");
    }

    /**
     * Cette méthode permet d'afficher les boutons en bas de tous les servlet
     *
     * @param out
     */
    public static void doFooter(PrintWriter out) {
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

}
