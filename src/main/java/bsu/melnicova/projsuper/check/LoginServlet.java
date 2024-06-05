package bsu.melnicova.projsuper.check;


import bsu.melnicova.projsuper.model.TodoList;
import bsu.melnicova.projsuper.model.User;
import bsu.melnicova.projsuper.constancy.UserAccess;
import bsu.melnicova.projsuper.constancy.implementation.UserAccessImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/index")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccess userAccess = new UserAccessImplementation();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userAccess.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("name", user.getName());
			List<TodoList> todoLists = userAccess.findByAll(username);
			request.setAttribute("todoList", todoLists);	
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}

	}

}
