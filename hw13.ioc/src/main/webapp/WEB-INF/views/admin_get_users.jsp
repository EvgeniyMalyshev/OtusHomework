<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Admin</title>
</head>
<body>
<table>
    <h3>List of the users</h3>
    <c:forEach var="User" items="${list}">
        <li>${User.name}</li>
    </c:forEach>
</table>
</body>
</html>
