package es.fp.dwes.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Connector {
	
private static Connector connector = new Connector();
	
	private  Connector() {

	}
	public static Connector getInstance(){
		if (connector == null) {
			connector = new Connector();
		}
		return connector;
	}

	public Connection crearConexionMySQL(ResourceBundle properties){
		
		String bbdd = properties.getString("bbdd");
		String driver = properties.getString("driver");
		String usuario = properties.getString("user");
		String clave = properties.getString("password");
		String esquema = properties.getString("scheme");
		
		Connection con = null;
		try {
			Class.forName(driver);
			StringBuilder sb = new StringBuilder();
			sb.append(bbdd);
			sb.append("/");
			sb.append(esquema);
			con = DriverManager.getConnection(sb.toString(),usuario,clave);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	public Connection createConnectionMySQLWithJNDI(ResourceBundle properties){
		DataSource datasource = null;
		Connection con=null;
		try {
			Context ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup(properties.getString("datasource"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
			con = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public Connection createConnectionOracle(ResourceBundle properties){
		return null;
	}
	public Connection createConnectionMSSQL(ResourceBundle properties){
		return null;
	}
	public Connection crearConexionMySQLConJNDI(ResourceBundle rb) {
		return null;
	}
}
