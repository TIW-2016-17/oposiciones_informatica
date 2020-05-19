<%-- page directive --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"  import="es.fp.dwes.domains.BeanForm,java.util.List,java.util.ArrayList"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>

<body>
	<%-- declaration --%>
	<%!final static String USER = "pepe";%>
	<%-- scriptlet--%>
	<%
		String sLastName = (String) session.getAttribute("lastName");
		
		String sEmail = (String) request.getParameter("email");
		String sName = request.getParameter("name");
		
		
	%>
	<%-- jsp actions--%>
	<jsp:useBean id="formReceived" class="es.fp.dwes.domains.BeanForm"> 
	<jsp:setProperty name="formReceived" property="*" />
	</jsp:useBean>
	
	<%
	List<BeanForm> lUsers  = 	(ArrayList<BeanForm>) session.getAttribute("users");
	
	if (lUsers==null)
		lUsers = new ArrayList<BeanForm>();
	if(!lUsers.contains(formReceived)){
		lUsers.add(formReceived);
		}
	%>
	<!-- This is a comment that we can see -->
	<%-- This is secret comment --%>
	Client information:
		<h2>Name: <jsp:getProperty name="formReceived" property="name"/></h2>
		<h2>Email: <jsp:getProperty name="formReceived" property="email"/></h2>
		<h2>Forward: <jsp:getProperty name="formReceived" property="forwarding"/></h2>

	<%
		if (sName.equals(sLastName) || sName.equals(USER)) {
			%>
			<p>Hello <jsp:getProperty name="formReceived" property="name"/> </p>
			<% 
		} else {
			out.println("<p>I don't know you.</p>");
		}
		out.print("<ul>");
		for(Object user:lUsers){
			out.print("<li>"+user+"</li>");
		}
		out.print("</ul>");
		%>
	
	<%
		if (!sName.equals(""))
		session.setAttribute("lastName", sName);
		session.setAttribute("users", lUsers);
	%>
</body>
</html>

