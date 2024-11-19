<%@ page import="am.itspace.studentlessonservlet1.model.Lessons" %>
<%@ page import="java.sql.Time" %>
<%@ page import="am.itspace.studentlessonservlet1.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: NITRO
  Date: 15.11.2024
  Time: 20:24
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
<%Lessons lesson = (Lessons) request.getAttribute("lesson");%>
<nav></nav>
<div class="main_div">
    <h1>add_lesson</h1>
    <div class="buts">
        <a href="/" class="a_but"><p>home</p></a>
        <a href="/lessons" class="a_but"><p>lessons</p></a>
    </div>

    <form id="form_1" action="editeLessons" method="post">
        <input type="hidden" name="id" value="<%=lesson.getId()%>">
        <input type="text" placeholder="name" name="name" value="<%=lesson.getLecturerName()%>">
        <input type="time" name="time" value="<%=DateUtil.dateForTime(lesson.getDuration())%>">
        <input type="text" placeholder="price" name="price" value="<%=lesson.getPrice()%>">
        <br>
        <input type="submit" value="edit" id="add_1">
    </form>

</div>
</body>
</html>
