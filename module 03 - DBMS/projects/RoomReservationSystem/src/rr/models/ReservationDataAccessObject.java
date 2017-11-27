package rr.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import rr.helpers.DB;

public class ReservationDataAccessObject {
	private static final String dbTable = "reservations";
	private DB db;
	private Connection conn;
	
	public ReservationDataAccessObject() {
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

	
	public Reservation get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Reservation r = new Reservation(result);
				return r;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	public Reservation get(Room room) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `roomId` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, room.getId());
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Reservation r = new Reservation(result);
				return r;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	public ResultSet get(Account account) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `accountId` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, account.getId());
			result = stmt.executeQuery();
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	
	public boolean validateUniqueFields(Reservation r) {
		
		
		return true;
	}
	
	public boolean validate(Reservation r) {
		/**
		 * a = r.startDate
		 * b = r.endDate
		 * x = row.startDate
		 * y = row.endDate
		 * 
		 * CONDITIONS FOR FALSE:
		 * --- a >= x, a <= y
		 * --- b >= x, b <= y
		 * --- a <= x, b >= y
		 * --- a >= x, b <= y
		 * 
		 */
		
		String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getStartDate());		
		String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getEndDate());
		
		ResultSet result = null;
		
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder("SELECT COUNT(`id`) AS `hit` FROM " + dbTable + " WHERE ");
		query.append(" ((? >= `startDate`) AND (? <= `endDate`)) OR ");
		query.append(" ((? >= `startDate`) AND (? <= `endDate`)) OR ");
		query.append(" ((? <= `startDate`) AND (? >= `endDate`)) OR ");
		query.append(" ((? >= `startDate`) AND (? <= `endDate`)) ");
		
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setObject(1, startDate);
			stmt.setObject(2, startDate);
			stmt.setObject(3, endDate);
			stmt.setObject(4, endDate);
			stmt.setObject(5, startDate);
			stmt.setObject(6, endDate);
			stmt.setObject(7, startDate);
			stmt.setObject(8, endDate);
			result = stmt.executeQuery();
			
			if( result.next() ) {
				int hitCount = result.getInt("hit");
				if( hitCount == 0 ) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean create(Reservation r) {
		
		if( ! validate(r) ) return false;
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`roomId`, `accountId`, `startDate`, `endDate`) VALUES(?, ?, ?, ?)";
		
		String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getStartDate());		
		String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getEndDate());
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, r.getRoomId());
			stmt.setInt(2, r.getAccountId());
			stmt.setObject(3, startDate);
			stmt.setObject(4, endDate);
			stmt.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean update(int id, Reservation r) {
		PreparedStatement stmt = null;
		String query = "UPDATE " + dbTable + " SET `roomId` = ?, `accountId` = ?, `startDate` = ?, `endDate` = ?  WHERE `id` = ?";
		
		String startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getStartDate());		
		String endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getEndDate());
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, r.getRoomId());
			stmt.setInt(2, r.getAccountId());
			stmt.setObject(3, startDate);
			stmt.setObject(4, endDate);
			stmt.setInt(5, id);
			return stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void approve(int id) {
		String query = "UPDATE " + dbTable + " SET `status` = 'Approved' WHERE `id` = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(query);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deny(int id) {
		String query = "UPDATE " + dbTable + " SET `status` = 'Pending' WHERE `id` = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(query);
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
