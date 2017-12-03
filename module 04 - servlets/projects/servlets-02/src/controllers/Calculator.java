package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Calculator
 */
@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Calculator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Double n1 = Double.parseDouble(request.getParameter("n1"));
		Double n2 = Double.parseDouble(request.getParameter("n2"));
		String opr = request.getParameter("opr");
		Double result = 0.0;
		switch(opr) {
			case "add": result = n1 + n2; break;
			case "subtract": result = n1 - n2; break;
			case "multiply": result = n1 * n2; break;
			case "divide": result = n1 / n2; break;
		}
		request.setAttribute("result", result);
		RequestDispatcher view = request.getRequestDispatcher("calculatorResult.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
