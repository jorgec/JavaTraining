package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.User;
import models.UserDAO;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet({"/user", "/servlets-04/"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO users;
	private User user;
	private HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		users = new UserDAO();
		user = null;
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		String action = request.getParameter("action");
		String page = "index.jsp";
		
		if( action != null ) {
			switch(action) {
				case "login":
					page = "user/login.jsp";
					break;
				case "logout":
					session.removeAttribute("isLoggedIn");
					page = "user/logout.jsp";
					break;
				case "register":
					page = "user/register.jsp";
					break;
				case "list":
					page = "user/list.jsp";
					request.setAttribute("user_list", users.get());
					break;
				default:
					page = "index.jsp";
					break;
			}
		}
		request.setAttribute("isLoggedIn", "false");
		if( session.getAttribute("isLoggedIn") != null ) {
			request.setAttribute("isLoggedIn", "true");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		String action = request.getParameter("action");
		String page = "index.jsp";
		
		if( action != null ) {
			if( action.equals("register") ) {
				page = "user/register.jsp";
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String password_repeat = request.getParameter("password-repeat");
				
				if( password.equals(password_repeat)) {
					user = new User(username, password);
					if( users.create(user) ) {
						page = "user/register_success.jsp";
					}else {
						request.setAttribute("errorMsg", "Username already exists!");
					}
				}else {
					request.setAttribute("errorMsg", "Passwords do not match");
				}
			}
			
			if( action.equals("login")) {
				page = "user/login.jsp";
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				user = users.authenticate(username, password);
				
				if( user != null ) {
					response.sendRedirect("transactions");
					session.setAttribute("userId", user.getId());
					session.setAttribute("isLoggedIn", true);
					return;
				}else {
					request.setAttribute("errorMsg", "Invalid username or password");
				}
			}
		}
		request.setAttribute("isLoggedIn", "false");
		if( session.getAttribute("isLoggedIn") != null ) {
			request.setAttribute("isLoggedIn", "true");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

}
