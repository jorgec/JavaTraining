package mytasks.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mytasks.models.Task;
import mytasks.models.TasksDAO;

public class TasksView extends JFrame {

	private JPanel contentPane;
	private JTextField txtTaskDescription;
	private JButton btnAddTask;
	private JButton btnMarkAsDone;
	
	private TasksDAO tasks;
	private DefaultTableModel modelTasks;
	private JScrollPane scrollPane;
	private JTable tblTasks;
	
	private int currentTaskId;
	private JButton btnDelete;
	private JButton btnMarkAsPending;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TasksView frame = new TasksView();
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
	public TasksView() {
		setResizable(false);
		tasks = new TasksDAO();
		currentTaskId = 0;
		
		initComponents();
		initTable();
		initEvents();
		
	}


	private void initTable() {
		try {
			
			
			Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
			Vector<String> tableHeaders = new Vector<String>();
			tableHeaders.add("Primary Key");
			tableHeaders.add("Description");
			tableHeaders.add("Status");
			
			ResultSet tasksList = tasks.get();
			
			while(tasksList.next()) {
				
				Vector<Object> row = new Vector<Object>();
				row.add(tasksList.getInt("id"));
				row.add(tasksList.getString("description"));
				if( tasksList.getBoolean("isDone")) {
					row.add("Tapos na");
				}else {
					row.add("Dulugangan pa ni Shawn");
				}
				
				tableData.add(row);
			}
			
			modelTasks = new DefaultTableModel(tableData, tableHeaders);
			tblTasks.setModel(modelTasks);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String description = txtTaskDescription.getText();
				boolean isDone = false;
				
				int optResult = JOptionPane.showConfirmDialog(contentPane, "Sure?");
				if( optResult == 0 ) {
				
					Task task = new Task(description, isDone);
					tasks.create(task);
					initTable();
				}
			}
		});
		
		tblTasks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int currentRow = tblTasks.getSelectedRow();
				String currentIdAtRow = tblTasks.getValueAt(currentRow, 0).toString();
				currentTaskId = Integer.parseInt( currentIdAtRow );				
			}
		});
		
		btnMarkAsDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( currentTaskId == 0 ) {
					JOptionPane.showMessageDialog(contentPane, "Please select a task!");
				}else {
					tasks.markAsDone(currentTaskId);
					initTable();
				}
			}
		});
		
		btnMarkAsPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( currentTaskId == 0 ) {
					JOptionPane.showMessageDialog(contentPane, "Please select a task!");
				}else {
					tasks.markAsPending(currentTaskId);
					initTable();
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( currentTaskId == 0 ) {
					JOptionPane.showMessageDialog(contentPane, "Please select a task!");
				}else {
					tasks.delete(currentTaskId);
					initTable();
				}
			}
		});
		
		
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblTask = new JLabel("Task");
		
		txtTaskDescription = new JTextField();
		txtTaskDescription.setColumns(10);
		
		btnAddTask = new JButton("Add Task");
		
		btnMarkAsDone = new JButton("Mark as Done");
		
		
		scrollPane = new JScrollPane();
		
		btnDelete = new JButton("Delete");
		
		btnMarkAsPending = new JButton("Mark as Pending");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addComponent(lblTask)
						.addComponent(txtTaskDescription, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
						.addComponent(btnAddTask)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnDelete)
							.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
							.addComponent(btnMarkAsPending)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMarkAsDone)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTask)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTaskDescription, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddTask)
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMarkAsDone)
						.addComponent(btnDelete)
						.addComponent(btnMarkAsPending))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		tblTasks = new JTable();
		
		scrollPane.setViewportView(tblTasks);
		contentPane.setLayout(gl_contentPane);
	}
}
