package es.fp.dwes.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginPost")
public class Ejercicio4Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LOGIN_JSP = "/login.jsp";
	private static final String LISTADO_JSP = "/listado.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private ServletConfig config;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		config.getServletContext().getRequestDispatcher(LOGIN_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nombre = request.getParameter("name");
		String password = request.getParameter("key");

		String pagina = "";

		if (nombre.equals("bob") || password.equals("1234")) {

			pagina = LISTADO_JSP;

		} else {
			pagina = ERROR_JSP;

		}
		config.getServletContext().getRequestDispatcher(pagina).forward(request, response);
	}

}
