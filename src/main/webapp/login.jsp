<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>login</title>
	</head>
<body>
	<h1>Formulario</h1>
	<form action="loginPost" method="post">
	<fieldset>
		<legend>Registro cliente</legend>
		<p>
			<label for="nombre">Nombre</label> 
			<input type="text" name="name"	id="name" required>
		</p>
		<p>
			<label for="password">Contraseña</label>
			 <input type="password"	name="key" id="key" required>
		</p>
		<input type=Submit value="Enviar">
	</fieldset>
	</form>
</body>
</html>

