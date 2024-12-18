package resources;


import homebaking.exceptions.ConnectionException;
import homebaking.exceptions.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	public void createUserTable() throws DAOException {

		Connection c = null;
		try {
			c = DBManager.connect();
		} catch (ConnectionException e) {
			throw new DAOException("Error de conexión a la base de datos", e);
		}
		
		String sql = "CREATE TABLE usuarios ( id INTEGER IDENTITY, username VARCHAR(256) UNIQUE, email VARCHAR(256), password VARCHAR(10))";

		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}
	
	public void dropUserTable() throws DAOException {

		Connection c = null;
		try {
			c = DBManager.connect();
		} catch (ConnectionException e) {
			throw new DAOException("Error de conexión a la base de datos", e);
		}
		
		String sql = "DROP TABLE usuarios";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}
