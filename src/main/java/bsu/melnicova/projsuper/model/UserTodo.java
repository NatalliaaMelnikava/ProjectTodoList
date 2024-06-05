package bsu.melnicova.projsuper.model;

public class UserTodo {
	private int id;
	private User user;
	private TodoList todoList;

	public UserTodo() {
		super();
	}
	
	public UserTodo(int id, User user, TodoList todoList) {
		super();
		this.id = id;
		this.user = user;
		this.todoList = todoList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TodoList getTodoList() {
		return todoList;
	}

	public void setTodoList(TodoList todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "UserTodo [id=" + id + ", user=" + user + ", todoList=" + todoList + "]";
	}

}
