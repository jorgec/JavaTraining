package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
	private int id;
	private int roomTypeId;
	private int buildingId;
	private String name;
	private int seatingCapacity;
	private boolean isAirConditioned;
	
	public Room() {
		
	}
	
	public Room(String name, int roomTypeId, int buildingId, int seatingCapacity, boolean isAirConditioned) {
		this.setName(name);
		this.setRoomTypeId(roomTypeId);
		this.setBuildingId(buildingId);
		this.setSeatingCapacity(seatingCapacity);
		this.setAirConditioned(isAirConditioned);
	}
	
	public Room(ResultSet result) {
		try {
			this.setId(result.getInt("id"));
			this.setName(result.getString("name"));
			this.setRoomTypeId(result.getInt("roomTypeId"));
			this.setBuildingId(result.getInt("buildingId"));
			this.setSeatingCapacity(result.getInt("seatingCapacity"));
			this.setAirConditioned(result.getBoolean("isAirConditioned"));
		}catch( SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getBuilding() {
		int id = this.getBuildingId();
		BuildingDataAccessObject bldgs = new BuildingDataAccessObject();
		Building b = bldgs.get(id);
		return b.getName();
	}
	
	public String getRoomType() {
		int id = this.getRoomTypeId();
		RoomTypeDataAccessObject rtdao = new RoomTypeDataAccessObject();
		RoomType rt = rtdao.get(id);
		return rt.getName();
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
	 * @return the roomTypeId
	 */
	public int getRoomTypeId() {
		return roomTypeId;
	}
	/**
	 * @param roomTypeId the roomTypeId to set
	 */
	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	/**
	 * @return the buildingId
	 */
	public int getBuildingId() {
		return buildingId;
	}
	/**
	 * @param buildingId the buildingId to set
	 */
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the seatingCapacity
	 */
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	/**
	 * @param seatingCapacity the seatingCapacity to set
	 */
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	/**
	 * @return the isAirConditioned
	 */
	public boolean isAirConditioned() {
		return isAirConditioned;
	}
	/**
	 * @param isAirConditioned the isAirConditioned to set
	 */
	public void setAirConditioned(boolean isAirConditioned) {
		this.isAirConditioned = isAirConditioned;
	}

}
