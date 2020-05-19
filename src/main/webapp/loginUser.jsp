<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Usuario</title>
</head>
<body>
 	<jsp:useBean id="beanUsuario" class="es.fp.dwes.domains.User"/>
 	<jsp:setProperty name="beanUsuario" property="*" />
	<p>Acabas de registrar al siguiente usuario</p>
	<ul>
	<li>Nombre: <jsp:getProperty property="name" name="beanUsuario"/></li>
	<li>Apellido: <jsp:getProperty property="lastName" name="beanUsuario"/></li>
	<li>Usuario<jsp:getProperty property="user" name="beanUsuario" /></li>
 	<li>Contraseña<jsp:getProperty property="password" name="beanUsuario" /></li> 	
 	</ul>
</body>
</html>

