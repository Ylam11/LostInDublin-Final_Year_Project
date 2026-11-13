<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reports</title>
</head>
<body>

    <h2>Report Services</h2>

    <form action="http://localhost:8080/ReportService/item/1" method="GET">
        <input type="submit" value="View Reports For Item 1">
    </form>


    <h2>Create Report</h2>

    <form action="http://localhost:8080/ReportService/createReport" method="POST">

        Item ID:
        <input type="text" name="itemId">
        <br><br>

        Report Type:
        <select name="reportType">
			<option value="LOST">LOST</option>
			<option value="FOUND">FOUND</option>
			<option value="RETURNED">RETURNED</option>
		</select>
        <br><br>

        Reported At:
        <input type="text" name="reportedAt">
        <br><br>

        Report Note:
        <input type="text" name="reportNote">
        <br><br>

        <input type="submit" value="Create Report">

    </form>
    <br>

   

    <form action="home.jsp" method="GET">
        <input type="submit" value="Home">
    </form>

</body>
</html>
