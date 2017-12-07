package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
	private static final String CONNSTR = "jdbc:mysql://localhost:3306/room_reservation?useSSL=false";
	private static final String DBUSER = "root";
	private static final String DBPASS = "asdf1234";
	
	private Connection connection;
	
	public DB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void connect() {
		
		if( connection != null ) return;
		try {
			connection = (Connection) DriverManager.getConnection(CONNSTR, DBUSER, DBPASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if( connection != null ) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Connection getConnection() {
		return connection;
	}

	public String diagnostics() throws SQLException {
		return this.connection.getSchema();
	}
}
