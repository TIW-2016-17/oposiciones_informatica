package es.fp.dwes.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Ejercicio5ListServlet
 */

@WebServlet("/listServlet")
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read the parameters from the request
		String name = (String) request.getParameter("name");
		String password = (String) request.getParameter("key");
		@SuppressWarnings("unchecked")
		List<String> listaUsuarios =  (List<String>) request.getAttribute("users");

		response.setContentType("text/html"); // tipo de conntenido
		PrintWriter out = response.getWriter(); // obtener el writer
		// escribiendo en la salida
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Registro</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Información del usuario </h1>");
		out.println("Hola " + name + "</br>");
		out.println("tu clave es "+ password + "</br>");
		
		out.println("<h2>Lista de usuarios</h2>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>Nombre</th>");
		out.println("</tr>");
		for(String nombre: listaUsuarios){
			out.println("<tr>");
			out.println("<td>"+nombre+"</td>");
			out.println("</tr>");
		}		
		out.println("</table>");



		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close(); // Informa al servidor que ha terminado
		// enviando información
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

