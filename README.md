# J2EE

# Contenido

1. [Servlets](#servlets)
2. [JSP](#jsp)
3. [JDBC](#jdbc)

## Servlets

1. Crear un servlet llamado que muestre un mensaje ¡hola mundo!

2. Crear un servlet mapeado como /header que muestre toda la información de la cabecera HTTP (protocolo, método, idioma, dirección remota etc.)

3. En esta ocasión vamos a reutilizar el código del servlet Ejercicio1Servlet
    1. Crea un nuevo servlet en el paquete lab1 que se llame Ejercicio3Servlet
    2. En el URL mapping cambia la cadena por /parametros
    3. Copia el código del ejercicio 1
    4. Crea el código necesario para que ahora el servlet imprima el mensaje: Hola nombre donde nombre será dinámicamente insertado por QUERY\_STRING3
    5. La URL de acceso será: http://localhost:8080/laboratorios/parametros?nombre=Carlos En este caso el servlet responderá: Hola Carlos.

![](RackMultipart20200520-4-nhhawh_html_60a84db685e88c29.png)

4. Crear una página web registroServlet.html que contenga un formulario para registrar el nombre y el email de un cliente y que se enviará a un servlet llamado Ejercicio4Servlet.

```
<!DOCTYPE html>
<html>
<head>
	<title>Formulario Registro</title>
	<meta charset="utf-8>">
</head>
<body>
	<form method="POST" action="/j2ee/loginPost">
	   <fieldset>
		 <legend>Registro cliente</legend>
		<p>
			<label for="nombre">Nombre</label>
		 	<input type="text"  name="name" id="name" value="">
		</p>
		<p>
		 	<label for="password">Contraseña</label>
			<input  type="password"  name="key" id="key" value="">
		</p>
		<input
			type=Submit value="Enviar">
		</fieldset>
		<br>
	</form>
</body>
</html>
```

- El servlet mapeado como loginPost recibirá los parámetros por POST y los mostrará por pantalla.
- El servlet debe validar al usuario y clave enviado emitiendo una respuesta en caso favorable y otra en caso de error en el método\_\_doPost\_\_.
- Las respuestas deben ser con cabecera text/html y código formateado
- El usuario válido es: bob con password 1234
- La respuesta válida debe saludar al usuario con su nombre de usuario. La respuesta inválida debe mostrar un mensaje indicando que no tiene acceso y un enlace para volver al formulario de login


5. Crea un Servlet que cargará una lista de nombres de usuario en el método init() y la hará disponible mediante el objeto request a otro servlet que recorrerá la lista y pintará en una tabla los nombres de los usuarios.
    1. Crear un ArrayList de tipo String en el método init(ServletConfig config) y llénalo con varios nombre de usuarios.
    2. Cuando el usuario sea válido envíale al ListServlet junto con los datos de usuario. En caso contrario mostrará un servlet de error
    3. Necesitarás modificar el objeto request para añadir atributos.
    4. En el ListServlet recupera el atributo de la lista y escribe una tabla dinámica con los datos del ArrayList

6. El objetivo de este ejercicio es que el usuario no tenga que pasar una y otra vez por el formulario de login para acceder al listado de nombres si ya se ha autenticado.

```
<!DOCTYPE html>
<html>
	<head>
	<title>Formulario Login</title>
	<meta charset="utf8">
</head>
<body>
	<form method="POST" action="/j2ee/loginServlet6">	
		<h1>Ejercicio 6 LoginServlet</h1>
	   <fieldset>
       <legend>Registro cliente</legend>
	    <label for="nombre">Nombre</label>
		<input name="name" id="name" value=""><br>
		<label for="password">Contraseña</label>		
		<input name="key" id="key" value="" ><br>
		<input type=Submit value = "Sent">
	   </fieldset>
	<br>
</form>
</body>
</html>
```

- Utilizar un formulario html para pasar los datos mediante Post, mostrar errores al introducir mal el usuario, contraseña o no existir el usuario.
- Crea un HttpSession en el LoginServlet y añade un token a la sesión con el valor autenticado=true si el usuario es correcto y autenticado=false en caso contrario
- LoginServlet debe controlar que si el usuario ya se ha autenticado para acceder directamente a listServlet o devolverle al formulario de login en caso contrario.
- El listServlet debe mostrar además de los datos, un enlace para cerrar la sesión y volver a mostrar el formulario de logueo mediante el parámetro cerrarsesion

7. Crear un filtro que permita controlar y trazar todos los pasos del usuario en el servidor.
    1. Crea un nuevo paquete, por ejemplo es.fp.dwes.filters
    2. Crea un nuevo Filtro en dicho paquete (New-\&gt;Filter)
    3. Pon de Nombre: LoggerFilter
    4. Pulsa Next y en la siguiente pantalla cambia el URL pattern por /*
    5. Next->Finish
    6. Dentro del método doFilter pon el código necesario y termina con chain.doFilter(request,response)
    7. El filtro debería mostrar por consola o en el log del servirdor de glassfish un registro de navegación del usuario del tipo: <fecha>- <ip> - <protocolo> - <método> - <url> . Un filtro avanzado registraría estos datos en un log del sistema, pero simplemente lo sacamos por consola por propósitos de la práctica.
    8. Ahora al navegar normalmente verás por consola todos tus movimientos del tipo

```
| tiw@vagrant:/opt/glassfish/payara41/glassfish/domains/domain1/logs$ tail server.log

[2020-05-15T16:07:31.870+0000] [Payara 4.1] [INFO] [] [] [tid: \_ThreadID=24
\_ThreadName=http-thread-pool(1)] [timeMillis: 1589558851870] [levelValue: 800] [[15-05-2020 04:07:31 - 0:0:0:0:0:0:0:1 - HTTP/1.1 - GET - http://localhost8080/j2ee/loginServlet6]]

[2020-05-15T16:07:38.270+0000] [Payara 4.1] [INFO] [] [] [tid: \_ThreadID=26 \_ThreadName=http-thread-pool(3)] [timeMillis: 1589558858270] [levelValue: 800] [[15-05-2020 04:07:38 - 0:0:0:0:0:0:0:1 - HTTP/1.1 - GET - http://localhost8080/j2ee/listServlet6]]

[2020-05-15T16:07:41.022+0000] [Payara 4.1] [INFO] [] [] [tid: \_ThreadID=27 \_ThreadName=http-thread-pool(4)] [timeMillis: 1589558861022] [levelValue: 800] [[15-05-2020 04:07:41 - 0:0:0:0:0:0:0:1 - HTTP/1.1 - GET - http://localhost8080/j2ee/loginServlet6]]
```

8. Aunque permitimos que el usuario no necesite volver a pasar por el formulario de login mediante sesiones, eso no impide que un usuario que conozca la página listServlet pueda acceder directamente a ella sin autenticarse.
 Por lo que en este ejercicio se propone controlar el acceso a la página listado.jsp mediante un filtro que compruebe si el usuario dispone del token de autenticación en sesión.
    1. Crea un nuevo filtro en el paquete filters
    2. Nombre: SecurityFilter
    3. URL pattern: /listServlet
    4. Pon el código necesario en el método doFilter para controlar si el usuario dispone de sesión para acceder, en caso contrario redirigirle al registroUsuario.html.

````

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

@WebFilter("/listServlet6")
public class SecurityFilter implements Filter {

	public SecurityFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		}

	}

	public void init(FilterConfig fConfig) throws ServletExcep-tion {
	}

}

````

9. **Ejercicio Oposición Secundaria Informática Madrid 2018.** Desarrolle una mini-aplicación en java que conste de los siguientes scripts:

- **CreaSesion.java.**
    En este primer servlet:
    - Se crea/recupera una sesión.
    - Se resetea su contenido (se elimina cualquier dato que tuviera)
    - Se registran 6 variables de sesión con lo siguientes identificadores y tipos:
      1. entero: un número entero
      2. real: un número real
      3. texto: una cadena de texto
      4. fecha: una fecha (objeto DateTime)
      5. semaforo (array asociativo)
      6. unPunto (objeto Punto)
  2. La clase Punto ha sido codificada previamente con dos atributos (x e y) y sus métodos constructores, getters y setters.
    - Se imprimirá el contenido de la sesión antes de rellenarse.
    - Este script generará un enlace a RecuperaSesion.java en el que se recuperará la sesión.
  3. **RecuperaSesion.java.**

    En este script se recupera la sesión creada en el primer script y se visualiza el contenido de las 6 variables contenidas en su interior.

 Nota: EL código de la clase Punto se incluye con el enunciado, además se incluye un esqueleto del programa, debiendo rellenar los huecos con el código correspondiente. En su hoja de respuesta incluirá el número del comentario donde incluiría el código, y el desarrollo del mismo.
 
**Punto.java**
````
public class Punto {
	private int x;
	private int y;

	public Punto() {
	}

	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return this.x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	

````

**Script CreaSesion.java**

````
package sevlets;
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

import auxiliar.Punto;
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

 // 1.4) Se registran variables de sesión (objetos de diferentes clases)

 // 1.5) Se crea y visualiza el enlace al script RecuperaSesión

 }
 catch (Exception e){
 out.println("Se produce una excepción <br />");
 out.println(e.getMessage());
 }
 }
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 }
}

````
**Script RecuperaSesion.java**

````
package sevlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(name = "RecuperaSesion", urlPatterns = {"/RecuperaSesion"})
public class RecuperaSesion extends HttpServlet {
@Override
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 PrintWriter out = response.getWriter();
 try {

// 2.1) Se recuperan las variables de sesión previamente creadas

 // 2.2) Se imprime el contenido de la sesión

 catch (Exception e){
 out.println("Se produce una excepción <br />");
 out.println(e.getMessage());
 }
 }
 @Override
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
 throws ServletException, IOException {
 }
}


````

10. Dado la página web myForm.html que incluye un formulario que llama al servlet MyServlet. Modificar el código del servlet para que cuando sea llamada muestre la página web con los valores recibidos del formulario

````
<!DOCTYPE html>
<html>
<head>    
    <meta charset="UTF-8">
    <title>My form</title>
</head>
<body>

     <form method="post" action="MyServlet">
     <hr>
     <h2>
       Client registration<br>
       Name <input name="name" value=""><br>
       Email <input name="email" value="" ><br>
       <input type="checkbox" name="forward" value="y"> Forward<br>
       <input type="submit" value = "send">
     </h2>
     <br>
    </form>

</body>
</html>

````
**MyServlet.java**
````
import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.Enumeration;

public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;       
	
    public MyServlet() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
		
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	protected void doPost(HttpServletRequest request, HttpS-ervletResponse response) throws ServletException, IOException {
	
	}

}

````

11. Modificar el método post creado previamente, para que en caso de un campo del formulario llamado &#39;forwarding&#39; tenga el valor &quot;Y&quot;, el servlet redirija la salida a una página html de la aplicación
12.  Crear un método get que genere una página web que muestra el número de veces que el servlet ha sido llamado a través del método get (usar la aplicación del contexto para guardar el valor )
13.  Modificar el método get para que imprima en la consola el número de visitas por sesión del cliente
14.  Escribir un método init del servelt que imprima a través de la consola el valor del parámetro de configuración del servlet llamado author
15.  Modificar la definición del proyecto reemplazando la descripción del servlet en el fichero del descriptor por anotaciones. Necesitarás: asegurarte de que tu proyecto utiliza API 3.0 de Servlet ( propiedades del proyecto, seleccionar Project Facets en el menú izquierdo . Cambiar la versión de Dynamic Web Module facet a 3.0. Necesitar importar el paquete javax.servlet.annotation para utilizar anotaciones en el servlet. Ejemplo: import javax.servlet.annotation.WebServlet

## JSP

16. Crea una página JSP que muestre:
    1. la hora actual del sistema   
    2. El valor del número e
    3. Valor del parámetro GET prueba


17. Crear una página jsp que utilice scriptlets para mostrar el número de visitas de la página.

18. Crea una página JSP que devuelva el factorial del número introducido en un formulario.
    1. Controla que no se introduzca ningún número o que se envíe un carácter no numérico.
    2. Está preparado para recibir por parámetro el número sobre el que se operará.
    3. Si no se recibe, muestra el formulario para introducir el número. Al enviarlo, se llama a sí mismo.
    4. Si se recibe, muestra igualmente el formulario y el factorial del número que se recibió, siempre y cuando sea correcto (no nulo y numérico).
    5. Hace uso de la función/método factorial, declarada en el propio JSP.`

19. Incorporar html dentro del propio servlet no es muy adecuado. En este ejercicio sacaremos el código html y lo serviremos desde páginas dinámicas.
    1. Crea una página JSP llamada login.jsp dentro de Deployed Resources-\&gt;webapp con botón derecho-\&gt;new-\&gt;JSP File
    2. Incopora el código html de la página registroUsuario.html
    3. Crea dos páginas jsp más:
    - listado.jsp: contendrá el código que muestra el RegistroServletPost en caso de que exista el usuario
    - error.jsp: mostrará el mensaje de error actual del Ejercicio4Servlet
    4. Borra el código que has incorporado a las jsps del Ejercicio4Servlet y susitúyelo por un RequestDispatcher haciendo un forward
    5. Copia el Ejercicio4Servlet a un nuevo servlet llamado LoginServlet mapeado como login

20. Refactorizar LoginServlet teniendo como base Ejercicio4Servlet para que utilice los jsp: login.jsp, listado.jsp y error.jsp.
  1. Refactorizar los jsp para que mantenega la funcionalidad de LoginServlet
  2. LoginServlet guardará los usuarios en un arrayList y el usuario en la sesión
  3. Mostrar errores si no se completan todos los campos en en login.jsp a través de un Map
  4. Guardar los usuarios en un arrayList y obtener el usuario mostrarlos en listado.jsp
21. En este ejercicio vamos a refactorizar nuestras vistas añadiendo JSP actions. También vamos a sacar los datos del array de usuarios para hacerlo más dinámico y funcional mediante una capa de objetos de dominio.
    1. Crea una clase java normal llamada User en un nuevo paquete  dominios que tenga las siguiente propiedades y sus correspondientes métodos get/set
    
````
    String name;
    String lastName;
    String user;
    String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this. name = name;
    }
    ...

````

    2. Refactorizar LoginServlet para:
    - Utilizar un ArrayList de objetos User
    - Que tenga una lógica nueva que permita acceder a cualquier usuario/password definidos en esa lista
    - Que pase al objeto user autenticado en sesión para que sea recibido por cualquier página a la que tenga acceso

22. En este ejercicio vamos a refactorizar nuestras vistas añadiendo Expression Language (EL)6 y usando Taglibs JSTL para mejorarlas. También vamos a sacar los datos del array de usuarios para hacerlo más dinámico y funcional mediante una capa de objetos de dominio.

  1. Modifica el código de login.jsp sustituyendo todo el código java por jstl y EL
    - Para que funcione jstl hay que añadirlo como dependencia en el archive pom.xml

```

<dependency>
   	<groupId>jstl</groupId>
    	<artifactId>jstl</artifactId>
   	 <version>1.2</version>
</dependency>

```

  2.  Haz lo mismo con la página listado.jsp y error.jsp

24. Modificar el formulario de la siguiente página html para que llame se envie al jsp myFirst.jsp y modificar el jsp llamado myFirst.jsp para que muestre los datos introducidos en el formulario


````
<!DOCTYPE html>
<html>
<head>    
    <meta charset="UTF-8">
    <title>My form</title>
</head>
<body>
     <form>
     <hr>
     <h2>
       Client registration<br>
       Name <input name="name" value=""><br>
       Email <input name="email" value="" ><br>
       <input type="checkbox" name="forward" value="y"> Forward<br>
       <input type="submit" value ="send">
     </h2>
     <br>
    </form>
</body>
</html>
````


25. Modificar el fichero myFirstJSP.jsp para que incluya un saludo que muestre el nombre recibido si coincide con un nombre dado, por ejemplo pepe.
26. Modificar el fichero jsp para que la información se guarde utilizando el bean beanform. Pasos
    1. Crear una instancia del bean (clase BeanForm)
    2. Guardar los valores de la petición en la instancia del bean
    3. Mostrar los valores name, email y forward obtenidos del bean

26. Modificar el fichero jsp myFirstJSP.jsp después de mostrar la información del cliente, muestra una lista con todos los clientes introducidos hasta ahora. Para ello necesitaras guardar los clientes introducidos en algún lugar, por ejemplo en la sesión.
27. Mejorar el fichero jsp myFirstJSP.jsp para quitar los scrliptslet por EL y JSLT.

## JDBC

28. Modificar el dominio User para añadirle un campo nuevo int id que servirá para identificar la clave primaria
  1. Creamos una nueva estructura de paquetes:
  
````
../jdbc
../daos
../servlets
````


  2. En el paquete DAOs, incorporamos:
    - Una interfaz llamada UserDAO que definirá las operaciones
        1. Operaciones básicas de un CRUD (altas, bajas y modificaciones)
        2. Para manejar las conexiones


````
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;

import  es.fp.dwes.domains.User;

public interface UserDAO {
	
	public abstract User updateUser(User user) throws SQLException;
	public abstract void deleteUser(User user) throws SQLException;
	public abstract User createUser(User newUser) throws SQLException;
	public abstract User getUserByName(String name) throws SQLException;
	public abstract User getUserByKey(int pk) throws SQLException;
	public abstract Collection<User> listUsers() throws SQLException;
	public abstract void setConnection(Connection con);
	public abstract void setQuerys(ResourceBundle rb);
}
````

    - Una clase que implementa dicha interfaz que se llamará UserDAOImpl que contendrá las operaciones reales contra la base de datos.
    - La clase UsuarioDAOImpl tiene el código JDBC para insertar y recuperar datos de la base de datos (Statement, PreparedStatement, ResultSet y Connection)

````
 
package es.fp.dwes.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import es.fp.dwes.domains.User;

public class UserDAOImpl implements UserDAO {

	private Connection con;
	private ResourceBundle rb;
        
	@Override
	public Collection<User> listUsers() throws SQLException{
		Statement st = con.createStatement();
		ResultSet resultados = st.executeQuery(rb.getString("selectAllUsers"));
		
		List<User> listUsers = new ArrayList<User>();
		User user;
		while (resultados.next()) {
			user = new User(resultados.getInt("id"),
					resultados.getString("name"),
					resultados.getString("last_name"),
					resultados.getString("user"),
					resultados.getString("password"));
			
			listUsers.add(user);
		}
		return listUsers;
	}
	@Override
	public User getUserByKey(int pk) throws SQLException{
		PreparedStatement ps = con.prepareStatement(rb.getString("selectUserPK"));
		ps.setInt(1, pk);
		User user = null;
		ResultSet resultados = ps.executeQuery();
		while (resultados.next()) {
			user = new User(resultados.getInt("id"),
					resultados.getString("name"),
					resultados.getString("last_name"),
					resultados.getString("user"),
					resultados.getString("password"));
		}
		return user;
	}
	@Override
	/**
	 * Se asume que el campo nombre es unique y por tanto solo recuperará un usuario en caso de existir
	 */
	public User getUserByName(String name) throws SQLException{
		PreparedStatement ps = con.prepareStatement(rb.getString("selectUserName"));
		ps.setString(1, name);
		User user = null;
		ResultSet resultados = ps.executeQuery();
		while (resultados.next()) {
			user = new User(resultados.getInt("id"),
					resultados.getString("name"),
					resultados.getString("last_name"),
					resultados.getString("user"),
					resultados.getString("password"));
			
		}
		return user;
	}
	@Override
	public User createUser(User newUser) throws SQLException{
		
		PreparedStatement ps = con.prepareStatement(rb.getString("createUser"));
		ps.setString(1, newUser.getName());
		ps.setString(2, newUser.getLastName());
		ps.setString(3, newUser.getUser());
		ps.setString(4, newUser.getPassword());
		ps.execute();
	
		return getUserByName(newUser.getName());
	}
	@Override
	public void deleteUser(User user) throws SQLException{
		PreparedStatement ps = con.prepareStatement(rb.getString("deleteUser"));
		ps.setInt(1, user.getId());
		ps.execute();
		
	}
	@Override
	public User updateUser(User user) throws SQLException{
		
		PreparedStatement ps = con.prepareStatement(rb.getString("updateUser"));
		ps.setString(1, user.getName());
		ps.setString(2, user.getLastName());
		ps.setString(3, user.getUser());
		ps.setString(4, user.getPassword());
		ps.setInt(5, user.getId());
		ps.execute();

		return getUserByKey(user.getId());
		
	}
	@Override
	public void setConnection(Connection con) {
		this.con = con;
		
	}
	@Override
	public void setQuerys(ResourceBundle rb) {
		this.rb = rb;
		
	}

}


````


    2. Creamos una clase nueva llamada Conector y un fichero de texto plano: persistencia.properties
    
    La clase Conector gestiona las conexiones con varias bases de datos y utiliza un patrón Singleton para devolver una sola instancia. Para flexibilizar las conexiones con varias bases de datos de manera local o en remoto con un DataSource, esta clase hace uso de un fichero .properties


````
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

````

    3. El fichero persistencia.properties es invocado tanto por el Conector como por el DAO, lo que permite modificar valores de bases de datos, drivers, conexiones y sentencias sin tener que recompilar las clases, y se carga mediante el objeto ResourceBundle4


````
#file of properties for  managing the conexion and the sentences
bbdd=jdbc:mysql://localhost:3306
scheme=tiw
user=root
password=admin
driver=com.mysql.jdbc.Driver

#string for the conexion with the JNDI DataSource
datasource=jdbc/tiw

#sentences for user
selectAllUsers=SELECT * FROM user
selectUserPK=SELECT * FROM user WHERE id=?
selectUserName=SELECT * FROM user WHERE name=?
deleteUser=DELETE FROM user WHERE id=?
updateUser=UPDATE user SET name=? , last_name=? , user=? , password=? WHERE id=?
createUser=INSERT INTO user (name, last_name, user, password) VALUES (?,?,?,?)

````

    4. El LoginServlet debe cambiar ahora su código eliminando la creación de la lista de usuarios en memoria y usar el DAO para sacar los usuarios directamente de la base de datos.
    - Para ello hace uso de la clase conector y pasa la conexión y el objeto ResourceBundle al DAO.
    - El servlet recupera la conexión de un DataSource remoto, por lo que previamente habría que crearlo en el servidor.


````
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" }, initParams = {
		@WebInitParam(name = "configuracion", value = "es.fp.dwes.jdbc.persistencia") })
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String LOGIN_JSP = "/login.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private static final String LISTADO_JSP = "/listado.jsp";
	private ServletConfig config;
	private List<User> usuarios;
	private UserDAO dao;
	private Connection con;
	private String configuracion;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		this.config = config;
		try {
			configuracion = this.config.getInitParameter("configuracion");
			ResourceBundle rb = Resource-Bundle.getBundle(configuracion);
			Connector conector = Connector.getInstance();
			con = conector.crearConexionMySQL(rb);
			dao = new UserDAOImpl();
			dao.setConnection(con);
			dao.setQuerys(rb);

			usuarios = (ArrayList<User>) dao.listUsers();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException LoginServlet");
		}
	}

	@Override
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


