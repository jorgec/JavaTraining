package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import helpers.DB;

public class RoomDataAccessObject {
	private static final String dbTable = "rooms";
	private DB db;
	private Connection conn;
	
	public RoomDataAccessObject() {
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
	
	public ArrayList<Room> getRooms() throws SQLException{
		ArrayList<Room> rooms = new ArrayList<Room>();
		ResultSet rDS = this.get();
		
		while(rDS.next()) {
			Room r = new Room();
			r.setId(rDS.getInt("id"));
			r.setName(rDS.getString("name"));
			r.setBuildingId(rDS.getInt("buildingId"));
			r.setRoomTypeId(rDS.getInt("roomTypeId"));
			r.setAirConditioned(rDS.getBoolean("isAirConditioned"));
			r.setSeatingCapacity(rDS.getInt("seatingCapacity"));
			rooms.add(r);
		}
		return rooms;
	}
	
	public Room get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Room room = new Room(result);
				return room;
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
	
	public ResultSet filter(int buildingId, int roomTypeId, int seatingCapacity, boolean isAirConditioned) {
		ResultSet result = null;
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder("SELECT * FROM " + dbTable + " WHERE ");
		query.append("`buildingId` = ? ");
		query.append("AND `roomTypeId` = ? ");
		query.append("AND `seatingCapacity` <= ? ");
		query.append("AND `isAirConditioned` = ?");
		
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, buildingId);
			stmt.setInt(2, roomTypeId);
			stmt.setInt(3, seatingCapacity);
			stmt.setBoolean(4, isAirConditioned);
			result = stmt.executeQuery();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public boolean create(Room room) {
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`name`, `roomTypeId`, `buildingId`, `seatingCapacity`, `isAirConditioned`) VALUES(?, ?, ?, ?, ?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, room.getName());
			stmt.setInt(2, room.getRoomTypeId());
			stmt.setInt(3, room.getBuildingId());
			stmt.setInt(4, room.getSeatingCapacity());
			stmt.setBoolean(5, room.isAirConditioned());
			return stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, Room room) {
		PreparedStatement stmt = null;
		String query = "UPDATE " + dbTable + " SET `name` = ?, `roomTypeId` = ?, `buildingId` = ?, `seatingCapacity` = ?, `isAirConditioned` = ?  WHERE `id` = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, room.getName());
			stmt.setInt(2, room.getRoomTypeId());
			stmt.setInt(3, room.getBuildingId());
			stmt.setInt(4, room.getSeatingCapacity());
			stmt.setBoolean(5, room.isAirConditioned());
			stmt.setInt(6, id);
			return stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
