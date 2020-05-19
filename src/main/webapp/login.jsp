<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<%
		Map<?, ?> errores = (HashMap<?, ?>) request.getAttribute("errores");
		if (errores != null && !errores.isEmpty()) {
			for (Map.Entry<?, ?> error : errores.entrySet()) {
				out.println("<p style=\"color:red\">" + error.getValue() + "</p>");
			}

		}
	%>

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



