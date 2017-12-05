package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	private int id;
	private int userId;
	private String accountType;
	
	public Account() {
		
	}
	
	public Account(int userId, String accountType) {
		this.setUserId(userId);
		this.setAccountType(accountType);
	}
	
	public Account(ResultSet result) {
		try {
			this.id = result.getInt("id");
			this.setUserId(result.getInt("userId"));
			this.setAccountType(result.getString("accountType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public Account(String[] result) {
		this.id = Integer.parseInt(result[0]);
		this.setUserId( Integer.parseInt( result[1]) );
		this.setAccountType(result[2]);
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
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	

}
