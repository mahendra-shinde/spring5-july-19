<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Book</title>
</head>
<body>
<h3>${msg }</h3>
<form method="post">
	Enter book id : <input type="text" value="0" name="id"/>
	<input type="submit" value="find"/>
</form>

<c:if test="${book !=null}">
<table border=1 cellpadding="3" cellspacing="0">
<tr>
<td>Book Id</td>
<td>Title</td>
<td>Author</td>
</tr>
	
	<tr>
	<td>${book.bookId }</td>
	<td>${book.title }</td>
	<td>${book.author }</td>
	</tr>
	
</table>
<form action="delete-by-id" method="post" >
	<input type="hidden" value="${book.bookId }" name="id"/>
	<input type="submit" value="delete"/>
</form>
</c:if>
</body>
</html>