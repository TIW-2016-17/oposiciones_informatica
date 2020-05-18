<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Ejemplo expresiones</title>
</head>
<body>
	<h2>Ejemplos de expresiones JSP</h2>
		<ul>
			<li>Fecha actual del servidor <%= java.time.LocalDateTime.now()  %></li>
			<li>Valor de e = <%= Math.PI	%></li>
			<li>Valor del parámetro prueba: <%= request.getParameter("prueba") %></li>
		</ul>

</body>
</html>
