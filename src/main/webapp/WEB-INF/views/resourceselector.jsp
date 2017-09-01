
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Resource Selector</title>
</head>
<body onload="getLocation()">




    <form action="/filterResources" method="put">

        <div id="resourceSelector">
            <h1>What do you need today?</h1>
            <input type="checkbox" name="food" value="1"> Food <br>
        <input type="submit" value="Submit" >
            <p>Please allow SafeZone to access your device's location.
                This information will not be saved or used for anything other than providing you directions to your selected resource.
            </p>
        </div>
    </form>

</div>



<c:forEach var="item" items="${usList}">
<div class="selectedResource" onload="hideMe()">
    <!--Returns list of resources where category selected is "Food" and their respective latitude and longitude values !-->

    <h1 class="organization"> ${item.organization} </h1>
    <%--<p class="lat">Lat: ${item.latitude} </p>--%>
    <%--<p class="lon">Lon: ${item.longitude}</p>--%>

        <p ID="demo"></p>

        <form action="route" method="post">

            <input type="hidden" name="lat"> </input>
            <input type="hidden" name="lon"> </input>
            <input type="hidden" name="rLat" value="${item.latitude}"> </input>
            <input type="hidden" name="rLon" value="${item.longitude}"> </input>
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
        function hideMe() {
            var x = document.getElementById('resourceSelector');
            if (x.style.display === 'none') {
                x.style.display = 'block';
            } else {
                x.style.display = 'none';
            }
        }

        var x = document.getElementById("demo");

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
