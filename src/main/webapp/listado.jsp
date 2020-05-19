<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.fp.dwes.domains.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listado</title>
<style type="text/css">
.error {
	color: red;
}
</style>
</head>
<body>
   <jsp:useBean id="beanUsuario" class="es.fp.dwes.domains.User" scope="session"/>
	<h1>Hola ${beanSesionUsuario.name}</h1>
	
	<!-- Esto saca el parametro pasado por el usuario y recogido dentro del objeto request -->
	<c:if test="${empty param.user}">
		<p class="error">Si ves el nombre de usuario pero no la password
			es porque el nombre esta almacenado en session, mientras que la
			password en request.</p>
	</c:if>
	
	<h2>tu usuario es: ${param.user}</h2>
	<!-- Esto saca el parametro pasado por el usuario y recogido dentro del objeto request -->	
	<c:if test="${empty beanSesionUsuario.password}">
		<p class="error">Si ves el nombre de usuario pero no la password
			es porque el nombre esta almacenado en session, mientras que la
			password en request.</p>
	</c:if>
	
	<c:if test="${empty users }">
		<!-- usuarios es un atributo metido en el request por eso no es necesario ponerle el prefijo param -->
		<p class="error">Si no ves usuarios es porque has accedido
			directamente a la pagina y por tanto no has pasado por el servlet
			controlador y no hay datos en el objeto request.</p>
	</c:if>
	<c:if test="${!empty users }">
		<h3>Aqui tienes el listado de usuarios</h3>
		<table border="1">
		<tr>
			<th>Nombre</th>
			<th>Apellidos</th>
		</tr>
		<c:forEach items="${users}" var="usuario">
			<!-- recorremos todos los objetos de la coleccion usuarios y cada objeto devuelto lo asignamos a la variable usuario -->
			<tr>
				<td>${usuario.name }</td>
				<!-- Usuario es un POJO por lo que podemos acceder a sus propiedades sin necesidad de get/set -->
				<td>${usuario.lastName }</td>
			</tr>

		</c:forEach>
	</table>
		
	</c:if>
	
	<c:if test="${!empty beanSesionUsuario.user }">
	
	<p>
		<a href="login?cerrarsesion=true">Cerrar sesión</a>
	</p>
	
	</c:if>
	
</body>
</html>

