package resources;

import homebaking.exceptions.ConnectionException;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final String DB_BASE_URL = "jdbc:h2:tcp://localhost//{DIR}";
	private static final String DB_NAME = "/proyecto";
	private static final String DB_USERNAME = "sa";
	private static final String DB_PASSWORD = "";

	public static Connection connect() throws ConnectionException {
		Connection c = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			String url = "jdbc:h2:tcp://localhost//{DIR}/ejemplo";
			url = url.replace("{DIR}", getDirectorioBase());
			//System.out.println(url);
			c = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
			c.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ConnectionException("Error de conexión a la base de datos", e);
        }

		return c;
	}

	private static String getDirectorioBase() {
		File currDir = new File("h2/base_de_datos/");
		return currDir.getAbsolutePath();
	}

	public static String obtenerUbicacionBase() {
		String url = "jdbc:h2:tcp://localhost//{DIR}/ejemplo";
		url = url.replace("{DIR}", getDirectorioBase());
		return url;
	}

}
