package rr.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Reservation {
	private int id;
	private int roomId;
	private int accountId;
	private Date startDate;
	private Date endDate;
	private String status; 
	
	public Reservation() {
		
	}
	
	public Reservation(int roomId, int accountId, Date startDate, Date endDate) {
		this.setRoomId(roomId);
		this.setAccountId(accountId);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	
	public Reservation(ResultSet result) {
		try {
			this.setId(result.getInt("id"));
			this.setRoomId(result.getInt("roomId"));
			this.setAccountId(result.getInt("accountId"));
			this.setStartDate(result.getDate("startDate"));
			this.setEndDate(result.getDate("endDate"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
	 * @return the roomId
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	/**
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}	

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
