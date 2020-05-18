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
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ServletContext context;

	private List<String> usuarios;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.context = config.getServletContext();// ServletContext
		usuarios = new ArrayList<String>();
		usuarios.add("bob");
		usuarios.add("alice");
		usuarios.add("charlie");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
		
		HttpSession sesion = request.getSession();		
		boolean autenticado;
		
		autenticado = (sesion != null && sesion.getAttribute("autenticado") != null
				&& (Boolean) sesion.getAttribute("autenticado"));
		
		if (request.getParameter("cerrarsesion") != null) {
			sesion.invalidate();
			autenticado = false;
		}

		if (autenticado) {			
			RequestDispatcher reqDis = context.getRequestDispatcher("/listServlet");
			reqDis.include(request, response);
		}
		
		else {
            response.sendRedirect("login.html");

		}		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sesion = request.getSession();
		
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
			out.println("<h1>Ejercicio 6 LoginServlet</h1>");
			out.println("<fieldset>");
			out.println(" <legend>Registro cliente</legend>");
			out.println("<form action='loginServlet' method='POST'>");
			out.println("<input type='text' name='name' value='" + nombre + "' placeholder='user'>  ");
			if (nombre.equals(""))
				out.println("<span class=\"error\"> El nombre no puede estar vacio</span>");
			out.println("</br>");

			out.println("<input type='password' name='key' value='" + password + "' placeholder='password'> ");
			if (password.equals(""))
				out.println("<span class=\"error\">  La contraseña no puede estar vacia</span>");
			out.println("</br>");

			if (!nombre.equals("") && !password.equals("") && !usuarios.contains(nombre))
				out.println("<p class=\"error\">Usuario o nombre incorrecto</p>");

			out.println("<input type='submit' value='Send'>");
			out.println("</fieldset>");
			out.println("</form>");
			
			out.println("</body>");
			out.println("</html>");
			
		} else {
			sesion.setAttribute("autenticado", true);
			sesion.setAttribute("name", nombre);
			sesion.setAttribute("key", password);
			sesion.setAttribute("users", usuarios);			
		
            RequestDispatcher reqDis = context.getRequestDispatcher("/listServlet");
			reqDis.forward(request, response);

		}

		
	}

}


