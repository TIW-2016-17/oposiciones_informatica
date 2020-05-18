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

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext context;

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
