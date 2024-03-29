package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DerbyDatabase {
	public static void init() throws ClassNotFoundException {
		// Load Derby JDBC driver
		// Fully qualified driver name
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// Trigger class loader
		Class.forName(driver);
	}

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:derby:myDB;create=true";
		String username = "APP";
		String password = "APP";
		return DriverManager.getConnection(url, username, password);
	}
}
