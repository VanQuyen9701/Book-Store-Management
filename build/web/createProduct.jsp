<%-- 
    Document   : createProduct
    Created on : Jul 12, 2021, 10:48:24 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not sessionScope.LOGIN_USER.roleID.equals('AD')}">
            <c:redirect url="login.html"/>
        </c:if>
        <h1>Create new Product !</h1>
        <form action="MainController" method="POST">
            Product ID <input type="text" name="bookID" required=""/> 
            ${sessionScope.PRODUCT_ERROR}</br>
            Name <input type="text" name="name" required=""/> </br>
            Price <input type="number" name="price" required=""/> </br>
            Quantity <input type="number" name="quantity" required=""/> </br>
            Category <input type="text" name="catagory" required=""/> </br>
            <input type="submit" name="action" value="Insert"/>
            <input type="reset" value="Reset"/>
        </form>
        <a href="admin.jsp">Cancel Create</a>
    </body>
</html>
