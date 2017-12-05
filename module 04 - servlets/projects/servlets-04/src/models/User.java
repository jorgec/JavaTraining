package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.SHA1;

public class User {
	private int id;
	private String username;
	private String password;
	
	public User() {
		
	}
	
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public User(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.username = result.getString("username");
			this.password = result.getString("password");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public User(String[] result) {
		this.id = Integer.parseInt(result[0]);
		this.username = result[1];
		this.password = result[2];
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		try {
			this.password = generatePasswordHash(password);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.password = password;
		}
	}
	
	public String generatePasswordHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String hashedPassword = SHA1.makeSHA1(password);
		return hashedPassword;
	}
	

}
