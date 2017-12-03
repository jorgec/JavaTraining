package controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Price;

/**
 * Servlet implementation class Order
 */
@WebServlet("/order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Order() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("orderForm.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String gender = request.getParameter("gender");
		int ageGroup = Integer.parseInt( request.getParameter("ageGroup") );
		String[] addons = request.getParameterValues("addons");
		Double discount = 1.0;
		Double price = 200.0;
		Double finalPrice = 0.0;
		
		if( gender.equals("female")) {
			discount = discount - 0.2;
		}else {
			discount = discount + .1;
		}
		switch(ageGroup) {
			case 0:
				discount = discount - 0.2;
				break;
			case 1:
				discount = discount - 0.1;
				break;
			case 2:
				discount = discount - 0.05;
				break;
			case 6:
			case 7:
				discount = discount - 0.2;
				break;
		}
		HashMap<String, Double> prices = Price.getPriceList();
		for(String addon: addons) {
			price = price + prices.get(addon);
		}
		finalPrice = price * discount;
		request.setAttribute("firstName", firstName);
		request.setAttribute("lastName", lastName);
		request.setAttribute("price", price);
		request.setAttribute("finalPrice", finalPrice);
		
		RequestDispatcher view = request.getRequestDispatcher("orderDetails.jsp");
		view.forward(request, response);
		
		
	}

}
