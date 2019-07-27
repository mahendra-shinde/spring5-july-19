<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View All Books</title>
</head>
<body>
<h2>View All Books</h2>
${msg }
<table border=1 cellpadding="3" cellspacing="0">
<tr>
<td>Book Id</td>
<td>Title</td>
<td>Author</td>
</tr>
<c:forEach items="${books}" var="b">
	<tr>
	<td>${b.bookId }</td>
	<td>${b.title }</td>
	<td>${b.author }</td>
	</tr>
</c:forEach>
</table>
</body>
</html>