package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.DB;

public class AccountDAO {
	private static final String dbTable = "accounts";
	private DB db;
	private Connection conn;
	
	public AccountDAO() {
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
	
	public Account get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Account account = new Account(result);
				return account;
			}			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public ResultSet getAccountsOfUser(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `userId` = ?";
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
	
	public boolean create(Account account) {			
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`userId`, `accountType`) VALUES(?, ?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, account.getUserId());
			stmt.setString(2, account.getAccountType());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
