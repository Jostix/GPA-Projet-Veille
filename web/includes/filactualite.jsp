<%-- 
    Document   : filactualite
    Created on : 6 nov. 2015, 11:54:41
    Author     : Romain Ducret <romain.ducret1@he-arc.ch>
--%>

<%@page import="java.util.Set"%>
<%@page import="ch.hearc.ig.gpa.business.Message"%>
<%@page import="ch.hearc.ig.gpa.business.Message"%>
<table class="table table-striped">
    <thead>
        <tr>
            <td>Catégorie</td>
            <td>Description</td>
            <td>Action</td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Veille Protection</td>
            <td>Nouveau prototype</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
        <tr>
            <td>Veille Protection</td>
            <td>Démission</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
        <tr>
            <td>Veille Protection</td>
            <td>Manifestation</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
        <tr>
            <td>Veille Protection</td>
            <td>Grève</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
        <tr>
            <td>Veille Protection</td>
            <td>Grève</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
        <tr>
            <td>Veille Protection</td>
            <td>Promotion</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
       
        <tr>
            <td>Veille Protection</td>
            <td>Promotion</td>
            <td><button type="button" class="btn-xs btn btn-primary"><span class="glyphicon glyphicon-search" aria-hidden="true"></span> Détail</button></td>
        </tr>
    </tbody>
</table>


 <% Set<Message> myList = (Set<Message>) request.getAttribute("my.search.results"); 
 myList.size();
int test = 12;
out.println(test);

 %>