
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
<body>

<nav class="navbar navbar-toggleable-md navbar-light bg-faded">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#"><h1>${title}</h1></a>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <%--Hyperlink that takes user to register form, which is in the register/jsp--%>
                <a class="nav-link" href="/">Homepage <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/foodResources" method="put">
                    <input type="submit" value="Food">
                </form></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/shelterResources" method="put">
                    <input type="submit" value="Shelter">
                </form></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/clothingResources" method="put">
                    <input type="submit" value="Clothing">
                </form></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/counselingResources" method="put">
                    <input type="submit" value="Counseling">
                </form></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/healthcareResources" method="put">
                    <input type="submit" value="Healthcare">
                </form></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/educationResources" method="put">
                    <input type="submit" value="Education">
                </form></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><form action="/jobResources" method="put">
                    <input type="submit" value="Jobs">
                </form></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="resourceview">All Resources</a>
            </li>
        </ul>
        <p class="navbar-text">
            ${message}
        </p>
    </div>
</nav>

<img src="resources/imgs/logo.png" alt="Hands Logo Vector Copyright: freevector" class="logo">



</body>
</html>
