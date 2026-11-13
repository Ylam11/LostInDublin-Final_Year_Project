<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>Log in</title>
</head>
<body>
    <h2>User Login</h2>

    <form action="http://localhost:8080/UserService/login" method="POST">
        Email: <input type="text" name="userEmail" required> <br><br>
        Password: <input type="text" name="userPassword" required> <br><br>
        <input type="submit" value="Login">
    </form>

    <br>
    <form action="index.jsp" method="GET">
        <input type="submit" value="Home">
    </form>
</body>
</html>
