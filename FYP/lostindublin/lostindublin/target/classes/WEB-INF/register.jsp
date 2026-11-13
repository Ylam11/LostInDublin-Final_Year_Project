<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>

    <h2>User Registration</h2>

    <form action="http://localhost:8080/FYP/restful-services/UserService/register" method="POST">

        Email:
        <input type="text" name="userEmail">
        <br><br>

        Password:
        <input type="text" name="userPassword">
        <br><br>

        Phone:
        <input type="text" name="userPhoneNumber">
        <br><br>

        <input type="submit" value="Register User">

    </form>
    <br>

    <form action="login.jsp" method="GET">
        <input type="submit" value="Go to Log in">
    </form>
    <br>

    <form action="index.jsp" method="GET">
        <input type="submit" value="Home">
    </form>

</body>
</html>
