package java0709;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {

	public static Connection getConnection() {
		Connection connect = null;
		try {
			// load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB

			// connect = DriverManager.getConnection("jdbc:mysql://192.168.31.74/test2",
			// "jennifer", "123");
			connect = DriverManager.getConnection("jdbc:mysql://192.168.50.67/test2", "jennifer", "123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}

}
