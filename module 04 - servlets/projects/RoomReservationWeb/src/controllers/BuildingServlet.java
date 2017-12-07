package controllers;

import java.io.IOException;
import java.sql.SQLException;

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
import models.Building;
import models.BuildingDataAccessObject;
import models.Room;
import models.RoomDataAccessObject;
import models.RoomTypeDataAccessObject;

/**
 * Servlet implementation class BuildingServlet
 */
@WebServlet("/admin/buildings")
public class BuildingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Account user;
	private AccountDataAccessObject users;
	private Room room;
	private RoomDataAccessObject rooms;
	private BuildingDataAccessObject buildings;
	private RoomTypeDataAccessObject roomTypes;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildingServlet() {
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
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("admin") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				try {
					request.setAttribute("buildings", buildings.getBuildings());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				page = "/views/admin/building.jsp";	
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
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("account?action=login");
			return;
		}
		String page = "views/user/unauthorized.jsp";
		if( session.getAttribute("userId") != null ) {
			request.setAttribute("userId", session.getAttribute("userId"));
			request.setAttribute("isLoggedIn", "true");
			user = users.get( (int) session.getAttribute("userId"));
			if( ! user.getRole().equals("admin") ) {
				response.sendRedirect("/RoomReservationWeb/account?action=login");
				return;
			}else {
				
				try {
					
					String name = request.getParameter("name");
					Building b = new Building(name);
					buildings.create(b);
					
					request.setAttribute("buildings", buildings.getBuildings());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				page = "/views/admin/building.jsp";	
			}
		}else {
			response.sendRedirect("/RoomReservationWeb/account?action=login");
			return;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

}
