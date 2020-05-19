<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Usuario</title>
</head>
<body>
 	<jsp:useBean id="beanUsuario" class="es.fp.dwes.domains.User"/>
 	<jsp:setProperty name="beanUsuario" property="*" />
 	
	<c:if test="${empty param.name }">
		<p>Ningún usuario registrado</p>
	</c:if>
	<c:if test="${!empty param.name }">	
		<p>Acabas de registrar al siguiente usuario</p>
		
		<ul>
		<li>Nombre: ${beanUsuario.name} </li>
		<li>Apellido: ${beanUsuario.lastName}</li>
		<li>Usuario  ${beanUsuario.name}</li>
	 	<li>Contraseña ${beanUsuario.name}</li> 	
	 	</ul>
 	</c:if>
</body>
</html>

