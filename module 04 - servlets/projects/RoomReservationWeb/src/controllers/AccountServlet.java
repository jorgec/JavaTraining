package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account;
import models.AccountDataAccessObject;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Account user;
	private AccountDataAccessObject users;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	users = new AccountDataAccessObject();
    	user = null;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String page = "index.jsp";
		if( request.getParameter("action") != null ) {
			String action = request.getParameter("action");
			switch( action ) {
				case "login":
					page = "views/user/login.jsp";
					break;
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String page = "index.jsp";
		if( request.getParameter("action") != null ) {
			String action = request.getParameter("action");
			switch( action ) {
				case "login":
					user = login(request);
					if( user != null ) {
						// save the userId in the session so we can refer to this later
						session.setAttribute("userId", user.getId());
						// set the isLoggedIn flag to true
						// this will be checked by the other servlets to determine if the user has indeed logged in
						session.setAttribute("isLoggedIn", true);
						
						if( user.getRole().equals("admin")) {
							// if user role is admin, redirect to admin page
							response.sendRedirect("admin");
						}else {
							response.sendRedirect("user");
						}
						return;
					}else {
						page = "views/user/login.jsp";
						request.setAttribute("errorMsg", "Invalid username or password");
					}
					break;
				case "logout":
					session.setAttribute("isLoggedIn", null);
					page = "views/user/login.jsp";
					break;
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}
	
	private Account login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		return users.authenticate(username, password);
	}

}
