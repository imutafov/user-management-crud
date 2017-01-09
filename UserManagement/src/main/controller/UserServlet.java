package main.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.UserDao;
import main.model.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -386644492023703102L;
	private static final String USER_ADD_EDIT = "/user.jsp";
	private static final String LIST_USERS = "/userlist.jsp";
	private UserDao dao;

	public UserServlet() {
		super();
		dao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			dao.deleteUser(userId);
			forward = LIST_USERS;
			request.setAttribute("users", dao.getAllUsers());
			ServletContext sc = request.getServletContext();
			sc.setAttribute("users", dao.getAllUsers());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = USER_ADD_EDIT;
			Long userId = Long.parseLong(request.getParameter("userId"));
			User user = dao.getUserById(userId);
			request.setAttribute("user", user);
		} else if (action.equalsIgnoreCase("listUser")) {
			forward = LIST_USERS;
			request.setAttribute("users", dao.getAllUsers());
			ServletContext sc = request.getServletContext();
			sc.setAttribute("users", dao.getAllUsers());
		} else {
			forward = USER_ADD_EDIT;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(request.getParameter("birthDate"));
		} catch (ParseException e) {
			System.out.println("Invalid date");
		}
		user.setBirthDate(birthDate);
		user.setPhoneNumber(request.getParameter("phoneNumber"));
		user.setEmail(request.getParameter("email"));
		String userid = request.getParameter("userid");
		if (userid == null || userid.isEmpty()) {
			dao.addUser(user);
		} else {
			user.setUserId(Long.parseLong(userid));
			dao.updateUser(user);
		}
		RequestDispatcher view = request.getRequestDispatcher(LIST_USERS);
		request.setAttribute("users", dao.getAllUsers());
		ServletContext sc = request.getServletContext();
		sc.setAttribute("users", dao.getAllUsers());
		view.forward(request, response);
	}
}
