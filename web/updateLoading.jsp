<%-- 
    Document   : updateLoading
    Created on : 11 janv. 2016, 14:45:15
    Author     : thierry.hubmann
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="modal-body">
            <h1>Mise à jour</h1>
            <p>Le fil d'actualité est entrain d'être mis à jour...</p>
        </div>

        <form name="auto" action="Update" />
        <script>
            document.auto.submit();
        </script>
    </body>
</html>