````

    5. Ya sólo nos queda crear la tabla USUARIOS en el esquema de base de datos tiw con las columnas emparejadas a las propiedades de la clase User y el driver al fichero pom.xml para que todo funcione adecuadamente.
    
````
    use tiw;
CREATE TABLE user (
       id int unsigned NOT NULL AUTO_INCREMENT,
       name varchar(20) NOT NULL,
       last_name varchar(20) NOT NULL,
       user varchar(20) NOT NULL,
       password varchar(20) NOT NULL,
       PRIMARY KEY (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
`````

**Pom.xml**
````
   	 	<dependency>
    		<groupId>mysql</groupId>
   			 <artifactId>mysql-connector-java</artifactId>
    		<version>5.1.49</version>
		</dependency>
````

    Este escenario es un MVC completo, donde los controladores (Servlets) reciben acciones, ejecutan lógica de negocio y redirigen a las nuevas vistas o trasladan las acciones a la capa de persistencia, las vistas se mantienen separadas de la base de datos y obtienen la información de los controladores.

29. Creación de la infraestructura necesaria para insertar y modificar usuarios en dicha tabla, mediante unos formularios web. Para ello se necesitará:
    1. Un nuevo Servlet: UsuarioServle
    2. Dos nuevas páginas: altausuario.jsp y editarusuario.jsp
    3. Ambas páginas envían los datos al mismo servlet y un campo oculto con un mensaje que informa al servlet sobre qué acción debe hacer: insertar o actualizar.
