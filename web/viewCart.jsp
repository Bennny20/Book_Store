<%-- 
    Document   : viewCart
    Created on : Oct 14, 2021, 11:41:19 AM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="phuongnlt.cart.CartObject"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/viewCart.css"/>
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart</h1>
        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
            <c:set var="items" value="${cart.items}"/>
            <c:if test="${not empty items}">
                <form action="RemoveBookProcess">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Title</th>
                                <th>Quantity</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                        .</td>
                                    <td>
                                        ${item.key}
                                    </td>
                                    <td>
                                        ${item.value}
                                    </td>
                                    <td>
                                        <input type="checkbox" name="chkItem" 
                                               value="${item.key}" />
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tr>
                            <td colspan="3">
                                <a href="ShowBookProcess">Add More Books to Your Cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove Selected Books" name="btAction" />
                            </td>
                        </tr>
                    </table>
                </form>
                <form action="CheckOutBookProcess">
                    Name*: <input type="text" name="txtName" value="" required placeholder="Phượng, Tiên, e.g..."/><br/>
                    Address*: <input type="text" name="txtAddress" value="" required placeholder="HCM, HN, e.g..."/><br/>
                    <input type="submit" value="Check Out" name="btAction" />
                </form>
            </c:if>
            <c:if test="${empty items}">
                <h2>
                    No items is Existed!!
                </h2>
                <a href="ShowBookProcess">Add more book to your cart!!</a>
            </c:if>
        </c:if>
        <c:if test="${empty cart}">
            <h2>
                No cart is Existed!!
            </h2>
        </c:if>
    </body>
</html>
