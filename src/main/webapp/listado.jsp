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
.mensaje {
	color: blue;
}
</style>
</head>
<body>
   <jsp:useBean id="beanUsuario" class="es.fp.dwes.domains.User" scope="session"/>
	<h1>Hola ${beanSesionUsuario.name}</h1>
	
	<c:if test="${!empty mensajes }">
		<c:forEach items="${mensajes }" var="mensaje">			
				<p class="mensaje">${mensaje.value}</p>	
		</c:forEach>
	</c:if>
	
	<c:if test="${!empty errores }">
		<c:forEach items="${errores }" var="error">			
				<p class="error">${error.value}</p>	
		</c:forEach>
	</c:if>
	
	<c:if test="${empty users }">
		<!-- usuarios es un atributo metido en el request por eso no es necesario ponerle el prefijo param -->
		<p class="error">Si no ves usuarios es porque has accedido
			directamente a la pagina y por tanto no has pasado por el servlet
			controlador y no hay datos en el objeto request.</p>
	</c:if>
	<c:if test="${!empty users }">
		
		<table border="1">
		<tr>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>editar</th>
			<th>eliminar</th>
		</tr>
		<c:forEach items="${users}" var="usuario">
			<!-- recorremos todos los objetos de la coleccion usuarios y cada objeto devuelto lo asignamos a la variable usuario -->
			<tr>
				<td>${usuario.name }</td>
				<!-- Usuario es un POJO por lo que podemos acceder a sus propiedades sin necesidad de get/set -->
				<td>${usuario.lastName}</td>
				<td><a href="user?accion=EDIT&id=${usuario.id}">Editar</a>	</td>
				<td><a href="user?accion=DELETE&id=${usuario.id}">Eliminar</a>	</td>
				
			</tr>

		</c:forEach>
	</table>
		
		
	</c:if>
	
	<p>
		<a href="user?accion=ADD">Añadir usuario</a>	
	</p>
	
	<c:if test="${!empty beanSesionUsuario.user }">
	
	<p>
		<a href="login?cerrarsesion=true">Cerrar sesión</a>
	</p>
	
	</c:if>
	
</body>
</html>

