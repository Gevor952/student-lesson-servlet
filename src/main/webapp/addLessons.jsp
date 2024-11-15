<%--
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
  <link rel="stylesheet" href="style/style1.css">
</head>
<body>
<nav></nav>
<div class="main_div">
    <h1>add_lesson</h1>
    <div class="buts">
        <a href="index.jsp" class="a_but"><p>home</p></a>
        <a href="lessons" class="a_but"><p>lessons</p></a>
    </div>

    <form id="form_1" action="addLessons" method="post">
        <input type="text" placeholder="name" name="name">
        <input type="time" name="time">
        <input type="text" placeholder="price" name="price">
        <br>
        <input type="submit" value="add" id="add_1">
    </form>

</div>
</body>
</html>
