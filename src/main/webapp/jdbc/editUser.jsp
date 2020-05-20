<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar usuarios</title>
</head>
<body>
	<form action="user" method="post">
		<fieldset>
			<legend>Formulario para editar usuarios</legend>
			<label for="id">Id:</label> 
			<input type="text" name="id" id="id" value="${user.id }" size="3" readonly  ><br>
			<label for="name">Nombre:</label> <input type="text" name="name"
				id="name" value="${user.name }"><br> 
			<label for="lastname">Apellido:</label> <input type="text" name="lastname"
				id="lastname" value="${user.lastName }"  ><br>
            <label for="user">Usuario:</label> <input type="text" name="user"
				id="user" value="${user.user }"  ><br>
			<label for="password">Contraseña:</label> <input
				type="password" name="password" id="password" value="${user.password }">
			<input type="hidden" value="EDIT" name="accion">
		</fieldset>
		<input type="submit" value="Send">

	</form>

</body>
</html>


