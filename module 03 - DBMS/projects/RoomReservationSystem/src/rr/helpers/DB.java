package rr.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class DB {
	private static final String CONNSTR = "jdbc:mysql://localhost:3306/room_reservation?useSSL=false";
	private static final String DBUSER = "root";
	private static final String DBPASS = "asdf1234";
	private static final String DBNAME = "room_reservation";
	
	private Connection connection;
	
	public DB() {
		connect();
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

	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
		
	public static DefaultComboBoxModel<String> buildComboBoxModel(ResultSet rs) throws SQLException{
		Vector<String> data = new Vector<String>();	
		while(rs.next()) {
			data.add(rs.getString("name"));
		}
		return new DefaultComboBoxModel<String>(data);
	}
}
