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
        <input type="checkbox" name="shelter" value="1"> Shelter <br>
        <input type="checkbox" name="clothing" value="1"> Clothing <br>
        <input type="checkbox" name="counseling" value="1"> Counseling <br>
    `   <input type="checkbox" name="healthcare" value="1"> Healthcare <br>
        <input type="checkbox" name="education" value="1"> Education <br>
        <input type="checkbox" name="job" value="1"> Job <br>
        <input type="checkbox" name="female" value="1"> Female <br>
<input type="checkbox" name="male" value="1"> Male <br>

        <input type="submit" value="Submit">
    </form>


</body>
</html>
