/**
 * Room Scaffold
 * Learning objectives
 * - see how a relational model with foreign keys is represented in code
 * 
 * Suggested student activities
 * - Complete the ff functionality:
 * --- Rooms
 * ------ Room search
 * ------ Room filter
 * ------ Room update
 * ------ Change the integer value in the rooms table for air conditioning to say 'yes' or 'no'
 * --- Room Types 
 * ------ Room Type search
 * ------ Room Type update
 * 
 * 
 * Java SMP+ Training for Teachers
 * 
 * Jorge Cosgayon
 * Head of Development, Operations, and SPR/I.NT
 * Spring Valley Tech Corp.
 */
package rr.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import rr.helpers.DB;
import rr.models.Building;
import rr.models.BuildingDataAccessObject;
import rr.models.Room;
import rr.models.RoomDataAccessObject;
import rr.models.RoomType;
import rr.models.RoomTypeDataAccessObject;

public class AdminRoomFormPage extends JFrame {

	private JPanel contentPane;
	private JTextField btnSearchTerm;
	private JTextField txtRoomName;
	private JComboBox cboBuildingId;
	private JComboBox cboRoomTypeId;
	private JCheckBox cbAirConditioned;
	
	private JTextField txtBuildingName;
	private JButton btnUpdateBuilding;
	private JButton btnCreateBuilding;
	private JButton btnResetBuildingForm;
	private BuildingDataAccessObject buildings;
	private Building currentBuilding;
	private ResultSet buildingsRS;
	private DefaultTableModel modelBuildings;
	private JTable tblBuildings;
	private DefaultComboBoxModel modelCboBuildings;
	
	private RoomTypeDataAccessObject roomTypes;
	private RoomType currentRoomType;
	private DefaultTableModel modelRoomTypes;
	private DefaultComboBoxModel modelCboRoomTypes;
	private JButton btnCreateRoomType;
	private JButton btnUpdateRoomType;
	private JButton btnResetRoomTypeForm;
	private JTextField txtRoomTypeName;
	private JTable tblRoomTypes;
	private ResultSet roomTypesRS;
	
