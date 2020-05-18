package es.fp.dwes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginPost")
public class Ejercicio4Servlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Lee los parametrod de la petición (request)
		String nombre = (String) req.getParameter("name");
		String password = (String) req.getParameter("key");
		res.setContentType("text/html"); // tipo de conntenido
		PrintWriter out = res.getWriter(); // obtener el writer
		// escribiendo en la salida
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registro</title>");
		out.println("</head>");
		out.println("<body>");
		if (nombre.equals("bob") && password.equals("1234")) {
			out.println("<p>Información del cliente</p> ");
			out.println("<p><strong>Nombre: " + nombre + "</strong></p>");
			out.println("<p><strong>Email: " + password + "</strong></p>");
		}
		else {
			out.println("<p style=\"color:red\"> Usuario incorrecto</p>");
			out.println("<p> <a href='registroServlet.html'>Volver</a></p>");
		}
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close(); // Informa al servidor que ha terminado
		// enviando información

	}

}
