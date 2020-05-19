<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="es.fp.dwes.domains.BeanForm"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>

<body>

	Client information:
	<h2>Name: <%= request.getParameter("name") %></h2>
	<h2>Email: <%= request.getParameter("email") %></h2>


</body>
</html>

