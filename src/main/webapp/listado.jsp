<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="es.fp.dwes.domains.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
   <jsp:useBean id="beanUsuario" class="es.fp.dwes.domains.User"/>
   <jsp:useBean id="beanSesionUsuario" class="es.fp.dwes.domains.User" scope="session"/>
   <jsp:setProperty name="beanUsuario" property="user" param="user"/>
   <jsp:setProperty name="beanUsuario" property="password" param="key"/>
	<h1>Hola</h1>
	<h2>Tu nombre es:<jsp:getProperty property="name" name="beanSesionUsuario"/></h2>
	<% 
	if(beanSesionUsuario.getUser()==null){
		out.println("<p class=\"error\">Si no ves el usuario es porque has accedido "+
				"directamente a la pagina sin loguearte</p>");
	}
	%>
	<h2>tu clave es: <jsp:getProperty property="password" name="beanUsuario"/></h2>
	<!-- Esto saca el parametro pasado por el usuario y recogido dentro del objeto request -->
	<% 
	if(request.getParameter("key")==null){
		out.println("<p class=\"error\">Si ves el nombre de usuario pero no la password "+
					"es porque el nombre esta almacenado en session, mientras que la " +
					"password en request.</p>");
	}
	%>
	<h3>Aqui tienes el listado de usuarios</h3>
	<% 
	List<?> usuarios = (ArrayList<?>) request.getAttribute("users");
	if(usuarios==null){
		out.println("<p class=\"error\">Si no ves usuarios es porque has accedido "+
					"directamente a la pagina y por tanto no has pasado por el servlet " +
					"controlador y no hay datos en el objeto request</p>");
	}
	else {
		%>
	<table border="1">
		<tr>
			<th>Nombre</th>
			<th>Apellido</th>
		</tr>
		<% 
		for(Object u: usuarios){
			out.println("<tr>");
			out.println("<td>"+((User) u).getName()+"</td>");
			out.println("<td>"+((User) u).getLastName()+"</td>");
			out.println("<tr>");
		}
	
		%>
		
	</table>
	<% }
	if(beanSesionUsuario.getUser()!=null){
		out.println("<p>");
		out.println("<a href=\"login?cerrarsesion=true\">Cerrar sesión</a>");	
		out.println("</p>");
	}
	%>
	
</body>
</html>

