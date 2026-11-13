<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
    <h2>Register</h2>

    <form action="http://localhost:8080/UserService/register" method="POST">
        Email: <input type="text" name="userEmail" required>  <br><br>
        Password: <input type="text" name="userPassword" required> <br><br>
        Phone: <input type="text" name="userPhoneNumber" required> <br><br>
        <input type="submit" value="Register">
		
    </form>
</body>
</html>
