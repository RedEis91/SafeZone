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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <link href="resources/css/style.css" rel="stylesheet"/>
</head>
<body>
You are not alone ${firstname}<br>
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
