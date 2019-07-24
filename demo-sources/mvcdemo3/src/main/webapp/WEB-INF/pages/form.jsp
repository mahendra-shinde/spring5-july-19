<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add product page</title>
</head>
<body>
	<!-- modelAttribute MUST MATCH with attribute defined in Controller's GET method! -->
	<s:form method="post" modelAttribute="product">
	Product ID : <s:input path="productId" />
		<s:errors path="productId" />
		<br />
	Name : <s:input path="name" />
		<s:errors path="name" />
		<br />
	Description: <s:input path="description" />
		<s:errors path="description" />
		<br />
	Price: <s:input path="price" />
		<s:errors path="price" />
		<br />
		<input type="submit" value="Save" />
	</s:form>
</body>
</html>