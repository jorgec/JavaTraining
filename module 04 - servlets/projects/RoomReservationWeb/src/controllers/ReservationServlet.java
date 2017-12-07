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
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/admin/reservation")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Account user;
	private AccountDataAccessObject users;
	private ReservationDataAccessObject reservations;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservationServlet() {
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
		reservations = new ReservationDataAccessObject();
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("admin") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				String action = request.getParameter("action");
				int id = Integer.parseInt(request.getParameter("id"));
				
				if( action.equals("approve")) {
					reservations.approve(id);
				}else {
					reservations.deny(id);
				}
				
				response.sendRedirect("/RoomReservationWeb/admin");
				return;
				
			}
		}else {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
