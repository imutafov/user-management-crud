package main.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.model.User;

@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {

	private static final long serialVersionUID = -8077880255238006357L;
	private static final String LIST_USERS = "/userlist.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String criteria = request.getParameter("criteria");
		String order = request.getParameter("order");
		ServletContext sc = request.getServletContext();
		List<User> users = (List<User>) sc.getAttribute("users");
		if ("lastname".equals(criteria)) {
			Collections.sort(users, (u1, u2) -> u1.getLastName().compareTo(u2.getLastName()));
		} else if ("birthdate".equals(criteria)) {
			Collections.sort(users, (u1, u2) -> u1.getBirthDate().compareTo(u2.getBirthDate()));
		}
		if ("desc".equals(order)) {
			Collections.reverse(users);
		}
		request.setAttribute("users", users);
		RequestDispatcher view = request.getRequestDispatcher(LIST_USERS);
		view.forward(request, response);
	}
}
