<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet1.model.Lessons" %><%--
  Created by IntelliJ IDEA.
  User: NITRO
  Date: 15.11.2024
  Time: 23:33
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
<div class="main_div">
  <h1>add_students</h1>
  <div class="buts">
    <a href="/" class="a_but"><p>home</p></a>
    <a href="/students" class="a_but"><p>students</p></a>
  </div>
  <%if(request.getAttribute("msg") != null){%>
      <p style="color: red"><%=request.getAttribute("msg")%></p>
  <%}%>
  <form action="addStudents" method="post" id="form_2">
    <input type="text" name="name" placeholder="name">
    <input type="text" name="surname" placeholder="surname">
    <input type="email" name="email", placeholder="email">
    <input type="number" name="age" placeholder="age">
    <%List<Lessons> lessons = (List<Lessons>) request.getAttribute("lessons");%>
    Lecture_name:<select name="lessons">
      <%for (Lessons lesson : lessons){%>
        <option value="<%= lesson.getId()%>"><%=lesson.getLecturerName()%></option>
      <%}%>
    </select>
    <br>
    <input type="submit" value="add" id="add_2">
  </form>
</div>
</body>
</html>
