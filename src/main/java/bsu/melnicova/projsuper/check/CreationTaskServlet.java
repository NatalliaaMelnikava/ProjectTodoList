package bsu.melnicova.projsuper.check;


import bsu.melnicova.projsuper.constancy.TodoListAccess;
import bsu.melnicova.projsuper.constancy.UserAccess;
import bsu.melnicova.projsuper.constancy.UserTodoAccess;
import bsu.melnicova.projsuper.constancy.implementation.TodoListAccessImplementation;
import bsu.melnicova.projsuper.constancy.implementation.UserAccessImplementation;
import bsu.melnicova.projsuper.constancy.implementation.UserTodoAccessImplementation;
import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.model.User;
import bsu.melnicova.projsuper.model.UserTodo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@WebServlet("/creation-task")
public class CreationTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoListAccess todoListAccess = new TodoListAccessImplementation();
	private UserTodoAccess userTodoAccess = new UserTodoAccessImplementation();
	private UserAccess userAccess = new UserAccessImplementation();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			// The user is already authenticated
			User user = (User) session.getAttribute("user");

			String description = request.getParameter("task");
			String dataCreationAsString = request.getParameter("dataCreation");
			Date dataCreation = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dataCreation = sdf.parse(dataCreationAsString);
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}

			String dateAssignmentAsString = request.getParameter("deadLine");
			Date dateAssignment = null;
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dateAssignment = sdf2.parse(dateAssignmentAsString);
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}

			// Create a new TodoList object
			TodoList todoList = new TodoList();
			todoList.setDescription(description);
			todoList.setCreateDate(dataCreation);
			todoList.setDeadline(dateAssignment);
			// Save the new TodoList object in the database
			todoListAccess.createNewTask(todoList);

			// Get the ID of the current user from the session
			// Creates a UserTodo object that associates the new TodoList with the current user
			UserTodo userTodo = new UserTodo();
			userTodo.setUser(user);
			userTodo.setTodoList(todoList);
			// Save the UserTodo object in the database
			userTodoAccess.save(userTodo);
			
			List<TodoList> todoLists = userAccess.findByAll(user.getUsername());
			request.setAttribute("todoList", todoLists);	
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			// User is not authenticated, redirect to login page
			response.sendRedirect(request.getContextPath() + "/login");

		}
	}
}
