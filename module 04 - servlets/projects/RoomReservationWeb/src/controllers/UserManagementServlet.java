package controllers;

import java.io.IOException;
import java.sql.SQLException;

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
import models.ReservationDataAccessObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/users")
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Account user;
	private AccountDataAccessObject users;
	private ReservationDataAccessObject reservations;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		users = new AccountDataAccessObject();
    	reservations = new ReservationDataAccessObject();
    	user = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("account?action=login");
			return;
		}
		String page = "views/user/unauthorized.jsp";
		reservations = new ReservationDataAccessObject();
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("admin") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				try {
					request.setAttribute("accounts", users.getUsers());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				page = "/views/admin/user.jsp";	
			}
		}else {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		String page = "views/user/unauthorized.jsp";
		reservations = new ReservationDataAccessObject();
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("admin") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				try {
					String firstName = request.getParameter("firstName");
					String lastName = request.getParameter("lastName");
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String role = request.getParameter("role");
					
					Account u = new Account(username, password, firstName, lastName, role);
					users.create(u);
					
					request.setAttribute("accounts", users.getUsers());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				page = "/views/admin/user.jsp";	
			}
		}else {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

}
