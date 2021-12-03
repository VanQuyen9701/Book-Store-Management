<%-- 
    Document   : createUser
    Created on : Jul 2, 2021, 10:56:57 AM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create new User Page</title>
    </head>
    <body>
        <h1>Create new user !</h1>
        <form action="MainController" method="POST">
            User ID <input type="text" name="userID" required=""/> 
            ${requestScope.USER_ERROR.getUserIdError()}</br>
            Full-name <input type="text" name="fullname" required=""/> 
            ${requestScope.USER_ERROR.getNameError()}</br>
            Address <input type="text" name="address" required=""/> 
            ${requestScope.USER_ERROR.getAddressError()}</br>
            Password <input type="text" name="password" required=""/> </br>
            Confirm your password <input type="text" name="confirmPassword" required=""/> 
            ${requestScope.USER_ERROR.getConfirmPasswordError()}</br>
            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>
        </form>
        <a href="login.html">Cancel Create</a>
    </body>
</html>
