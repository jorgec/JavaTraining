package rr.views;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import rr.helpers.DB;
import rr.models.Account;
import rr.models.Building;
import rr.models.BuildingDataAccessObject;
import rr.models.Reservation;
import rr.models.ReservationDataAccessObject;
import rr.models.Room;
import rr.models.RoomDataAccessObject;
import rr.models.RoomType;
import rr.models.RoomTypeDataAccessObject;

public class UserMainPage extends JFrame {

	private JPanel contentPane;
	private JComboBox cboBuildingId;
	private JTable tblRoomSearch;
	private JSpinner spSeatingCapacity;
	private JComboBox cboRoomTypeId;
	private JCheckBox cbAirConditioning;
	private JButton btnReset;
	private JButton btnFilter;
	private JButton btnCheckAvailability;
	private JSpinner spStartDate;
	private JSpinner spEndDate;
	private JButton btnRequestReservation;
	
	private Reservation currentReservation;
	private ReservationDataAccessObject reservations;
	private ResultSet reservationsRS;
	private DefaultTableModel modelReservations;
	
	private Room room;
	private RoomDataAccessObject rooms;
	private ResultSet roomsRS;
	private DefaultTableModel modelRooms;
	
	private Building building;
	private BuildingDataAccessObject buildings;
	private ResultSet buildingsRS;
	private DefaultComboBoxModel modelBuildings;
	
	private RoomType roomType;
	private RoomTypeDataAccessObject roomTypes;
	private ResultSet roomTypesRS;
	private DefaultComboBoxModel modelRoomTypes;
	private JScrollPane scrollPane_1;
	private JTable tblReservations;
	
	private static Account account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainPage frame = new UserMainPage(account);
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
	public UserMainPage(Account account) {
		if( account == null ) {
			JOptionPane.showMessageDialog(contentPane, "Nope");
			
		}else {
			this.account = account;
			setResizable(false);
			setExtendedState(Frame.NORMAL);
			
			reservations = new ReservationDataAccessObject();		
			buildings = new BuildingDataAccessObject();				
			roomTypes = new RoomTypeDataAccessObject();		
			rooms = new RoomDataAccessObject();
			
			currentReservation = null;
			room = null;
			roomsRS = rooms.get();
			reservationsRS = reservations.get(account);
			
			initComponents();
			initBuildingComponents();
			initRoomTypeComponents();
			initRoomComponents();
			initReservationComponents();
			initEvents();
		}
		
		
	}

