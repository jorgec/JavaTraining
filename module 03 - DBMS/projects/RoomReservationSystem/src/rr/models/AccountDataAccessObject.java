package rr.models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.table.DefaultTableModel;

import rr.helpers.DB;

public class AccountDataAccessObject {
	private static final String dbTable = "accounts";
	private DB db;
	private Connection conn;
	
	public AccountDataAccessObject() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	public Account get(String username) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `username` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Account account = new Account(result);
				return account;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	public ResultSet search(String term) {
		ResultSet result = null;
		
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder("SELECT * FROM " + dbTable + " WHERE ");
		query.append("`username` LIKE ?");
		query.append(" OR `firstName` LIKE ?");
		query.append(" OR `lastName` LIKE ?");
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, "%" + term + "%");
			stmt.setString(2, "%" + term + "%");
			stmt.setString(3, "%" + term + "%");
			result = stmt.executeQuery();		
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public boolean validateUniqueFields(Account account) {
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) AS `usercount` FROM " + dbTable + " WHERE ");
		query.append("`username` = ?");
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, account.getUsername());			
			ResultSet rs = stmt.executeQuery();			
			rs.next();			
			return true ? rs.getInt("usercount") == 0 : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean create(Account account) {
		
		if( ! validateUniqueFields(account) ) return false;
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`username`, `password`, `firstName`, `lastName`, `role`) VALUES(?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, account.getUsername());
			stmt.setString(2, account.getPassword());
			stmt.setString(3, account.getFirstName());
			stmt.setString(4, account.getLastName());
			stmt.setString(5, account.getRole());
			
			return stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, Account account) {
		PreparedStatement stmt = null;
		String query = "UPDATE " + dbTable + " SET `firstName` = ?, `lastName` = ?, `role` = ?, `password` = ? WHERE `id` = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, account.getFirstName());
			stmt.setString(2,  account.getLastName());
			stmt.setString(3, account.getRole());
			stmt.setString(4, account.getPassword());
			stmt.setInt(5, id);
			return stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, String field, String value) {
		PreparedStatement stmt = null;
		String query = "UPDATE " + dbTable + " SET ? = ? WHERE `id` = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, field);
			stmt.setString(2,  value);
			stmt.setInt(3, id);
			return stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, HashMap<String, String> serialData) {
		StringBuilder query = new StringBuilder();
		query.append("UPDATE " + dbTable + " SET ");
		for(Entry<String, String> keyValPair: serialData.entrySet()) {
			query.append(keyValPair.getKey() + "=" + keyValPair.getValue());
		}
		query.append(" WHERE `id` = '" + id + "'");
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(query.toString());
			return stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Account authenticate(String username, String password) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		Account account = new Account();
		String hashedPassword = new String();
		try {
			hashedPassword = account.generatePasswordHash(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hashedPassword = password;
		}
		String query = "SELECT * FROM " + dbTable + " WHERE `username` = ? AND `password` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, hashedPassword);
			result = stmt.executeQuery();
			if( result.next() ) {
				account = new Account(result);
				return account;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}
