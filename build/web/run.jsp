<%-- 
    Document   : run
    Created on : Sep 23, 2013, 10:44:01 PM
    Author     : Wolverine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
<%
String program = request.getParameter("program"); 
String classname=request.getParameter("classname");
out.println(program);
%>