	private void initReservationComponents() {
		// TODO Auto-generated method stub
		if(reservationsRS != null) {
			try {
				modelReservations = DB.buildTableModel(reservationsRS);
				tblReservations.setModel(modelReservations);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void initRoomComponents() {
		// TODO Auto-generated method stub		
		if( roomsRS != null ) {
			try {
				modelRooms = this.buildRoomTableModel(roomsRS);
				tblRoomSearch.setModel(modelRooms);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
	

	private void initRoomTypeComponents() {
		// TODO Auto-generated method stub
		roomTypesRS = roomTypes.get();
		if(roomTypesRS != null) {
			try {
				roomTypesRS.beforeFirst();
				modelRoomTypes = DB.buildComboBoxModel(roomTypesRS);
				cboRoomTypeId.setModel(modelRoomTypes);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	private void initBuildingComponents() {
		// TODO Auto-generated method stub
		buildingsRS = buildings.get();
		if(buildingsRS != null) {
			try {
				buildingsRS.beforeFirst();
				modelBuildings = DB.buildComboBoxModel(buildingsRS);
				cboBuildingId.setModel(modelBuildings);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btnCheckAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnRequestReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( room == null ) {
					JOptionPane.showMessageDialog(rootPane, "Please select a room");
				}else {
					Date startDate = (Date) spStartDate.getValue();
					Date endDate = (Date) spEndDate.getValue();
					
					int roomId = room.getId();
					
					Reservation newReservation = new Reservation(roomId, account.getId(), startDate, endDate);
					boolean reserveStatus = reservations.create(newReservation);
					if( ! reserveStatus ) {
						JOptionPane.showMessageDialog(rootPane, "That room is unavailable on that date!");
					}else {
						JOptionPane.showMessageDialog(rootPane, "Reservation request successful, an admin will review it shortly.");
						initReservationComponents();
					}
				}
			}
		});
		
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Building b = buildings.get(cboBuildingId.getSelectedItem().toString());
				RoomType rt = roomTypes.get(cboRoomTypeId.getSelectedItem().toString());
				
				int buildingId = b.getId();
				int roomTypeId = rt.getId();
				int seatingCapacity = (int) spSeatingCapacity.getValue();
				boolean isAirConditioned = cbAirConditioning.isSelected();
				
				roomsRS = rooms.filter(buildingId, roomTypeId, seatingCapacity, isAirConditioned);
				
				initRoomComponents();
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomsRS = rooms.get();
				initRoomComponents();
			}
		});
		
		tblRoomSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = tblRoomSearch.getSelectedRow();
				int roomId = Integer.parseInt(tblRoomSearch.getValueAt(selectedRow, 0).toString());
				
				room = rooms.get(roomId);
				
				
			}
		});
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		
		setTitle("User Portal for " + account.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 903);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Make Reservation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 757, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		
		tblReservations = new JTable();
		tblReservations.setBorder(new TitledBorder(null, "Reservations", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane_1.setViewportView(tblReservations);
		
		JLabel lblBuilding = new JLabel("Building");
		
		JLabel lblRoomType = new JLabel("Room Type");
		
		cboBuildingId = new JComboBox();
		
		cboRoomTypeId = new JComboBox();
		
		JLabel lblCapacitymax = new JLabel("Capacity (Max)");
		
		spSeatingCapacity = new JSpinner();
		spSeatingCapacity.setModel(new SpinnerNumberModel(new Integer(50), null, null, new Integer(1)));
		
		cbAirConditioning = new JCheckBox("Air Conditioning");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Rooms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Start", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "End", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		btnFilter = new JButton("Filter");
		
		
		btnReset = new JButton("Reset");
		
		
		btnCheckAvailability = new JButton("Check Availability");
		
		btnRequestReservation = new JButton("Request Reservation");
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(cbAirConditioning)
									.addGap(73))
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblBuilding)
									.addComponent(cboBuildingId, 0, 161, Short.MAX_VALUE)
									.addComponent(lblRoomType))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(cboRoomTypeId, 0, 161, Short.MAX_VALUE)
									.addGap(31))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCapacitymax)
										.addComponent(spSeatingCapacity, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
									.addGap(31))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnFilter)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnReset)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnCheckAvailability)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRequestReservation)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblBuilding)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboBuildingId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblRoomType)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboRoomTypeId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCapacitymax)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spSeatingCapacity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cbAirConditioning)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnFilter)
								.addComponent(btnReset)))
						.addComponent(scrollPane, 0, 0, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRequestReservation)
						.addComponent(btnCheckAvailability))
					.addGap(91))
		);
		
		spEndDate = new JSpinner();
		spEndDate.setModel(new SpinnerDateModel(new Date(1511625600000L), new Date(1511625600000L), new Date(1764086400000L), Calendar.DAY_OF_YEAR));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(spEndDate, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(117, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(spEndDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		spStartDate = new JSpinner();
		
		// spStartDate.setEditor(new JSpinner.DateEditor(spStartDate, "MM/yyyy"));
		spStartDate.setModel(new SpinnerDateModel(new Date(1511625600000L), new Date(1511625600000L), new Date(1764086400000L), Calendar.MONTH));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(spStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(301, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(spStartDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		tblRoomSearch = new JTable();
		
		scrollPane.setViewportView(tblRoomSearch);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

}
