package es.fp.dwes.madrid2018;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreaSesion")
public class CreaSesion extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// 1.1) Se vacía la sesión si existe

			// 1.2) Se crea una sesión asociada a la petición

			// 1.3) Se imprime el contenido de la sesión vacía

			// 1.4) Se registran variables de sesión (objetos de diferentes
			// clases)

			// 1.5) Se crea y visualiza el enlace al script RecuperaSesión

		} catch (Exception e) {
			out.println("Se produce una excepción <br />");
			out.println(e.getMessage());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
