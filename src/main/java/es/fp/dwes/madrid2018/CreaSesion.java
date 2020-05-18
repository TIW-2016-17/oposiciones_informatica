package es.fp.dwes.madrid2018;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CreaSesion")
public class CreaSesion extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7869567684621899346L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		LinkedHashMap<String, Integer> semaforo;
		HttpSession sesion;

		response.setContentType("text/html;charset=UTF-8");
		try {

			sesion = request.getSession(false);
			// 1.1) Se vacía la sesión si existe
			if (sesion != null) {
				sesion.invalidate();
			}
			// 1.2) Se crea una sesión asociada a la petición
			sesion = request.getSession(true);
			// 1.3) Se imprime el contenido de la sesión vacía
			imprimirSesion(out, sesion);
			// 1.4) Se registran variables de sesión (objetos de diferentes
			// clases)
			sesion.setAttribute("entero", 7);
			sesion.setAttribute("doble", 3.1416);
			sesion.setAttribute("texto", "ola ke ase");
			sesion.setAttribute("fecha", new SimpleDateFormat("dd/MM/yyyy").parse("30/04/2020"));
			semaforo = new LinkedHashMap<String, Integer>();
			semaforo.put("wait", -1);
			semaforo.put("signal", 1);
			sesion.setAttribute("semaforo", semaforo);
			sesion.setAttribute("punto", new Punto(1, 2));

			// 1.5) Se crea y visualiza el enlace al script RecuperaSesión
			out.println("<a href=\"RecuperaSesion\">Recupera Sesión</a>");

		} catch (Exception e) {
			out.println("Se produce una excepción <br />");
			out.println(e.getMessage());
		}

	}

	private void imprimirSesion(PrintWriter out, HttpSession sesion) {
		Enumeration<String> atributos;
		String atributo;
		atributos = sesion.getAttributeNames();
		while (atributos.hasMoreElements()) {
			atributo = (String) atributos.nextElement();
			out.println(atributo + " : " + sesion.getAttribute(atributo) + "<br />");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
