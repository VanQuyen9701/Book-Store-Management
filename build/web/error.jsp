<%-- 
    Document   : error
    Created on : Jun 22, 2021, 12:56:29 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Error : <%= session.getAttribute("ERROR_MESSAGE")%></h1>
        <a href="login.html">Login with another Account !</a>
    </body>
</html>
