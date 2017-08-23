
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Forecast</title>
</head>
<body>
Response status: ${status} <br />
Production center: ${prodCenter} <br />
<h2>Weather</h2>
<table>

     <td>
        ${day1}:<br> ${temp1} <br>
     </td>
    <td>${day2}:<br> ${temp2} <br> </td>
    <td>${day3}:<br> ${temp3} <br> </td>
    <td>${day4}:<br> ${temp4}<br> </td>
    <td>${day5}:<br> ${temp5}<br> </td>
</table>
</body>
</html>
