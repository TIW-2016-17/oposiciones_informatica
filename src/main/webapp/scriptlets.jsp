<%@ page import = "java.io.*,java.util.*" %>
<!DOCTYPE html">
<html>
   <head>
      <title>Ejemplo Scriptlets</title>
   </head>
   
   <body style="text-align: center">
      <%
         /* contador  */
     	 Integer contador = (Integer) application.getAttribute("contador");
         /* tambien podiamos haberlo hecho con pageContext.getServletContext().getAttribute("contador");*/
         /* Primera visita */
         if( contador ==null || contador == 0 ) {    
          	out.println("¡Bienvenido a mi sitio web!");
         	contador = 1;
         	
         } 
         /* visita de nuevo */
		else {          
	         out.println("¡Bienvenido de vuelta a mi sitio web !");
        	 contador += 1;
         }  
         application.setAttribute("contador", contador); 	 
        
         String vez = contador == 1 ? " vez" : " veces";         
         %>
         <p>Has visitado la página <%=contador%> <%=vez%></p>    
   
   </body>
</html>
