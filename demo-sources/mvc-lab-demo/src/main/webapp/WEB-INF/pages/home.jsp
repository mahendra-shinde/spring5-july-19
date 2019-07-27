<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home page</title>
</head>
<body>
	<h2>Book library!</h2>
	
	<h3>${msg }</h3>
	<table>
		<tr>
			<td>Operations</td>
		</tr>
		<tr>
			<td><a href="books/create">Add a book</a></td>
		</tr>
		<tr>
			<td><a href="books/delete">Delete a book</a></td>
		</tr>
		<tr>
			<td><a href="books/edit"> Modify a book </a></td>
		</tr>
		<tr>
			<td><a href="books/view"> Find a book</a></td>
		</tr>
		<tr>
			<td><a href="books/view-all">View all books</a></td>
		</tr>
	</table>
</body>
</html>