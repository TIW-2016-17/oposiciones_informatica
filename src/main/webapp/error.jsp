<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; UTF-8">
		<title>Error login</title>
	</head>
<body>
	<c:if test="${!empty errores }">
		<c:forEach items="${errores }" var="error">			
				<p style="color:red">${error.value}</p>	
		</c:forEach>
	</c:if>
	<p> <a href='login'>Volver</a></p>
</body>
</html>
