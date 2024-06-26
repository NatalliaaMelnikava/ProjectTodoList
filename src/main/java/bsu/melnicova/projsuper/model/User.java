package bsu.melnicova.projsuper.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int id;
	private String name;
	private String surname;
	private String username;
	private String password;
	List<TodoList> todoList = new ArrayList<TodoList>(); 

	public User() {
		super();
	}

	public User(int id, String name, String surname, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public List<TodoList> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoList> todoList) {
		this.todoList = todoList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username + ", password="
				+ password + "]";
	}

}
