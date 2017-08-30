<%--
  Created by IntelliJ IDEA.
  User: Jared
  Date: 8/24/17
  Time: 4:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Route Outline</title>
</head>
<body>
<h1>Status: ${status}</h1>

<%--Below, we have a for each loop that references our model called 'insturctions' which contains an ArrayList of Strings --%>
<%--that are step-by-step instructions for the user to get from their location to their destination. We use the "var=item" to --%>
<%--access the elements in that arraylist--%>

<c:forEach var="item" items="${instructions}">
    <p>${item}</p>

</c:forEach>

</body>
</html>