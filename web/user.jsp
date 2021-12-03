<%-- 
    Document   : user
    Created on : Jul 2, 2021, 10:49:35 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not sessionScope.LOGIN_USER.roleID.equals('US')}">
            <c:redirect url="login.html"/>
        </c:if>

        <h1>WELCOME ${sessionScope.LOGIN_USER.name} </h1>
        </br>
        <a href="MainController?action=Logout">Logout</a></br>


        <a href="shopping.jsp">Shopping now </a></br>
        </br>
        <a href="viewCart.jsp">View Cart </a></br>
    </body>
</html>
