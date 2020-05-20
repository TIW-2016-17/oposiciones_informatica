package es.fp.dwes.jdbc.daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;

import es.fp.dwes.domains.User;


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


