<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8"/>
    <title>Admin</title>
</head>
<body>
<h1>Create user</h1>
<div class="create_user">
    <form action="create_user" method="POST">
        username: <label>
        <input type="text" name="user"/>
    </label>
        <input type="submit" value="createUser">
    </form>

    <form action="admin_get_users" method="GET">
        <input type="submit" name="getUsers" value="getUsers">
    </form>
</div>
</body>
</html>
