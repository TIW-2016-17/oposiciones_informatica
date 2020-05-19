<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>listado</title>
	</head>
<body>
	<p>
		<strong>Hola <%=request.getParameter("name")%></strong>
	</p>	
	<p>
		<strong>Tu clave es: <%=request.getParameter("key")%></strong>
	</p>


</body>
</html>

