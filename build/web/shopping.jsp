<%-- 
    Document   : shopping
    Created on : Jul 5, 2021, 7:17:43 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <body>
        <h1>Welcome to Shop</h1>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not sessionScope.LOGIN_USER.roleID.equals('US')}">
            <c:redirect url="login.html"/>
        </c:if>

        <form action="MainController">
            Search product :</br>
            <input type="text" name="searchProduct" value="${param.searchProduct}"/>
            <input type="submit" name="action" value="SearchProduct"/>
        </form>

        <c:if test="${sessionScope.LIST_PRODUCT != null}">
            <c:if test="${not empty sessionScope.LIST_PRODUCT}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Price/Product</th>
                            <th>Quantity in Store</th>
                            <th>Quantity</th>
                            <th>Add to Cart</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${sessionScope.LIST_PRODUCT}">
                        <form action="MainController">
                            <tr>
                                <td><input type="text" name="bookID" value="${book.bookID}" readonly=""/>
                                </td>
                                <td><input type="text" name="bookName" value="${book.name}" readonly=""/>
                                </td>
                                <td><input type="text" name="price" value="${book.price}" readonly=""/>
                                </td>
                                <td><input type="text" name="quantity" value="${book.quantity}" readonly=""/>
                                </td>
                                <td>
                                    <input type="number" name="quantityToCart" value="1"/>
                                </td>
                                <td>
                                    <input type="hidden" name="search" value="${param.searchProduct}"/>
                                    <input type="hidden" name="bookID" value="${book.bookID}"/>
                                    <input type="hidden" name="quantityStore" value="${book.quantity}"/>
                                    <input type="submit" name="action" value="Add to Cart"/>
                                </td>
                            </tr> 
                        </form>

                    </c:forEach>

                </tbody>
            </table>

        </c:if>
    </c:if>
    <h1>${sessionScope.SHOPPING_MESSAGE}</h1>
    <form action="MainController">
        <input type="submit" name="action" value="View Cart"/>
    </form>

    <a href="user.jsp">Cancel Shopping</a>
</body>
</html>
