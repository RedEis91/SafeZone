
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Resource Selector</title>
</head>
<body onload="getLocation()">
<h1>What do you need today?</h1>

    <form action="/filterResources" method="put">
        <input type="checkbox" name="food" value="1"> Food <br>
        <input type="submit" value="Submit">
    </form>

<c:forEach var="item" items="${usList}">
<div class="selectedResource">
    <!--Returns list of resources where category selected is "Food" and their respective latitude and longitude values !-->

    <h1 class="organization"> ${item.organization} </h1>
    <p class="lat">Lat: ${item.latitude} </p>
    <p class="lon">Lon: ${item.longitude}</p>

    </c:forEach>
</div>

<div class="getDirections">

    <p>Please allow this page to access your location. We will give you directions to your destination.</p>


    <p ID="demo"></p>

    <%--this form grabs the user's current location through their device's GPS and it will alos get the latitude and longitude --%>
    <%--of their destination
    When the submit button is pressed, the info. will be sent to the route method that is in our Home Controller --%>
    <form action="route" method="post">

        <input type="text" id="lat" name="lat"> </input>
        <input type="text" id="lon" name="lon"> </input>
        <input type="text" name="rLat"> </input>
        <input type="text" name="rLon"> </input>

        <input  type="submit" name="submit" value="Submit coordinates" >
        <!-- onsubmit="locateDestination()" -->

    </form>

    <%--//this JavaScript script runs a function that is called within our opening body tag (at the top) which gets the user's --%>
    <%--current location by latitude and longitude
    Then we set the user's latitude and longitude values equal to their corresponding id (lat & lon) in the above form to be passed
    to the route method in our Home Controller--%>
</div>
<div class="stepByStepDirections">
    <c:forEach var="item" items="${instructions}">
    <p>${item}</p>
</c:forEach>
</div>

    <script >
        var x = document.getElementById("demo");
        var pos;
        function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition);
            } else {
                x.innerHTML = "Geolocation is not supported by this browser.";
            }
        }

        function showPosition(position) {
            var lat = position.coords.latitude;
            var lon = position.coords.longitude;
            pos = [lat, lon];

            document.getElementById("lon").value = lon;
            document.getElementById("lat").value = lat;

        }

    </script>

</body>
</html>
