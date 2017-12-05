package models;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.DB;

public class TransactionDAO {
	private static final String dbTable = "transactions";
	private DB db;
	private Connection conn;
	
	public TransactionDAO() {
		db = new DB();
		conn = db.getConnection();
	}
	
	public ResultSet get() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable;
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public Transaction get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Transaction transaction = new Transaction(result);
				return transaction;
			}			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public ResultSet getTransactionsOfAccount(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `accountId` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			return result;
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public boolean create(Transaction transaction) {			
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`accountId`, `transactionType`, `amount`, `transactionDate`) VALUES(?, ?, ?, NOW())";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, transaction.getAccountId());
			stmt.setString(2, transaction.getTransactionType());
			stmt.setBigDecimal(3, transaction.getAmount());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public BigDecimal getBalance(int accountId) {
		BigDecimal balance = new BigDecimal(0.0);
		
		String query = "select ( select sum(amount) from transactions where accountId = ? and transactionType='Deposit' ) - (select ifnull(sum(amount),0) from transactions where accountId = ? and transactionType='Withdrawal') as balance";		
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, accountId);
			stmt.setInt(2, accountId);
			System.out.println(stmt.toString());
			ResultSet res = stmt.executeQuery();
			
			if( res.next() ) {
				balance = res.getBigDecimal("balance");
				return balance;
			}
			
			return balance;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
}
