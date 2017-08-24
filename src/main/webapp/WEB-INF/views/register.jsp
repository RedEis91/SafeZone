
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
</head>
<body>
<h1>${inst}</h1>
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
</body>
</html>
