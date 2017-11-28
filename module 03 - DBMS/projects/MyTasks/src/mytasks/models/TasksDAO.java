package mytasks.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helpers.DB;

/**
 * 
 * @author Jorge
 * 
 * get() - get all
 * get(id) - get specific task
 * create(Task)
 * update(id, Task)
 * delete(id)
 */

public class TasksDAO {
	private static final String dbTable = "tasks";
	private DB db;
	private Connection conn;
	
	public TasksDAO() {
		db = new DB();
		conn = db.getConnection();
	}
	
	public ResultSet get() {
		ResultSet result = null;
		PreparedStatement stmt = null;
		
		String query = "SELECT * FROM " + dbTable;
		
		try {
			stmt = conn.prepareStatement(query);
			result = stmt.executeQuery();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(stmt.toString());
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void create(Task task) {
		String description = task.getDescription();
		boolean isDone = task.isDone();
		
		PreparedStatement stmt = null;
		String query = "INSERT INTO " + dbTable + "(`description`, `isDone`) VALUES(?, ?)";
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, description);
			stmt.setBoolean(2, isDone);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void markAsDone(int id) {
		String query = "UPDATE " + dbTable + " SET `isDone` = 1 WHERE `id` = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delete(int id) {
		String query = "DELETE FROM " + dbTable + " WHERE `id` = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void markAsPending(int id) {
		String query = "UPDATE " + dbTable + " SET `isDone` = 0 WHERE `id` = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
