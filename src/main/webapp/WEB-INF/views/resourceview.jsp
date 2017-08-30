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
<%--Here is the <strong>entire</strong> list of SafeZone's Resource List:--%>

<%--&lt;%&ndash;For each loop below (Java) is being used via the JSTL tag library at the top of page. JSTL allows us to use java functions&ndash;%&gt;--%>
<%--&lt;%&ndash;such as loops in our jsp page and also to access java items such as an Arraylist like rList and usList.&ndash;%&gt;--%>
<%--&lt;%&ndash;"items" allows us to refer to the arraylist in our for each loop and "var=item" allows us to refer to each element and its value &ndash;%&gt;--%>
<%--&lt;%&ndash;in the arraylist&ndash;%&gt;--%>

<%--<c:forEach var="item"  items="${rList}">--%>
<%--<div class="resource">--%>
    <%--<!--Returns entire list of resources in our db and certain info. for each resource!-->--%>
    <%--&lt;%&ndash;${item.ID}&ndash;%&gt;--%>
  <%--<h1> ${item.organization} </h1>--%>
        <%--<p> ${item.description} </p>--%>
        <%--<span>Homepage: </span><a href="http://${item.website}"> ${item.website} </a>--%>
    <%--${item.phone}--%>
   <%--<p> ${item.address} ,--%>
            <%--${item.zip}--%>
   <%--</p>--%>

<%--</c:forEach>--%>

<%--</div>--%>
<h1> Your SafeZone resources: </h1>
<%--"items" here allows us to refer to the arraylist (usList) in our for each loop and "var=item" allows us to refer to
each element and its value --%>
<%--in the arraylist--%>

<c:forEach var="item" items="${usList}">
    <div class="selectedResource">
        <!--Returns list of resources where category selected is "Food" and their respective latitude and longitude values !-->
   <h1 class="organization"> ${item.organization} </h1>
    <p class="lat">Lat: ${item.latitude} </p>
    <p class="lon">Lon: ${item.longitude}</p>

</c:forEach>
</div>
<%-- ${resource} --%>
</body>
</html>
