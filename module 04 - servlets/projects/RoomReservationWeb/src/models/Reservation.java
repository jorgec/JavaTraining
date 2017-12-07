package models;

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
	private String sStartDate;
	private String sEndDate;
	private Date startTime;
	private Date endTime;
	
	public Reservation() {
		
	}
	
	public Reservation(int roomId, int accountId, Date startDate, Date endDate) {
		this.setRoomId(roomId);
		this.setAccountId(accountId);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
	}
	
	public Reservation(int roomId, int accountId, String startDate, String endDate) {
		this.setRoomId(roomId);
		this.setAccountId(accountId);
		this.setsStartDate(startDate);
		this.setsEndDate(endDate);
	}
	
	public Reservation(ResultSet result) {
		try {
			this.setId(result.getInt("id"));
			this.setRoomId(result.getInt("roomId"));
			this.setAccountId(result.getInt("accountId"));
			this.setStartDate(result.getDate("startDate"));
			this.setEndDate(result.getDate("endDate"));
			this.setStartTime(result.getTime("startDate"));
			this.setEndTime(result.getTime("endDate"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the sStartDate
	 */
	public String getsStartDate() {
		return sStartDate;
	}

	/**
	 * @param sStartDate the sStartDate to set
	 */
	public void setsStartDate(String sStartDate) {
		this.sStartDate = sStartDate;
	}

	/**
	 * @return the sEndDate
	 */
	public String getsEndDate() {
		return sEndDate;
	}

	/**
	 * @param sEndDate the sEndDate to set
	 */
	public void setsEndDate(String sEndDate) {
		this.sEndDate = sEndDate;
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
	
	public String getRoom() {
		int id = this.getRoomId();
		RoomDataAccessObject rooms = new RoomDataAccessObject();
		Room room = rooms.get(id);
		return room.getName();
	}
	
	public String getUser() {
		int id = this.getAccountId();
		AccountDataAccessObject users = new AccountDataAccessObject();
		Account user = users.get(id);
		String n = user.getLastName() + ", " + user.getFirstName();
		return n;
	}

}
