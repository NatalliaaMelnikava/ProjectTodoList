package bsu.melnicova.projsuper.check;

import bsu.melnicova.projsuper.model.User;
import bsu.melnicova.projsuper.constancy.UserAccess;
import bsu.melnicova.projsuper.constancy.implementation.UserAccessImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserAccess userAccess = new UserAccessImplementation();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (name != null  && surname != null &&   username != null
				 && password != null ) {
			user.setName(name);
			user.setSurname(surname);
			user.setUsername(username);
			user.setPassword(password);
			userAccess.newUserRegistration(user);
			response.sendRedirect("login.jsp");
			return;
		} else
			response.sendRedirect("registration.jsp");
	}

}
