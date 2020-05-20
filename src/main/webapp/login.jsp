<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<h1>Formulario</h1>
	<c:if test="${!empty errores }">
		<c:forEach items="${errores }" var="error">			
				<p class="error">${error.value}</p>	
		</c:forEach>
	</c:if>

	<form action="login" method="post">
		<fieldset>
			<legend>Registro cliente</legend>
			<p>
				<label for="user">Usuario</label> <input type="text" name="user"
					id="user" required>
			</p>
			<p>
				<label for="password">Contraseña</label> <input type="password"
					name="key" id="key" required>
			</p>
			
		</fieldset>
		<input type=Submit value="Enviar">
	</form>
</body>
</html>



