package es.fp.dwes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/listServlet")
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();

		String name = "";
		String password = "";
		boolean autenticado;
		ArrayList<?> listaUsuarios = new ArrayList<String>();
		PrintWriter out = null;

		response.setContentType("text/html;charset=UTF-8"); // tipo de
															// conntenido
		out = response.getWriter(); // obtener el writer

		autenticado = (sesion != null && sesion.getAttribute("autenticado") != null
				&& (Boolean) sesion.getAttribute("autenticado"));

		// escribiendo en la salida
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registro eje 6</title>");
		out.println("</head>");
		out.println("<body>");

		if (autenticado) {
			name = (String) sesion.getAttribute("name");
			password = (String) sesion.getAttribute("key");
			listaUsuarios = (ArrayList<?>) sesion.getAttribute("users");
		} else if (request.getAttribute("users") != null) {

			name = (String) request.getParameter("name");
			password = (String) request.getParameter("key");
			listaUsuarios = (ArrayList<?>) request.getAttribute("users");
		}

		out.println("<h1>Información del usuario </h1>");
		out.println("Hola " + name + "</br>");
		out.println("tu clave es " + password + "</br>");

		out.println("<h2>Lista de usuarios</h2>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Nombre</th>");
		out.println("</tr>");

		for (Object nombre : listaUsuarios) {
			out.println("<tr>");
			out.println("<td>" + nombre.toString() + "</td>");
			out.println("</tr>");
		}
		out.println("</table>");

		if (autenticado)
			out.println("<a href=\"loginServlet?cerrarsesion=true\">Cerrar sesión</a>");

		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
