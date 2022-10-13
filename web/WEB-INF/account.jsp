<%-- 
    Document   : account
    Created on : Oct 6, 2022, 1:21:26 PM
    Author     : ilyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Information</title>
    </head>
    <body>
        <h1>${account.name}</h1>
    <ul>
        <li>username: ${account.username}</li>
        <li>password: ${account.password}</li>
        <li>email: <a href="mailto:${account.email}">${account.email}</a></li>
    </ul>
    <div>
        
    </div>
    </body>
</html>
