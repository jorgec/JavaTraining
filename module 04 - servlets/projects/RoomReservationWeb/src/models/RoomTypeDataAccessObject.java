package models;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import helpers.DB;

public class RoomTypeDataAccessObject {
	private static final String dbTable = "room_types";
	private DB db;
	private Connection conn;
	
	public RoomTypeDataAccessObject() {
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
	
	public ArrayList<RoomType> getRoomTypes() throws SQLException{
		ArrayList<RoomType> roomTypes = new ArrayList<RoomType>();
		ResultSet bRS = this.get();
		
		while(bRS.next()) {
			RoomType b = new RoomType();
			b.setId(bRS.getInt("id"));
			b.setName(bRS.getString("name"));
			roomTypes.add(b);
		}
		
		return roomTypes;
	}
	
	public RoomType get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				RoomType roomType = new RoomType(result);
				return roomType;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	public RoomType get(String name) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `name` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				RoomType roomType = new RoomType(result);
				return roomType;
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
		query.append("`name` LIKE ?");
		
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, "%" + term + "%");
			result = stmt.executeQuery();		
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		return result;
	}
	
	public boolean create(RoomType roomType) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`name`) VALUES(?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, roomType.getName());
			
			return stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, RoomType roomType) {
		PreparedStatement stmt = null;
		String query = "UPDATE " + dbTable + " SET `name` = ?  WHERE `id` = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, roomType.getName());
			stmt.setInt(5, id);
			return stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
