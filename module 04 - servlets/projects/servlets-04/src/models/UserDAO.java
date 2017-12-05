package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.DB;

public class UserDAO {
	private static final String dbTable = "users";
	private DB db;
	private Connection conn;
	
	public UserDAO() {
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
	
	public User get(String username) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `username` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				User user = new User(result);
				return user;
			}			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public User get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				User user = new User(result);
				return user;
			}			
		} catch (SQLException e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	public ResultSet search(String term) {
		ResultSet result = null;
		
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder("SELECT * FROM " + dbTable + " WHERE ");
		query.append("`username` LIKE ?");
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, "%" + term + "%");
			result = stmt.executeQuery();		
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public boolean validateUniqueFields(User user) {
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) AS `usercount` FROM " + dbTable + " WHERE ");
		query.append("`username` = ?");
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, user.getUsername());			
			ResultSet rs = stmt.executeQuery();			
			rs.next();			
			return true ? rs.getInt("usercount") == 0 : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean create(User user) {
		
		if( ! validateUniqueFields(user) ) return false;
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`username`, `password`) VALUES(?, ?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public User authenticate(String username, String password) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		User user = new User();
		String hashedPassword = new String();
		try {
			hashedPassword = user.generatePasswordHash(password);
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
				user = new User(result);
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
