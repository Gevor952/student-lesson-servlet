<%@ page import="java.util.List" %>
<%@ page import="am.itspace.studentlessonservlet1.model.Lessons" %>
<%@ page import="am.itspace.studentlessonservlet1.util.DateUtil" %><%--
  Created by IntelliJ IDEA.
  User: NITRO
  Date: 15.11.2024
  Time: 19:42
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
<%List<Lessons> lessons = (List<Lessons>) request.getAttribute("lessons");%>
<nav></nav>
<div class="main_div">
    <h1>lessons</h1>
    <div class="buts">
        <a href="/" class="a_but"><p>home</p></a>
        <a href="/addLessons" class="a_but"><p>add lessons</p></a>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>duration</th>
            <th>lecture_name</th>
            <th>price</th>
            <th>user_id</th>
            <th>action</th>
        </tr>
        <%for(Lessons lesson : lessons){%>
            <tr>
                <td><%=lesson.getId()%></td>
                <td><%=DateUtil.dateForTime(lesson.getDuration())%></td>
                <td><%=lesson.getLecturerName()%></td>
                <td><%=lesson.getPrice()%></td>
                <td><%=lesson.getUser().getId()%></td>
                <td><a href="deleteLessons?id=<%=lesson.getId()%>" class="a_but"><p>delete</p></a>  <a href="editeLessons?id=<%=lesson.getId()%>" class="a_but"><p>edit</p></a> </td>

            </tr>
        <%}%>
    </table>

</div>

</body>
</html>
