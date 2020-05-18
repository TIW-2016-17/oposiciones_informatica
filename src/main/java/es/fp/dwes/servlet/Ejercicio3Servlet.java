package es.fp.dwes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/parametros")
public class Ejercicio3Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("Petición recibida por GET");
		out.println("<h1>Hola " + nombre + "</h1>");
		out.close();
	}

}
