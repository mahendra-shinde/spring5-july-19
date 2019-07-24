<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>
</head>
<body>
	<h1>Welcome to Home page!</h1>
	${msg }

	<a href="add-product">Add Product</a>

	<form action="search" method="get">
		Search product : <input name="q" type="text" /> <input type="submit"
			value="search" />
	</form>
	
	<form action=calc-interest method=post>
		Principal amount : <input type="text" name="p"/><br/>
		Rate of Interest : <input type="text" name="r"/><br/>
		Duration (Months): <input type="text" name="d"/><br/>
		<input type="submit" value="Calculate"/>
	</form>
		
</body>
</html>