<%-- 
    Document   : search
    Created on : Oct 5, 2021, 11:37:24 AM
    Author     : Admin
--%>

<%--<%@page import="phientq.register.RegisterDTO"%>--%>
<%--<%@page import="java.util.List"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href = "./css/search.css">
        <title>search</title>
    </head>
    <body>
        <span>
        Welcome, ${sessionScope.Fullname} </br>

        </span>
        <form action="LogOutProcess">
            <input type="submit" value="Log out" name="btAction" />
        </form>
        <h1>Search Page</h1>
        <form action="SearchProcess">
            Search value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}" >
            <c:set var="result" value="${requestScope.SEARCH_RESULT}" ></c:set>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Full name</th>
                        <th>Role</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                    <form action="UpdateAccountProcess">
                        <tr>
                            <td>
                                ${counter.count}
                            </td>
                            <td>
                                ${dto.username}
                                <input type="hidden" name="txtUsername" value="${dto.username}" />
                            </td>
                            <td>
                                <input type="text" name="txtPassword" value="${dto.password}" />
                            </td>
                            <td>
                                ${dto.fullName}
                            </td>
                            <td>
                                <input type="checkbox" name="chkAdmin" value="ON" 
                                       <c:if test="${dto.role}">
                                           checked="checked"
                                       </c:if>
                                       />
                            </td>
                            <td>
                                <c:url var="urlRewriting" value="DeleteAccountProcess">
                                    <c:param name="btAction" value="delete"/>
                                    <c:param name="pk" value="${dto.username}"/>
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                </c:url>
                                <a href="${urlRewriting}">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="btAction" />
                                <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                            </td>
                        </tr>
                    </form>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:set var="errors" value="${requestScope.UPDATEERRORS}"/>
    <c:if test="${not empty errors.passwordLengthViolent}" >
        <p>
            ${errors.passwordLengthViolent}
        </p>
    </c:if>
    <c:if test="${not empty errors.passwordInvalidate}" >
        <p>
            ${errors.passwordInvalidate}
        </p>
    </c:if>
    <c:if test="${empty result}">
        <h2>No record is matched!!!</h2>
    </c:if>
</c:if>
</body>
</html>
