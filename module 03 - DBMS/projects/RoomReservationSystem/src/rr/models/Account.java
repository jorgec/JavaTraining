package rr.models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import rr.helpers.SHA1;

public class Account {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String role;
	
	public Account() {
		
	}
	
	public Account(String username, String password, String firstName, String lastName, String role) {
		this.username = username;
		this.setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;		
	}
	
	public Account(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.username = result.getString("username");
			this.password = result.getString("password");
			this.firstName = result.getString("firstName");
			this.lastName = result.getString("lastName");
			this.role = result.getString("role");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Account(String[] result) {
		this.id = Integer.parseInt(result[0]);
		this.username = result[1];
		this.password = result[2];
		this.firstName = result[3];
		this.lastName = result[4];
		this.role = result[5];
	}
	
	public HashMap<String, String> serialize() {
		HashMap<String, String> s = new HashMap<String, String>();
		s.put("username", this.getUsername());
		s.put("password", this.getPassword());
		s.put("firstName", this.getFirstName());
		s.put("lastName",  this.getLastName());
		s.put("role", this.getRole());
		return s;
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
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
