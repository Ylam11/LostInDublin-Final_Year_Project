<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta charset="UTF-8">
<title>Items</title>
</head>
<body>

    <h1>Item Services</h1>

  

   
   
	
	<h2>View User Items</h2>

		<form action="http://localhost:8080/ItemService/ownerItems" method="GET">


		User ID:
		<input type="text" name="userId">
		<br><br>

		<input type="submit" value="view Users Items">

	</form>
	
	
	
	
		<h2>View Item QR Code</h2>
		<form action="/ItemService/qrcode/image" method="GET">
		
		Item ID:
		<input type="text" name="itemId" required>
		<br><br>
		
		<input type="submit" value="View QR Code">
	</form>


    <h2>Register Item</h2>

    <form action="http://localhost:8080/ItemService/create" method="POST">

        Owner ID:
        <input type="text" name="ownerId">
        <br><br>

        Item Name:
        <input type="text" name="itemName">
        <br><br>

        Description:
        <input type="text" name="itemDescription">
        <br><br>

       

		
		Item Status:
        <select name="itemStatus">
			<option value="LOST">LOST</option>
			<option value="FOUND">FOUND</option>
			<option value="RETURNED">RETURNED</option>
			<option value="OWNERSHIP">OWNERSHIP</option>
		</select>
        <br><br>

        Registered At:
        <input type="text" name="itemRegisteredAt">
        <br><br>

        <input type="submit" value="Register Item">

    </form>
    <br>

    <form action="reports.jsp" method="GET">
        <input type="submit" value="Report A Lost Item">
    </form>
    <br>

    <form action="index.jsp" method="GET">
        <input type="submit" value="Home">
    </form>

</body>
</html>
