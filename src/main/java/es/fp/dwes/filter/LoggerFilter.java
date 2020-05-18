package es.fp.dwes.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;


@WebFilter("/*")
public class LoggerFilter implements Filter {
	private static final String  SEPARADOR = " - ";
   
    public LoggerFilter() {
    }


	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//simplemente crea una estadística de acceso y la muestra por pantalla.
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		sb.append(sdf.format(new Date()));
		sb.append(SEPARADOR);
		
		sb.append(request.getRemoteAddr());
		sb.append(SEPARADOR);
		sb.append(((HttpServletRequest)request).getProtocol()+SEPARADOR+((HttpServletRequest)request).getMethod());
		sb.append(SEPARADOR);
		String url = request.getScheme()+"://"+request.getServerName()+request.getLocalPort()+((HttpServletRequest)request).getContextPath()+
				((HttpServletRequest)request).getServletPath();
		sb.append(url);
		
		System.out.println(sb.toString());
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}


	
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
