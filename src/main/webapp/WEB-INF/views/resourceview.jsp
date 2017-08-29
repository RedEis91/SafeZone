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


<c:forEach var="item"  items="${rList}">
<div class="resource">

    <%--${item.ID}--%>
  <h1> ${item.organization} </h1>
        <p> ${item.description} </p>
        <span>Homepage: </span><a href="http://${item.website}"> ${item.website} </a>
    ${item.phone}
   <p> ${item.address} ,
            ${item.zip}
   </p>

</c:forEach>

</div>
Here is a list of SafeZone's Resources whose <strong> category is "Food" </strong>:

<div class="selectedResource">
    <!--Returns list of resources where category selected is "Food" !-->
<c:forEach var="item" items="${usList}">

   <h1 class="organization"> ${item.organization} </h1>
    <p class="lat">Lat: ${item.latitude} </p>
    <p class="lon">Lon: ${item.longitude}</p>

</c:forEach>
</div>
<%-- ${resource} --%>
</body>
</html>
