package bsu.melnicova.projsuper.constancy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static void close(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void close(Statement statement) {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void close(ResultSet resultSet) {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
