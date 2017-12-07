package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
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
import models.Reservation;
import models.ReservationDataAccessObject;
import models.Room;
import models.RoomDataAccessObject;
import models.RoomTypeDataAccessObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
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
    public UserServlet() {
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
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		String page = "views/user/unauthorized.jsp";
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("user") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				try {
					request.setAttribute("rooms", rooms.getRooms());
					request.setAttribute("buildings", buildings.getBuildings());
					request.setAttribute("roomTypes", roomTypes.getRoomTypes());
					request.setAttribute("reservations", reservations.getReservations(user));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				page = "/views/users/home.jsp";	
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
			response.sendRedirect("account?action=login");
			return;
		}
		String page = "views/user/unauthorized.jsp";
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("user") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				
				int roomId = Integer.parseInt(request.getParameter("room"));
				String sStartDate = request.getParameter("start");
				String sEndDate = request.getParameter("end");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
				
				LocalDateTime localStart = LocalDateTime.parse(sStartDate, formatter);
				LocalDateTime localEnd = LocalDateTime.parse(sEndDate, formatter);
				
				Date startDate = Date.from(localStart.atZone(ZoneId.systemDefault()).toInstant());
				Date endDate = Date.from(localEnd.atZone(ZoneId.systemDefault()).toInstant());
				
				Reservation r = new Reservation(roomId, user.getId(), startDate, endDate);
				if(reservations.create(r)) {
					response.sendRedirect("/RoomReservationWeb/user");
					return;
				}else {
					try {
						request.setAttribute("rooms", rooms.getRooms());
						request.setAttribute("buildings", buildings.getBuildings());
						request.setAttribute("roomTypes", roomTypes.getRoomTypes());
						request.setAttribute("reservations", reservations.getReservations(user));
						page = "/views/users/home.jsp";	
						request.setAttribute("errorMsg", "There is a conflict with that schedule for that room, please try again.");
						RequestDispatcher view = request.getRequestDispatcher(page);
						view.forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
//				page = "/views/users/home.jsp";	
			}
		}else {
			response.sendRedirect("account?action=login");
			return;
		}
		
	}

}
