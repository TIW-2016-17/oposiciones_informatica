<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Añadir Usuario</title>
</head>
<body>
	<form action="user" method="post">
		<fieldset>
			<legend>Añadir Usuario</legend>

			<label for="name">Nombre:</label> 
			<input type="text" name="name" id="name" required><br> 
            <label for="lastname">Apellido:</label> 
			<input type="text" name="lastname" id="lastname"><br>
            <label for="user">Usuario:</label> 
			<input type="text" name="user" id="user"><br>
			<label for="password">Contraseña:</label> 
			<input type="password" name="password" id="password">
			<input type="hidden" value="ADD" name="accion">
		</fieldset>
		<input type="submit" value="Send">

	</form>
</body>
</html>

