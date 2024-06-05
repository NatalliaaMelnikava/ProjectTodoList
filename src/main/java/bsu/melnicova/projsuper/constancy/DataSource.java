package bsu.melnicova.projsuper.constancy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private static final String URL = "jdbc:mysql://localhost:3306/todo";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	private static DataSource instance = new DataSource();

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	private DataSource() {
	}

	public static DataSource getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
