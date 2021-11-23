<%-- 
    Document   : shopping
    Created on : Oct 16, 2021, 11:55:28 PM
    Author     : Admin
--%>

<%@page import="phientq.book.BookDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="phientq.book.BookDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/shopping.css" />
        <title>Shopping</title>
    </head>
    <body>
        <h1>Shopping Page</h1>

        <c:set var="list" value="${requestScope.BOOKLIST}" />
        <c:if test="${not empty list}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Book ID</th>
                        <th>Book Name</th>
                        <th>Add book to cart</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${list}" varStatus="counter" >
                    <form action="AddBookProcess">
                        <tr>
                            <td>
                                ${counter.count}
                                .</td>
                            <td>
                                ${dto.bookId}
                                <input type="hidden" name="txtBookID" value="${dto.bookId}" />
                            </td>
                            <td>
                                ${dto.bookName}
                            </td>
                            <td>
                                <input type="submit" value="Add Book to Cart" name="btAction" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
        <br/>   
        <form action="ViewCartPage">
            <input type="submit" value="View your Cart" name="btAction" />
        </form>
    </c:if>
    <c:if test="${empty list}">
        <h2>
            No book existed!!
        </h2>
    </c:if>
</body>
</html>
