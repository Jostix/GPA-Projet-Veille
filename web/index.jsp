<%-- 
    Document   : index
    Created on : 4 janv. 2010, 11:23:22
    Author     : termine
--%>
<%@page import="ch.hearc.ig.gpa.business.RSS"%>
<%@page import="java.util.List"%>
<%@page import="ch.hearc.ig.gpa.RSS.RecuperationRSS"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="ch.hearc.ig.gpa.dao.UserDAOImpl"%>
<%@page import="java.util.Date"%>
<%@page import="ch.hearc.ig.gpa.business.User"%>
<%@page import="ch.hearc.ig.gpa.twitter.*" %>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<!-- <a href="recherchePersonne.html">Recherche Personne</a><br>
 <a href="creationPersonne.html">Creation Personne</a>-->

<%Class.forName("oracle.jdbc.OracleDriver");%>
 
<!-- test -->
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  Launch demo modal
</button>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<%@include file="includes/footer.jsp" %>