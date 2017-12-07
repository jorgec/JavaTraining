package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import helpers.DB;

public class BuildingDataAccessObject {
	private static final String dbTable = "buildings";
	private DB db;
	private Connection conn;
	
	public BuildingDataAccessObject() {
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
	
	public ArrayList<Building> getBuildings() throws SQLException{
		ArrayList<Building> buildings = new ArrayList<Building>();
		ResultSet bRS = this.get();
		
		while(bRS.next()) {
			Building b = new Building();
			b.setId(bRS.getInt("id"));
			b.setName(bRS.getString("name"));
			buildings.add(b);
		}
		
		return buildings;
	}
	
	public Building get(int id) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `id` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Building building = new Building(result);
				return building;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return null;
	}
	
	public Building get(String name) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		String query = "SELECT * FROM " + dbTable + " WHERE `name` = ?";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			result = stmt.executeQuery();
			
			if(result.next()) {				
				Building building = new Building(result);
				return building;
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
	
	public boolean validateUniqueFields(Building building) {
		PreparedStatement stmt = null;
		StringBuilder query = new StringBuilder();
		query.append("SELECT COUNT(*) AS `buldingcount` FROM " + dbTable + " WHERE ");
		query.append("`name` = ?");
		try {
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, building.getName());			
			ResultSet rs = stmt.executeQuery();			
			rs.next();			
			return true ? rs.getInt("buldingcount") == 0 : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean create(Building building) {
		
		if( ! validateUniqueFields(building) ) return false;
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`name`) VALUES(?)";
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, building.getName());
			
			return stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id, Building building) {
		PreparedStatement stmt = null;
		String query = "UPDATE " + dbTable + " SET `name` = ?  WHERE `id` = ?";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, building.getName());
			stmt.setInt(5, id);
			return stmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
