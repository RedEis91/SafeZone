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
Here is the <strong>entire</strong> list of SafeZone's Resource List:

<ul>
<c:forEach var="item"  items="${rList}">

    <li>${item.ID}</li>
    <li>${item.organization}</li>
    <li>${item.zip}</li>
    <li>${item.website}</li>
    <li>${item.phone}</li>
    <li>${item.address}</li>
    <li>${item.description}</li>

</c:forEach>
</ul>

Here is a list of SafeZone's Resources whose <strong> category is "Food" </strong>:
<ul>
<c:forEach var="item" items="${usList}">

    <li>${item.organization}</li>
    <li>${item.latitude}</li>
    <li>${item.longitude}</li>

</c:forEach>
</ul>
<%-- ${resource} --%>
</body>
</html>
