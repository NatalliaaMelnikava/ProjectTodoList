package bsu.melnicova.projsuper.constancy.implementation;



import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.model.UserTodo;
import bsu.melnicova.projsuper.constancy.DBUtil;
import bsu.melnicova.projsuper.constancy.DataSource;
import bsu.melnicova.projsuper.constancy.UserTodoAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserTodoAccessImplementation implements UserTodoAccess {

	@Override
	public void save(UserTodo userTodo) {
		String sql = "INSERT INTO users_todo (user_id, todo_id) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql, new String[] { "id" });
			statement.setInt(1, userTodo.getUser().getId());
			statement.setInt(2, userTodo.getTodoList().getId());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				userTodo.setId(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

	}

	@Override
	public List<TodoList> getTodoListForUser(int userId) {
		String sql = "SELECT * FROM todo JOIN users_todo ON todo.id = users_todo.todo_id WHERE users_todo.user_id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TodoList> todoList = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			resultSet = statement.executeQuery();
			todoList = new ArrayList<>();
			while (resultSet.next()) {
				TodoList todo = new TodoList();
				todo.setId(resultSet.getInt("id"));
				todo.setDescription(resultSet.getString("description"));
				todo.setCreateDate(resultSet.getDate("create_date"));
				todo.setDeadline(resultSet.getDate("deadline"));
				todoList.add(todo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return todoList;
	}

}
