<%@ page import="com.mysql.cj.Session" %><%--
  Created by IntelliJ IDEA.
  User: NITRO
  Date: 19.11.2024
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="../style/style2.css"%>
    </style>
</head>
<body>
<nav></nav>
<h1>login</h1>
<br>
<% if(request.getAttribute("msg") != null){%>
    <p style="color: red"><%=request.getAttribute("msg")%></p>
<%}%>
<form action="/login" method="post">
    email: <input type="text" name="email">
    password: <input type="password" name="password">
    <input type="submit" value="enter">
</form>
<a href="/registration" class="a_but" id="registration"><p>registration</p></a>

</body>
</html>
