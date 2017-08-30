<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Welcome to Safe Zone!</title>
</head>
<body>

<%--The models ${title} and ${message} are used to access the text in our Home Controller where our
RequestMapping is ("/")/--%>

<h1>${title}</h1>
${message}<br />

<%--Hyperlink that takes user to register form, which is in the register/jsp--%>

<a href="register">Register for services</a>

</body>
</html>