	private Room currentRoom;
	private RoomDataAccessObject rooms;
	private ResultSet roomsRS;
	private DefaultTableModel modelRooms;
	private JButton btnCreateRoom;
	private JButton btnUpdateRoom;
	private JButton btnResetRoomForm;
	private JSpinner spSeatingCapacity;
	private JScrollPane scrollPane_2;
	private JTable tblRooms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRoomFormPage frame = new AdminRoomFormPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminRoomFormPage() {
		
		buildings = new BuildingDataAccessObject();
		currentBuilding = null;
		
		roomTypes = new RoomTypeDataAccessObject();
		currentRoomType = null;
		
		rooms = new RoomDataAccessObject();
		currentRoom = null;
		
		initComponents();		
		
		initBuildingTable();
		initBuildingComponents();
		
		initRoomTypeTable();
		initRoomTypeComponents();
		
		initRoomTable();
		
		initEvents();
		
	}
	private DefaultTableModel buildRoomTableModel(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    columnNames.add("ID");
	    columnNames.add("Room Name");
	    columnNames.add("Building");
	    columnNames.add("Room Type");
	    columnNames.add("Seating Capacity");
	    columnNames.add("Air Conditioned?");
	    
	    
	    int columnCount = metaData.getColumnCount();
	    /*for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }*/

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	        	if( columnIndex == 3 ) { // buildingId
	        		Building item = buildings.get(rs.getInt(columnIndex));
	        		vector.add(item.getName());
	        	}else if( columnIndex == 4 ) { // roomTypeId
	        		RoomType item = roomTypes.get(rs.getInt(columnIndex));
	        		vector.add(item.getName());
	        	}else {
	        		vector.add(rs.getObject(columnIndex));
	        	}
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}
	private void initRoomTable() {
		// TODO Auto-generated method stub
		roomsRS = rooms.get();
		if(rooms != null) {
			try {
				modelRooms = this.buildRoomTableModel(roomsRS);
				tblRooms.setModel(modelRooms);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void initRoomTypeComponents() {
		// TODO Auto-generated method stub
		if(roomTypesRS != null) {
			try {
				roomTypesRS.beforeFirst();
				modelCboRoomTypes = DB.buildComboBoxModel(roomTypesRS);
				cboRoomTypeId.setModel(modelCboRoomTypes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void initRoomTypeTable() {
		// TODO Auto-generated method stub
		roomTypesRS = roomTypes.get();
		if(roomTypes != null) {
			try {
				modelRoomTypes = DB.buildTableModel(roomTypesRS);
				tblRoomTypes.setModel(modelRoomTypes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	private void initBuildingComponents() {
		// TODO Auto-generated method stub
		if(buildingsRS != null) {
			try {
				buildingsRS.beforeFirst();
				modelCboBuildings = DB.buildComboBoxModel(buildingsRS);
				cboBuildingId.setModel(modelCboBuildings);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void initBuildingTable() {
		// TODO Auto-generated method stub		
		buildingsRS = buildings.get();
		if( buildingsRS != null) {
			try {
				modelBuildings = DB.buildTableModel(buildingsRS);
				tblBuildings.setModel(modelBuildings);
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btnCreateBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtBuildingName.getText();
				Building building = new Building(name);
				
				buildings.create(building);
				initBuildingTable();
				initBuildingComponents();
			}
		});
		
		btnUpdateBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if( currentBuilding != null ) {
					String name = txtBuildingName.getText();
					currentBuilding.setName(name);
					
					buildings.update(currentBuilding.getId(), currentBuilding);
					initBuildingTable();
					initBuildingComponents();
				}else {
					JOptionPane.showMessageDialog(contentPane, "Please select a bulding");
					
				}
			}
		});
		
		
		
		tblBuildings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = tblBuildings.getSelectedRow();					
				int buildingId = Integer.parseInt( tblBuildings.getValueAt(selectedRow, 0).toString() );
				
				currentBuilding = buildings.get(buildingId);
				txtBuildingName.setText(currentBuilding.getName());
				
				btnCreateBuilding.setEnabled(false);
				btnUpdateBuilding.setEnabled(true);
				
			}
		});
		
		btnCreateRoomType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = txtRoomTypeName.getText();
				RoomType roomType = new RoomType(name);
				
				roomTypes.create(roomType);
				initRoomTypeTable();
				initRoomTypeComponents();
			}
		});
		
		btnCreateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomName = txtRoomName.getText();
				RoomType selectedRoomType = roomTypes.get(cboRoomTypeId.getSelectedItem().toString());
				int roomTypeId = selectedRoomType.getId();
				Building selectedBuilding = buildings.get(cboBuildingId.getSelectedItem().toString());
				int buildingId = selectedBuilding.getId();
				int seatingCapacity = (int) spSeatingCapacity.getValue();
				boolean isAirConditioned = cbAirConditioned.isSelected();
				
				Room newRoom = new Room(roomName, roomTypeId, buildingId, seatingCapacity, isAirConditioned);
				rooms.create(newRoom);
				
				initRoomTable();
				
			}
		});
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setTitle("Room Management");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 810, 947);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Rooms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Room", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Buildings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Room Types", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 428, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		btnCreateRoomType = new JButton("Create Room Type");
		
		
		btnUpdateRoomType = new JButton("Update Room Type");
		btnUpdateRoomType.setEnabled(false);
		
		btnResetRoomTypeForm = new JButton("Reset");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblRoomTypeName = new JLabel("Room Type Name");
		
		txtRoomTypeName = new JTextField();
		txtRoomTypeName.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addComponent(btnCreateRoomType, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btnUpdateRoomType, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(btnResetRoomTypeForm, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRoomTypeName, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRoomTypeName, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRoomTypeName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtRoomTypeName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreateRoomType)
						.addComponent(btnUpdateRoomType)
						.addComponent(btnResetRoomTypeForm))
					.addContainerGap())
		);
		
		tblRoomTypes = new JTable();
		scrollPane_1.setViewportView(tblRoomTypes);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblBuildingName = new JLabel("Building Name");
		
		txtBuildingName = new JTextField();
		txtBuildingName.setColumns(10);
		
		btnCreateBuilding = new JButton("Create Building");
		
		
		btnUpdateBuilding = new JButton("Update Building");
		btnUpdateBuilding.setEnabled(false);
		
		btnResetBuildingForm = new JButton("Reset");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(txtBuildingName, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
						.addComponent(lblBuildingName)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnCreateBuilding)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnUpdateBuilding)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnResetBuildingForm))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblBuildingName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtBuildingName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateBuilding)
						.addComponent(btnUpdateBuilding)
						.addComponent(btnResetBuildingForm))
					.addGap(9))
		);
		
		tblBuildings = new JTable();
		scrollPane.setViewportView(tblBuildings);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblName = new JLabel("Name");
		
		txtRoomName = new JTextField();
		txtRoomName.setColumns(10);
		
		JLabel lblBuilding = new JLabel("Building");
		
		cboBuildingId = new JComboBox();
		
		JLabel lblRoomType = new JLabel("Room Type");
		
		cboRoomTypeId = new JComboBox();
		
		JLabel lblCapacity = new JLabel("Capacity");
		
		cbAirConditioned = new JCheckBox("Air Conditioned");
		
		btnCreateRoom = new JButton("Create Room");
		
		
		btnUpdateRoom = new JButton("Update Room");
		btnUpdateRoom.setEnabled(false);
		
		btnResetRoomForm = new JButton("Reset");
		
		spSeatingCapacity = new JSpinner();
		spSeatingCapacity.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRoomType)
								.addComponent(lblName)
								.addComponent(lblBuilding)
								.addComponent(lblCapacity))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRoomName, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
								.addComponent(cboBuildingId, 0, 301, Short.MAX_VALUE)
								.addComponent(cboRoomTypeId, 0, 301, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(1)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(spSeatingCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(cbAirConditioned)))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnCreateRoom, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btnUpdateRoom, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(btnResetRoomForm, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtRoomName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuilding)
						.addComponent(cboBuildingId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRoomType)
						.addComponent(cboRoomTypeId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCapacity)
								.addComponent(spSeatingCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(53)
							.addComponent(cbAirConditioned)))
					.addPreferredGap(ComponentPlacement.RELATED, 175, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCreateRoom)
						.addComponent(btnUpdateRoom)
						.addComponent(btnResetRoomForm))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSearch = new JLabel("Search");
		
		btnSearchTerm = new JTextField();
		btnSearchTerm.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		
		scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblSearch)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearchTerm, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearch))
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 750, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSearch)
						.addComponent(btnSearchTerm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(18)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblRooms = new JTable();
		scrollPane_2.setViewportView(tblRooms);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		
	}
}
