package es.fp.dwes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext context;
	//private ServletConfig config;

	 private List<String> usuarios;


	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.context = config.getServletContext();// ServletContext
		//this.config = config;
		usuarios = new ArrayList<String>();
		usuarios.add("bob");
		usuarios.add("alice");
		usuarios.add("charlie");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");


		// 1.Creación del formulario vacío inicial y lógica de negocio
		if (request.getParameter("name") == null  ) { // solo ocurre en la primera llamada			
		
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type \" content= \"text/html; charset=ISO-8859-1 \">");
			out.println("<title>login.jsp</title>");
			out.println("<style>");
			out.println("	.error { color:red;}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Ejercicio 5 LoginServlet</h1>");
			out.println("<fieldset>");
			out.println(" <legend>Registro cliente</legend>");
			out.println("<form action='loginServlet' method='POST'>");
			out.println("<input type='text' name='name' placeholder='user'> <br> ");
			out.println("<input type='password' name='key' placeholder='password'> <br>");
			out.println("<input type='submit' value='Send'>");
			out.println("</form>");
			out.println("</fieldset>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		
		String nombre = request.getParameter("name");
		String password = request.getParameter("key");					
			
		if ( nombre.equals("") || password.equals("") || !usuarios.contains(nombre)) {
				
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta http-equiv=\"Content-Type \" content= \"text/html; charset=UTF-8\">");
			out.println("<title>login servlet</title>");
			out.println("<style>");
			out.println("	.error { color:red;}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Ejercicio 5 LoginServlet</h1>");
			out.println("<fieldset>");
			out.println(" <legend>Registro cliente</legend>");
			out.println("<form action='loginServlet' method='POST'>");
			out.println("<input type='text' name='name' value='" + nombre + "' placeholder='user'>  ");
			if (nombre.equals(""))
				out.println("<span class=\"error\"> El nombre no puede estar vacio</span>");
			out.println("</br>");

			out.println("<input type='password' name='key' value='" + password + "' placehold-er='password'> ");
			if (password.equals(""))
				out.println("<span class=\"error\">  La contraseña no puede estar vacia</span>");
			out.println("</br>");

			if (!nombre.equals("") && !password.equals("") && !usuarios.contains(nombre)){
				
				RequestDispatcher reqDis = context.getRequestDispatcher("/errorUsuario");
				reqDis.forward(request, response);
			}

			out.println("<input type='submit' value='Send'>");
			out.println("</fieldset>");
			out.println("</form>");
			
			out.println("</body>");
			out.println("</html>");
			
		} else {
			request.setAttribute("name",nombre);
			request.setAttribute("key", password);
			request.setAttribute("users", usuarios);
		
            RequestDispatcher reqDis = context.getRequestDispatcher("/listServlet");
			reqDis.forward(request, response);

		}
		
	}
	
} 	
