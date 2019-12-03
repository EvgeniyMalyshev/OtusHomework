<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Admin</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>

    <h3>List of the users</h3>
    <tr>
        <th>Name</th>
    </tr>
    <c:forEach var="User" items="${list}">
        <tr>
            <td>${User.name}</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
