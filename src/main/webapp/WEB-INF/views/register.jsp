
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body onload="getLocation()" >
<h1>${inst}</h1>

<%--form below allows the user to input their info and when they press submit, all the info colllected will be passed through --%>
<%--the formhandler method in our Home Controller --%>

<form action="formhandler" method="post">
    <fieldset>
        <legend>User Registration</legend>
    First Name: <input type="text" name="firstname"><br>
    Last Name: <input type="text" name="lastname"><br>
    Phone Number: <input type="tel" name="phonenum"><br>
    Gender: Please enter 'Male','Female' or 'Other' <br>
        <input type="text" name="gender"> <br>
    Date of Birth: <input type="date" name="birthday"><br>
    Email: <input type="email" name="email"><br>
    </fieldset>
    <input type="submit" name="submit" value="Register">
</form>

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
