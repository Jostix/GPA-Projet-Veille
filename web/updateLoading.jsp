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
        <p class=''>Le fil d'actualit� est entrain d'�tre mis � jour</p>
        <form name="auto" action="Update" />
        <script>
            document.auto.submit();
        </script>
    </body>
</html>
