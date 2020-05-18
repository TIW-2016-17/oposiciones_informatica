package es.fp.dwes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext context;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession mySession = request.getSession(true);

		int contador = 1;
		Integer contadorCliente = 0;
		
		response.setContentType("text/html;charset=UTF-8"); // Obtenemos el writer PrintWriter
		out = response.getWriter();

		// contador global
		Object objectCounter = context.getAttribute("contador");

		if (objectCounter != null) {
			contador = Integer.parseInt((String) objectCounter);
			contador++;
		}
		context.setAttribute("contador", String.valueOf(contador));

		// contador cliente		
		
		contadorCliente = (Integer)mySession.getAttribute("contadorCliente");
        if( contadorCliente == null || contadorCliente == 0 ) {
           /* Primera visita */
           out.println("¡Bienvenido al sitio web!");
           contadorCliente = 1;
        } else {
           /* visita de nuevo */
           out.println("¡Bienvenido de vuelta a mi sitio web!");
           contadorCliente += 1;
        }
        mySession.setAttribute("contadorCliente", contadorCliente);

		out.println("<p>Número de veces que has visitado la página: " + contadorCliente + "</p>");

		out.println("<p>Vistas de la página: " + contador + "</p>");

		out.flush();
		out.close();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.context = config.getServletContext();// ServletCon-text

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// leemos los parámetros de la petición

		String nombre = (String) request.getParameter("name");
		String correo = (String) request.getParameter("email");
		String vForward = (String) request.getParameter("forward");

		if (vForward == null) {
			vForward = "n";
		}

		if (vForward.compareTo("y") == 0) {
			// response.sendRedirect("http://www.google.com");

			RequestDispatcher dispat = context.getRequestDispatcher("/index.html");
			dispat.forward(request, response);

		} else {

			// fijar el tipo de contenido
			response.setContentType("text/html;charset=UTF-8");

			// Obtener el writer
			PrintWriter out = response.getWriter();

			// respuesta del Write
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Form fields</title>");
			out.println("</head>");
			out.println("<body>");

			out.println("Client information ");
			out.println("<h2>Name: " + nombre + "</h2>");
			out.println("<h2>Email: " + correo + "</h2>");
			out.println("</body>");
			out.println("</html>");

			out.flush();
			out.close();
		}
	}

}
