package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomType {
	private int id;
	private String name;

	public RoomType() {

	}
	
	public RoomType(String name) {
		this.setName(name);
	}

	public RoomType(ResultSet result) {
		try {
			this.setId(result.getInt("id"));
			this.setName(result.getString("name"));
		}catch( SQLException e) {
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

}
