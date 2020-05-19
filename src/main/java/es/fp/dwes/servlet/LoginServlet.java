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

import es.fp.dwes.domains.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LOGIN_JSP = "/login.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private static final String LISTADO_JSP = "/listado.jsp";
	private ServletConfig config;
	private List<User> usuarios;

	@Override
	public void init(ServletConfig config) throws ServletException {

		usuarios = new ArrayList<User>();
		usuarios.add(new User("Bob","Smith","bob","1234"));
		usuarios.add(new User("Alice","Cooper","alice","1234"));
		usuarios.add(new User("Charlie","O'Donell","charlie","1234"));

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

		else if ( (usuario = comprobarUsuario(user,password))!= null) {
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
