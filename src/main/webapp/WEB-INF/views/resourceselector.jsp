
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Resource Selector</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    <link href="resources/css/style.css" rel="stylesheet"/>
</head>
<body onload="getLocation()">
<h1>What do you need today?</h1>

    <form action="/filterResources" method="put">
        <input type="checkbox" name="food" value="1"> Food <br>
        <input type="submit" value="Submit">
    </form>
<p>Please allow SafeZone to access your device's location.
This information will not be saved or used for anything other than providing you directions to your selected resource.
</p>
<c:forEach var="item" items="${usList}">
<div class="selectedResource">
    <!--Returns list of resources where category selected is "Food" and their respective latitude and longitude values !-->

    <h3 class="organization"> ${item.organization} </h3>
    <p class="lat">Lat: ${item.latitude} </p>
    <p class="lon">Lon: ${item.longitude}</p>

        <p ID="demo"></p>

        <form action="route" method="post">

            <input type="text" name="lat"> </input>
            <input type="text" name="lon"> </input>
            <input type="text" name="rLat" value="${item.latitude}"> </input>
            <input type="text" name="rLon" value="${item.longitude}"> </input>
            <input  type="submit" name="submit" value="Get Directions to ${item.organization}" >

        </form>

    </c:forEach>
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
            <!-- Might be "NodeList" !-->
            var userLatitude = document.querySelectorAll("[name=lat]");
            var userLongitude = document.querySelectorAll("[name=lon]");

            <!-- Going into object, reassign value for key value pair (lat = "") !-->
            for (var i = 0; i < userLatitude.length; i++){
                userLatitude[i].value = lat;
            }

            for (var i = 0; i < userLongitude.length; i++) {
                userLongitude[i].value = lon;
            }


        }

    </script>

</body>
</html>
