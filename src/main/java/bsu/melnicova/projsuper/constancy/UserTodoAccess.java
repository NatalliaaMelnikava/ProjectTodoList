package bsu.melnicova.projsuper.constancy;

import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.model.UserTodo;

import java.util.List;



public interface UserTodoAccess {
	  void save(UserTodo userTodo);
	  List<TodoList> getTodoListForUser(int userId);
}
