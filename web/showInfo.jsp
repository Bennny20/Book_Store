<%-- 
    Document   : showInfo
    Created on : Oct 29, 2021, 4:18:41 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/showInfo.css"/>
        <title>Info</title>
    </head>
    <body>
        <div>
        <h3>Hello, ${sessionScope.Fullname}</h3>
        <p>Sorry you are not Admin so you cannot allow to use any feature!!!</p>
        </div>
        <form action="LogOutProcess">
        <input type="submit" value="Log out" name="btAction" />
        </form>
    </body>
</html>
