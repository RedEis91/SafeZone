<%--
  Created by IntelliJ IDEA.
  User: Jared
  Date: 8/11/17
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Summary Page</title>
</head>
<body>
You are not alone, ${firstname}<br>
Here are some resources in your area:

<ul>
<c:forEach var="item"  items="${rList}">

    <li>${item}</li>

</c:forEach>
</ul>

<%-- ${resource} --%>
</body>
</html>
