<%--
  Created by IntelliJ IDEA.
  User: Jared
  Date: 8/30/17
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resource Selector</title>
</head>
<body>
<h1>What do you need today?</h1>

    <form action="/resourcelist" method="put">
        <input type="checkbox" name="food" value="1"> Food <br>
        <input type="submit" value="Submit">
    </form>


</body>
</html>
