<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add product page</title>
</head>
<body>
<!-- modelAttribute MUST MATCH with attribute defined in Controller's GET method! -->
<s:form method="post" modelAttribute="product" >
	Product ID : <input type="text" name="productId"/><br/>
	Name : <input type="text" name="name"/><br/>
	Description: <input type="text" name="description"/><br/>
	Price: <input type="text" name="price"/><br/>
	<input type="submit" value="Save"/>
</s:form>
</body>
</html>