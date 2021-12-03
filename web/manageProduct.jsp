<%-- 
    Document   : manageProduct
    Created on : Jul 11, 2021, 9:28:00 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Product Page</title>
    </head>
    <body>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not sessionScope.LOGIN_USER.roleID.equals('AD')}">
            <c:redirect url="login.html"/>
        </c:if>
        <a href="createProduct.jsp">Create new product!</a>
        <form action="MainController">
            Search product :</br>
            <input type="text" name="SearchBook" value="${param.SearchBook}"/>
            <input type="submit" name="action" value="SearchBook"/>
        </form>
        <c:if test="${sessionScope.LIST_PRODUCT_ADMIN != null}">
            <c:if test="${not empty sessionScope.LIST_PRODUCT_ADMIN}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Price/Product</th>
                            <th>Quantity in Store</th>
                            <th>Category</th>
                            <th>status</th>
                            <th>UPDATE</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${sessionScope.LIST_PRODUCT_ADMIN}">
                        <form action="MainController">
                            <tr>
                                <td><input type="text" name="bookID" value="${book.bookID}" />
                                </td>
                                <td><input type="text" name="bookName" value="${book.name}" />
                                </td>
                                <td><input type="number" name="price" value="${book.price}" />
                                </td>
                                <td><input type="number" name="quantity" value="${book.quantity}" />
                                </td>
                                <td><input type="text" name="category" value="${book.catagory}"</td>
                                <td>
                                    <select name="status" >
                                        <option value="${book.status}">${book.status}</option>
                                        <option value="ON">ON</option>
                                        <option value="OFF">OFF</option>
                                    </select>
                                </td>

                                <td>
                                    <input type="hidden" name="search" value="${param.SearchBook}"/>
                                    <input type="hidden" name="bookID" value="${book.bookID}"/>
                                    <input type="submit" name="action" value="UpdateProduct"/>
                                </td>
                            </tr> 
                        </form>

                    </c:forEach>

                </tbody>
            </table>

        </c:if>
    </c:if>
    <a href="admin.jsp">back</a>
</body>
</html>
