<%@ page import="am.itspace.studentlessonservlet1.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>1</title>


    <style>
      <%@include file="../style/style2.css"%>
    </style>

  </head>
  <body>
  <nav>
    <%if (session.getAttribute("user") == null){%>
    <a href="/login" class="a_but" id="singin"><p>singin</p></a>
    <%} else {%>
    <a href="/logout" class="a_but"><p>logout</p></a>
    <% }%>
  </nav>
  <div class="main_div" id="tlg">

    <h1>student-lesson-servlet</h1>
    <div class="buts">
      <%if (session.getAttribute("user") != null){%>
      <a href="/students" class="a_but"><p>students</p></a>
      <a href="/lessons" class="a_but"><p>lessons</p></a>
      <%}%>
    </div>
  </div>
  </body>
</html>
