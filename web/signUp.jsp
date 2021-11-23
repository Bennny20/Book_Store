<%-- 
    Document   : signUp
    Created on : Oct 21, 2021, 11:48:02 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Sign up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/signUp.css"/>
    </head>
    <body>
        <form action="SignUpProcess" method="POST"><br/>
            <h1>Create new account</h1>
            <c:set var="errors" value="${requestScope.SIGNUPERRORS}" />
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" required placeholder="e.g. 6 - 30 chars"/><br/>
            <c:if test="${not empty errors.usernameLengthViolent}">
                <font color="red">
                ${errors.usernameLengthViolent}
                </font><br/>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" required placeholder="e.g. 6 - 20 chars"/><br/>
            <c:if test="${not empty errors.passwordLengthViolent}">
                <font color="red">
                ${errors.passwordLengthViolent}
                </font><br/>
            </c:if>
            Confirm* <input type="password" name="txtConfirm" value="" required/><br/>
            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font><br/>
            </c:if>
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" required placeholder="e.g. 2 - 50 chars"/><br/>
            <c:if test="${not empty errors.fullNameLengthViolent}">
                <font color="red">
                ${errors.fullNameLengthViolent}
                </font><br/>
            </c:if>
            <input type="submit" value="Sign Up" name="btAction" />
            <input type="reset" value="Reset" /><br/>
            Already have Account? <a href="LoginPage">Click here!</a>
        </form>
        <c:if test="${not empty errors.usernameIsExisted}">
            <font color="red">
            ${errors.usernameIsExisted}
            </font>
        </c:if>
    </body>
</html>
