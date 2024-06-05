package bsu.melnicova.projsuper.constancy.implementation;

import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.constancy.DBUtil;
import bsu.melnicova.projsuper.constancy.DataSource;
import bsu.melnicova.projsuper.constancy.TodoListAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoListAccessImplementation implements TodoListAccess {

	@Override
	public void createNewTask(TodoList todolist) {
		String sql = "INSERT INTO todo (description, create_date, deadline) VALUES (?, ?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, todolist.getDescription());
			statement.setDate(2, new java.sql.Date(todolist.getCreateDate().getTime()));
			statement.setDate(3, new java.sql.Date(todolist.getDeadline().getTime()));
			statement.executeUpdate();
			generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				todolist.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(generatedKeys);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
	}
}
