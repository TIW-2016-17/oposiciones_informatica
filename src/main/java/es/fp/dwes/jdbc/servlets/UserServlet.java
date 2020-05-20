package es.fp.dwes.jdbc.servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		@WebInitParam(name = "configuracion", value = "es.tessier.jdbc.persistencia") })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String LISTADO_JSP = "/listado.jsp";

	private ServletConfig config;
	private UserDAOImpl dao;
	private Connection con;
	private String configuracion;
	private static final String ADD = "ADD", EDIT = "EDIT", DELETE = "DELETE";

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

	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
	}

	/**
	 * Obtiene los datos del usuario a editar o borrar
	 * 
	 * @param request
	 * @return
	 */
	private User getUserData(HttpServletRequest request) {
		
		User user = new User(request.getParameter("name"), request.getParameter("last_name"),
				request.getParameter("user"), request.getParameter("password"));
		if(request.getParameter("id")!=null) {
			user.setId(Integer.parseInt(request.getParameter("id")));
		}
		return user;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("action");
		ArrayList<User> listaUsuarios = new ArrayList<User>();

		try {
			listaUsuarios = (ArrayList<User>) dao.listUsers();
		} catch (SQLException e) {
			
		}
		request.setAttribute("usuarios", listaUsuarios);
		
			
		
		
		config.getServletContext().getRequestDispatcher(LISTADO_JSP).forward(request, response);
	}

	/**
	 * Modifica los datos del usuario con el UsuarioDao
	 * 
	 * @param usuario
	 */
	private void updateUser(User user) throws SQLException {
		
		dao.updateUser(user);
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
	private void addUser(User user) throws SQLException {

		dao.createUser(user);

	}
}
