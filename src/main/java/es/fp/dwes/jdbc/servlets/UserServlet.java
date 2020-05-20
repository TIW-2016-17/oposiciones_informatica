package es.fp.dwes.jdbc.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.fp.dwes.jdbc.Connector;
import es.fp.dwes.jdbc.daos.UserDAOImpl;
import es.fp.dwes.domains.User;

@WebServlet(name = "UserServlet", urlPatterns = { "/user" }, initParams = {
		@WebInitParam(name = "configuracion", value = "es.fp.dwes.jdbc.persistencia") })
public class UserServlet extends HttpServlet {
	private static final String ATRIBUTO_MENSAJES = "mensajes";
	private static final String PARAMETRO_PASSWORD = "password";
	private static final String PARAMETRO_LASTNAME = "lastname";
	private static final String PARAMETRO_NAME = "name";
	private static final String PARAMETRO_ID = "id";
	private static final String PARAMETRO_ACTION = "accion";
	private static final String ATRIBUTO_ERRORES = "errores";
	private static final String ATRIBUTO_USER = "user";
	private static final String ATRIBUTO_USERS = "users";

	private static final long serialVersionUID = 1L;

	private static final String LISTADO_JSP = "/listado.jsp";

	private ServletConfig config;
	private UserDAOImpl dao;
	private Connection con;
	private String configuracion;
	private static final String ADD = "ADD", EDIT = "EDIT", DELETE = "DELETE";
	private static final String EDIT_USER_JSP = "/jdbc/editUser.jsp";
	private static final String ADD_USER_JSP = "/jdbc/addUser.jsp";
	private static final String LOGIN_JSP = "/login.jsp";

	public void init(ServletConfig config) throws ServletException {
		this.config = config;

		configuracion = this.config.getInitParameter("configuracion");
		ResourceBundle rb = ResourceBundle.getBundle(configuracion);
		Connector conector = Connector.getInstance();
		con = conector.crearConexionMySQL(rb);
		dao = new UserDAOImpl();
		dao.setConnection(con);
		dao.setQuerys(rb);

	}

	@Override
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = LOGIN_JSP;
		User user = new User();
		ArrayList<User> listaUsuarios = new ArrayList<User>();
		Map<String, String> errores = new HashMap<String, String>();
		Map<String, String> mensajes = new HashMap<String, String>();
		String accion = request.getParameter(PARAMETRO_ACTION);

		try {

			if (accion.equals(ADD)) {				
				page = ADD_USER_JSP;

			} else if (accion.equals(EDIT)) {
				user = dao.getUserByKey(Integer.parseInt(request.getParameter(PARAMETRO_ID)));
				request.setAttribute(ATRIBUTO_USER, user);
				page = EDIT_USER_JSP;

			} else if (accion.equals(DELETE)) {

				user = dao.getUserByKey(Integer.parseInt(request.getParameter(PARAMETRO_ID)));
				deleteUser(user);
				page = LISTADO_JSP;
				mensajes.put("ADD", "usuario "+user.getId()+" "+user.getUser()+" borrado correctamente");


			}
		} catch (NumberFormatException e) {
			errores.put("NumberFormatException", "NumberFormatExceptiono en "+accion);
			
		} catch (SQLException e) {
			errores.put("SQLException", "SQLException en "+accion+ " con usuario "+user+" "+e.getMessage());

		} catch (Exception e) {
			errores.put("Exception", "Exception en "+accion);

		}
		finally{
			try {
				listaUsuarios = (ArrayList<User>) dao.listUsers();
			} catch (SQLException e) {
				errores.put("error", "Exception listar usuarios"+e.getMessage());
			}
			
		}
		request.setAttribute(ATRIBUTO_MENSAJES, mensajes);
		request.setAttribute(ATRIBUTO_ERRORES, errores);
		request.setAttribute(ATRIBUTO_USERS, listaUsuarios);

		config.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<User> listaUsuarios = new ArrayList<User>();
		Map<String, String> errores = new HashMap<String, String>();
		Map<String, String> mensajes = new HashMap<String, String>();

		String accion = request.getParameter(PARAMETRO_ACTION);

		try {
			
			User usuario = getUserData(request);		
			
			if (accion.equalsIgnoreCase(ADD)) {

				usuario = addUser(usuario);				
				mensajes.put("ADD", "usuario "+usuario.getId()+" "+usuario.getUser()+" añadido correctamente");
			}

			if (accion.equalsIgnoreCase(EDIT)) {

				usuario = updateUser(usuario);
				mensajes.put("EDIT", "usuario "+usuario.getUser()+" editado correctamente");

			}			

			
		} catch (NumberFormatException e) {
			errores.put("error", "NumberFormatException "+e.getMessage());
		} catch (SQLException e) {
			errores.put("error", "SQLException "+e.getMessage());
		} catch (Exception e) {
			errores.put("error", "Exception "+e.getMessage());
		}
		finally{
			try {
				listaUsuarios = (ArrayList<User>) dao.listUsers();
			} catch (SQLException e) {
				errores.put("error", "Exception "+e.getMessage());
			}
			
		}
		request.setAttribute(ATRIBUTO_MENSAJES, mensajes);
		request.setAttribute(ATRIBUTO_ERRORES, errores);
		request.setAttribute(ATRIBUTO_USERS, listaUsuarios);
		config.getServletContext().getRequestDispatcher(LISTADO_JSP).forward(request, response);
	}



	/**
	 * Obtiene los datos del usuario a editar o borrar
	 * 
	 * @param request
	 * @return
	 */
	private User getUserData(HttpServletRequest request) {

		User user = new User(request.getParameter(PARAMETRO_NAME), request.getParameter(PARAMETRO_LASTNAME),
				request.getParameter(ATRIBUTO_USER), request.getParameter(PARAMETRO_PASSWORD));
		
		if (request.getParameter(PARAMETRO_ID) != null) {
			user.setId(Integer.parseInt(request.getParameter(PARAMETRO_ID)));
		}
		
		return user;
	}


	/**
	 * Modifica los datos del usuario con el UsuarioDao
	 * 
	 * @param usuario
	 */
	private User updateUser(User user) throws SQLException {

		return dao.updateUser(user);
	}

	/**
	 * Borra los datos de un usuario con el UsuarioDao
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	private void deleteUser(User user) throws SQLException {

		dao.deleteUser(user);

	}

	/**
	 * Crea un usuario en la base de datos con el UsuarioDao
	 * 
	 * @param user
	 */
	private User addUser(User user) throws SQLException {

		return dao.createUser(user);

	}
}
