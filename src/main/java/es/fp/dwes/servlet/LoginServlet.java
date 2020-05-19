package es.fp.dwes.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LOGIN_JSP = "/login.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private static final String LISTADO_JSP = "/listado.jsp";
	private ServletConfig config;
	private List<String> usuarios;

	@Override
	public void init(ServletConfig config) throws ServletException {

		usuarios = new ArrayList<String>();
		usuarios.add("bob");
		usuarios.add("alice");
		usuarios.add("charlie");

		super.init(config);
		this.config = config;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		String pagina = "";

		if (request.getParameter("cerrarsesion") != null) {
			sesion.invalidate();
			pagina = LOGIN_JSP;

		} else if (sesion.getAttribute("user") != null) {
			pagina = LISTADO_JSP;
		} else {
			pagina = LOGIN_JSP;

		}

		config.getServletContext().getRequestDispatcher(pagina).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession sesion = request.getSession();
		String nombre = request.getParameter("name");
		String password = request.getParameter("key");
		String pagina = LOGIN_JSP;

		Map<String, String> errores = new HashMap<String, String>();

		if (nombre.equals("") || password.equals("")) {

			if (nombre.equals("")) {
				errores.put("nombre", "El nombre no puede quedar en blanco");
			}
			if (password.equals("")) {
				errores.put("clave", "El campo password no puede estar vacío");

			}

			request.setAttribute("errores", errores);
			pagina = LOGIN_JSP;
		}

		else if (usuarios.contains(nombre)) {
			sesion.setAttribute("name", nombre);
			request.setAttribute("name", nombre);
			request.setAttribute("key", password);
			request.setAttribute("users", usuarios);
			pagina = LISTADO_JSP;

		} else {
			// errores.put("usuario", "El usuario o la contraseña es
			// incorrecto");
			request.setAttribute("errores", errores);
			pagina = ERROR_JSP;

		}

		this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
	}

}
