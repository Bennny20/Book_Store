<%-- 
    Document   : confirm
    Created on : Oct 29, 2021, 10:02:38 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href = "./css/confirm.css">
        <title>Confirm password</title>
    </head>
    <body>
        <form action="ConFirmDeleteAccountProcess">
            <p>Are you sure to delete account ${sessionScope.USER} ?</p>
            <input type="submit" value="Yes" name="btAction" />
            <input type="submit" value="No" name="btAction" />
        </form>
    </body>
</html>
