package es.fp.dwes.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.fp.dwes.domains.User;
import es.fp.dwes.jdbc.Connector;
import es.fp.dwes.jdbc.daos.UserDAO;
import es.fp.dwes.jdbc.daos.UserDAOImpl;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" }, initParams = {
		@WebInitParam(name = "configuracion", value = "es.fp.dwes.jdbc.persistencia") })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LOGIN_JSP = "/login.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private static final String LISTADO_JSP = "/listado.jsp";
	private ServletConfig config;
	private List<User> usuarios;
	private UserDAO dao;
	private Connection con;
	private String configuracion;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		this.config = config;
		try {
			configuracion = this.config.getInitParameter("configuracion");
			ResourceBundle rb = ResourceBundle.getBundle(configuracion);
			Connector conector = Connector.getInstance();
			con = conector.crearConexionMySQL(rb);
			dao = new UserDAOImpl();
			dao.setConnection(con);
			dao.setQuerys(rb);

			usuarios = (ArrayList<User>) dao.listUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException LoginServlet");
		}
	}

	@Override
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		String pagina = "";

		if (request.getParameter("cerrarsesion") != null) {
			sesion.invalidate();
			pagina = LOGIN_JSP;

		} else {
			pagina = LOGIN_JSP;

		}

		config.getServletContext().getRequestDispatcher(pagina).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		String user = request.getParameter("user");
		String password = request.getParameter("key");
		String pagina = LOGIN_JSP;
		User usuario;
		Map<String, String> errores = new HashMap<String, String>();

		if (user.equals("") || password.equals("")) {

			if (user.equals("")) {
				errores.put("user", "El usuario no puede quedar en blanco");
			}
			if (password.equals("")) {
				errores.put("clave", "El campo password no puede estar vacío");

			}

			request.setAttribute("errores", errores);
			pagina = LOGIN_JSP;
		}

		else if ((usuario = comprobarUsuario(user, password)) != null) {
			sesion.setAttribute("beanSesionUsuario", usuario);
			request.setAttribute("user", user);
			request.setAttribute("key", password);
			request.setAttribute("users", usuarios);
			pagina = LISTADO_JSP;

		} else {
			errores.put("usuario", "El usuario o la contraseña es incorrecto");
			request.setAttribute("errores", errores);
			pagina = ERROR_JSP;

		}

		this.getServletContext().getRequestDispatcher(pagina).forward(request, response);
	}

	private User comprobarUsuario(String user, String password) {
		User usuarioValidado = null;
		for (User usuario : usuarios) {
			if (user.equals(usuario.getUser()) && password.equals(usuario.getPassword())) {
				usuarioValidado = usuario;
				break;
			}
		}
		return usuarioValidado;
	}

}
