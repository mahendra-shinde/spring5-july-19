<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Book</title>
</head>
<body>
<h2>View Book</h2>
<h3>${msg }</h3>
<form method="post">
	Enter book id : <input type="text" value="0" name="id"/>
	<input type="submit" value="find"/>
</form>

<table border=1 cellpadding="3" cellspacing="0">
<tr>
<td>Book Id</td>
<td>Title</td>
<td>Author</td>
</tr>
	<c:if test="${book !=null}">
	<tr>
	<td>${book.bookId }</td>
	<td>${book.title }</td>
	<td>${book.author }</td>
	</tr>
	</c:if>
</table>
</body>
</html>