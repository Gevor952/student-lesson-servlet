<%@ page import="am.itspace.studentlessonservlet1.model.Students" %>
<%@ page import="java.util.List" %>
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
    <link rel="stylesheet" href="style/style1.css">
</head>
<body>
<nav></nav>
<div class="main_div">
    <h1>students</h1>
    <div class="buts">
        <a href="index.jsp" class="a_but"><p>home</p></a>
        <a href="addStudents" class="a_but"><p>add students</p></a>
    </div>

    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>surname</th>
            <th>age</th>
            <th>lecture_name</th>
            <th>duration</th>
            <th>price</th>
            <th>action</th>
        </tr>

        <%List<Students> students = (List<Students>) request.getAttribute("students"); %>

        <%for (Students student : students) {%>
        <tr>
        <td><%= student.getId()%></td>
        <td><%= student.getName()%></td>
        <td> <%= student.getSurname()%></td>
        <td> <%= student.getAge()%></td>
        <td><%= student.getLesson().getLecturerName()%></td>
        <td><%= DateUtil.dateForTime(student.getLesson().getDuration())%></td>
        <td><%= student.getLesson().getPrice()%></td>
        <td><a href="deleteStudents?id=<%=student.getId()%>" class="a_but"><p>delete</p></a>  <a href="editeStudents?id=<%=student.getId()%>" class="a_but"><p>edit</p></a> </td>
        </tr>
        <%}%>

    </table>
</div>
</body>
</html>
