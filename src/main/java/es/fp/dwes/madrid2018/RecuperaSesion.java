package es.fp.dwes.madrid2018;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RecuperaSesion", urlPatterns = { "/RecuperaSesion" })
public class RecuperaSesion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer entero;
		Double doble;
		String texto;
		Date fecha;
		LinkedHashMap<?, ?> semaforo;

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			HttpSession sesion = request.getSession();

			// 2.1) Se recuperan las variables de sesión previamente creadas

			entero = (Integer) sesion.getAttribute("entero");
			doble = (Double) sesion.getAttribute("doble");
			texto = (String) sesion.getAttribute("texto");
			fecha = (Date) sesion.getAttribute("fecha");
			semaforo = (LinkedHashMap<?, ?>) sesion.getAttribute("semaforo");
			Punto punto = (Punto) sesion.getAttribute("punto");
			// 2.2) Se imprime el contenido de la sesión
			out.println("entero : " + entero + "<br />");
			out.println("doble : " + doble+ "<br />");
			out.println("texto : " + texto + "<br />");
			out.println("fecha : " + fecha+ "<br />");
			out.println("semaforo : " + semaforo + "<br />");
			out.println("Punto : " + punto+ "<br />");

			out.println("<p>Imprimir todas </p>");
			imprimirSesion(out, sesion);
						

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
