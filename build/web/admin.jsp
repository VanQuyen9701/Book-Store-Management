<%-- 
    Document   : admin
    Created on : Jul 2, 2021, 10:49:22 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not sessionScope.LOGIN_USER.roleID.equals('AD')}">
            <c:redirect url="login.html"/>
        </c:if>

        <h1>Welcome :${sessionScope.LOGIN_USER.name}</h1>

        <a href="MainController?action=Logout">Logout</a>
        </br>            </br>
        <form action="MainController">
            search <input type="text" name="search" value="${param.search}"/>
            <input type="submit" name="action" value="Search"/>
        </form>
        </br>
        <c:if test="${requestScope.LIST_USER != null}">
            <c:if test="${not empty requestScope.LIST_USER}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No </th>
                            <th>UserID</th>
                            <th>Full name</th>
                            <th>Address</th>
                            <th>roleID</th>
                            <th>Password</th>
                            <th>Status</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                        <form action="MainController">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${user.id}</td>
                                <td>
                                    <input type="text" name="name" value="${user.name}"/>
                                </td>
                                <td>
                                    <input type="text" name="address" value="${user.address}"/> 
                                </td>
                                <td>
                                    <input type="text" name="roleID" value="${user.roleID}"/> 
                                </td>
                                <td>${user.password}</td>
                                <td>
                                    <c:if test="${user.roleID eq 'AD'}">
                                        <input type="text" name="status" value="${user.status}" readonly=""/> 
                                    </c:if>
                                    <c:if test="${user.roleID eq 'US'}">
                                        <select name="status" >
                                            <option value="${user.status}">${user.status}</option>
                                            <option value="ON">ON</option>
                                            <option value="OFF">OFF</option>
                                        </select>
                                    </c:if>

                                </td>
                                <td>
                                    <input type="hidden" name="userID" value="${user.id}"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                    <input type="submit" name="action" value="Update"/>
                                </td>
                            </tr>
                        </form>

                    </c:forEach>

                </tbody>
            </table>

        </c:if>
    </c:if>
    </br>
    <a href="manageProduct.jsp">Manage your product !</a>
</body>
</html>
