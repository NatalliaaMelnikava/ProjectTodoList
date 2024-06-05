package bsu.melnicova.projsuper.constancy;

import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.model.User;

import java.util.List;


public interface UserAccess {
	List<TodoList> findByAll(String username);
	List<User> findUserByAll();
	User login(String username, String password);
	void newUserRegistration(User user);
	User findById(int id);
}
