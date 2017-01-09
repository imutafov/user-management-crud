package main.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.dao.UserDao;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 2960405476793295861L;
	private static final String LIST_USERS = "/userlist.jsp";
	private UserDao dao;

	public SearchServlet() {
		super();
		dao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("firstname");
		request.setAttribute("users", dao.getUsersByName(name));
		ServletContext sc = request.getServletContext();
		sc.setAttribute("users", dao.getUsersByName(name));
		RequestDispatcher view = request.getRequestDispatcher(LIST_USERS);
		view.forward(request, response);
	}

}
