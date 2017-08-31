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
    <title>Resource View</title>
</head>
<body>
You are not alone<br>
Here is the <strong>entire</strong> list of SafeZone's Resource List:

<%--For each loop below (Java) is being used via the JSTL tag library at the top of page. JSTL allows us to use java functions--%>
<%--such as loops in our jsp page and also to access java items such as an Arraylist like rList and usList.--%>
<%--"items" allows us to refer to the arraylist in our for each loop and "var=item" allows us to refer to each element and its value --%>
<%--in the arraylist--%>

<c:forEach var="item"  items="${rList}">
<div class="resource">
    <!--Returns entire list of resources in our db and certain info. for each resource!-->
    <%--${item.ID}--%>
  <h3> ${item.organization} </h3>
        <p> ${item.description} </p>
        <span>Homepage: </span><a href="http://${item.website}"> ${item.website} </a>
    ${item.phone}
   <p> ${item.address} ,
            ${item.zip}
   </p>

</c:forEach>

</div>

</body>
</html>
