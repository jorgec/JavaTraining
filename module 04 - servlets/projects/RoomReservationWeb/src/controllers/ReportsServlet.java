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
import models.BuildingDataAccessObject;
import models.ReservationDataAccessObject;
import models.Room;
import models.RoomDataAccessObject;
import models.RoomTypeDataAccessObject;

/**
 * Servlet implementation class ReportsServlet
 */
@WebServlet("/admin/reports")
public class ReportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Account user;
	private AccountDataAccessObject users;
	private Room room;
	private RoomDataAccessObject rooms;
	private BuildingDataAccessObject buildings;
	private RoomTypeDataAccessObject roomTypes;
	private ReservationDataAccessObject reservations;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
    	users = new AccountDataAccessObject();
    	rooms = new RoomDataAccessObject();
    	buildings = new BuildingDataAccessObject();
    	roomTypes = new RoomTypeDataAccessObject();
    	reservations = new ReservationDataAccessObject();
    	user = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		// check if user is logged in
		// redirect to login form if isLoggedIn flag is not set
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		String page = "views/user/unauthorized.jsp";
		reservations = new ReservationDataAccessObject();
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			
			// get the userId so we can use the user object
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("admin") ) {
				// restrict access to this servlet to admin roles only
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				/**
				 * byRoom:
				 * 		?view=room&roomId=?
				 * byUser:
				 *  	?view=user&userId=?
				 * byDate:
				 * 		?view=date&start=?&end=?
				 */
				if( request.getParameter("view") != null ) {
					// handle specific reports
					switch( request.getParameter("view")) {
						case "byRoom": 
							// process reports by room
							// get room
							this.reportsByRoom(request);
							break;
						case "byUser":
							// get user
							break;
						case "byDate":
							// get start and end dates
							break;
					}
					
					
				}else {
					// main reports page
				}
				
				
			}
		}else {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	private void reportsByRoom(HttpServletRequest request) {
		// by room specific report
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
