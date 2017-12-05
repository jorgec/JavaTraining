package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;

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
import models.AccountDAO;
import models.Transaction;
import models.TransactionDAO;
import models.User;
import models.UserDAO;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO users;
	private User user;
	private HttpSession session;
	private Account account;
	private AccountDAO accounts;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		users = new UserDAO();
		accounts = new AccountDAO();
		user = null;
		account = null;
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("user?action=login");
			return;
		}
		
		user = users.get( (int) session.getAttribute("userId"));

		ResultSet userAccounts = accounts.getAccountsOfUser( (int) session.getAttribute("userId") );
		request.setAttribute("userAccounts", userAccounts);
				
		String action = request.getParameter("action");
		String page = "account/transactions.jsp";
		if( action != null ) {
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			TransactionDAO transactions = new TransactionDAO();
			switch(action) {
				case "view_account":
					page = "account/view.jsp";
					BigDecimal balance = transactions.getBalance(accountId);
					request.setAttribute("balance", balance);
					
					ResultSet transactionsRS = transactions.getTransactionsOfAccount(accountId);
					request.setAttribute("transactions", transactionsRS);
					request.setAttribute("accountId", accountId);
					break;
				case "deposit":
					
					
					page = "account/deposit.jsp";
					break;
				case "withdraw":
					int withdrawAccountId = Integer.parseInt( request.getParameter("accountId") );
					page = "account/withdraw.jsp?accountId=" + withdrawAccountId;
					break;
				default:
					page = "account/transactions.jsp";
					
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
		if( session.getAttribute("isLoggedIn") == null ) {
			response.sendRedirect("user?action=login");
			return;
		}
		
		user = users.get( (int) session.getAttribute("userId"));
		String action = request.getParameter("action");
		String page = "account/transactions.jsp";
		if( action != null ) {
			int accountId = 0; 
			TransactionDAO transactions = new TransactionDAO();
			BigDecimal amount = new BigDecimal(0.0);
			
			switch(action) {
				case "create_account":
					String accountType = request.getParameter("accountType");					
					account = new Account(user.getId(), accountType);
					if( accounts.create( account ) ) {
						page = "account/account_create_success.jsp";
					}else {
						page = "account/account_create_fail.jsp";
					}
					break;
				case "deposit":
					accountId = Integer.parseInt(request.getParameter("accountId"));
					amount = new BigDecimal( request.getParameter("amount") );
					if( amount.compareTo( new BigDecimal(0) ) <= 0 ) {
						request.setAttribute("errorMsg", "Amount is too small!");
						page = "account/error.jsp";
						break;
					}else {
						Transaction deposit = new Transaction(accountId, "Deposit", amount);
						transactions.create(deposit);
						response.sendRedirect("transactions?action=view_account&accountId=" + accountId);
						return;
					}
				case "withdraw":
					accountId = Integer.parseInt(request.getParameter("accountId"));
					amount = new BigDecimal( request.getParameter("amount") );
					BigDecimal balance = transactions.getBalance(accountId);
					request.setAttribute("balance", balance);
					
					if( balance == null ) {
						request.setAttribute("errorMsg", "You can't withdraw more than what you have!");
						page = "account/error.jsp";
						break;
					}else {
						if( balance.compareTo( amount ) < 0 ) {
							request.setAttribute("errorMsg", "You can't withdraw more than what you have!");
							page = "account/error.jsp";
							break;
						}else if( amount.compareTo( new BigDecimal(0) ) <= 0 ) {
							request.setAttribute("errorMsg", "Amount is too small!");
							page = "account/error.jsp";
							break;
						}else {
							Transaction withdraw = new Transaction(accountId, "Withdrawal", amount);
							transactions.create(withdraw);
							response.sendRedirect("transactions?action=view_account&accountId=" + accountId);
							return;
						}
					}
				default:
					page = "account/transactions.jsp";
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

}
