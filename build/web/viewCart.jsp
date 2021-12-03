<%-- 
    Document   : viewCart
    Created on : Jul 5, 2021, 7:17:58 PM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <h1>This is your cart !</h1>
        <c:if test="${empty sessionScope.LOGIN_USER}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${not sessionScope.LOGIN_USER.roleID.equals('US')}">
            <c:redirect url="login.html"/>
        </c:if>

        <c:if test="${empty sessionScope.CART}">
            <h1>
                You did not select any product!
            </h1>
            <a href="shopping.jsp">Add new product</a>
        </c:if>
        <c:if test="${not empty sessionScope.CART}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Product name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Modify</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="${0}"/>
                    <c:forEach var="book" varStatus="counter" items="${sessionScope.CART.getCart()}">
                        <c:set var="total" value="${total + book.value.getPrice()*book.value.getQuantity()}"/>
                    <form action="MainController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                <input type="text" name="bookID" value="${book.value.getBookID()}" readonly=""/>
                            </td>
                            <td>
                                <input type="text" name="bookName" value="${book.value.getName()}" readonly=""/>
                            </td>
                            <td>
                                <input type="number" name="quantityInCart" value="${book.value.getQuantity()}" />
                            </td>
                            <td>
                                <input type="text" name="price" value="${book.value.getPrice()}" readonly=""/>
                            </td>
                            <td>
                                <input type="text" name="total" value="${book.value.getQuantity() * book.value.getPrice()}" readonly=""/>
                            </td>
                            <td>
                                <input type="submit" name="action" value="Modify"/>
                            </td>
                            <td>
                                <input type="submit" name="action" value="Remove"/>
                            </td>
                        </tr>

                    </form>

                </c:forEach>

            </tbody>
        </table>

    </c:if>
    <c:if test="${total>0}">
        <h1>Total: <c:out value="${total}"/></h1>
    </c:if>
    <a href="shopping.jsp">Add more product</a>
    </br>
    </br>
    <form action="MainController">
        <input type="hidden" name="quantityStore" value="${param.quantityStore}"/>
        <input type="submit" name="action" value="Check Out"/>
    </form>
    ${sessionScope.CHECK_OUT}
</body>
</html>
