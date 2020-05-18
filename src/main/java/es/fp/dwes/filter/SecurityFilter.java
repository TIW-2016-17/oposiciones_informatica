package es.fp.dwes.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/listServlet")
public class SecurityFilter implements Filter {

	public SecurityFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// accedemos a la sesion del servidor usando un casting al objeto
		// HttpServletRequest ya que nos pasan solamente ServletRequest
		HttpSession sesion = ((HttpServletRequest) request).getSession();
		// buscamos el token de autenticacion
		if (sesion.getAttribute("autenticado") != null && 
                   (boolean) sesion.getAttribute("autenticado")) {
			chain.doFilter(request, response);
		} else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}


