package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/user_management";
	private static final String USERNAME = "test";
	private static final String PASSWORD = "test";

	private static Connection connection = null;

	public static Connection getConnection() {
		try {
			if (connection == null || connection.isClosed() || !connection.isValid(10)) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}