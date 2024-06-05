package bsu.melnicova.projsuper.constancy.implementation;

import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.model.User;
import bsu.melnicova.projsuper.constancy.DBUtil;
import bsu.melnicova.projsuper.constancy.DataSource;
import bsu.melnicova.projsuper.constancy.UserAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class UserAccessImplementation implements UserAccess {

	@Override
	public List<TodoList> findByAll(String username) {
		String sql = "select * from users_todo as us inner join users as u on us.user_id = u.id inner join todo as t on us.todo_id = t.id where username = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<TodoList> todoListArray = new ArrayList<TodoList>();
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TodoList todoList = new TodoList();
				todoList.setId(resultSet.getInt(9));
				todoList.setDescription(resultSet.getString(10));
				todoList.setCreateDate(new Date(resultSet.getDate(11).getTime()));
				todoList.setDeadline(new Date(resultSet.getDate(12).getTime()));
				todoListArray.add(todoList);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}

		return todoListArray;
	}

	@Override
	public List<User> findUserByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User login(String username, String password) {
		String sql = "select * from users where username = ? and password = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt(1));
				user.setUsername(resultSet.getString(4));
				user.setPassword(resultSet.getString(5));

			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return user;
	}

	@Override
	public void newUserRegistration(User user) {
		String sql = "insert into users(name, surname, username, password) values(?,?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getSurname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
	}

	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = DataSource.getInstance().getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setSurname(resultSet.getString("surname"));
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(statement);
			DBUtil.close(connection);
		}
		return user;
	}

}
