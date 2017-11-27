package rr.views;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import rr.helpers.DB;
import rr.models.Account;
import rr.models.AccountDataAccessObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;

public class AdminUserFormPage extends JFrame {

	private JPanel contentPane;
	private JTable tblUsers;
	
	
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblRole;
	private JTextField txtUsername;
	private JTextField txtFirstName;
	private JPasswordField txtPassword;
	private JTextField txtLastName;
	private JComboBox cboRole;
	private JSeparator separator;
	private JButton btnReset;
	private JButton btnCreate;
	private JButton btnUpdate;
	
	private AccountDataAccessObject accounts;
	private DefaultTableModel modelAccounts;
	private Account account;
	private ResultSet accountsRS;
	private JSeparator separator_2;
	private JLabel lblSearch;
	private JTextField txtSearchTerm;
	private JButton btnSearch;
	private JButton btnChangePassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUserFormPage frame = new AdminUserFormPage();
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
	public AdminUserFormPage() {
		setResizable(false);
		
		accounts = new AccountDataAccessObject();
		account = null;
		
		initComponents();
		initTable();
		initEvents();
		
		
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		
		tblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow = tblUsers.getSelectedRow();					
				int accountNumber = Integer.parseInt( tblUsers.getValueAt(selectedRow, 0).toString() );
				
				account = accounts.get(accountNumber);
				
				txtUsername.setText(account.getUsername());
				txtPassword.setText(account.getPassword());
				txtFirstName.setText(account.getFirstName());
				txtLastName.setText(account.getLastName());
				cboRole.setSelectedItem(account.getRole());
				
				btnUpdate.setEnabled(true);
				btnChangePassword.setEnabled(true);
				txtUsername.setEnabled(false);
				txtPassword.setEnabled(false);
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account = null;
				txtUsername.setText("");
				txtPassword.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				cboRole.setSelectedItem("user");
				
				btnUpdate.setEnabled(false);
				btnChangePassword.setEnabled(false);
				txtUsername.setEnabled(true);
				txtPassword.setEnabled(true);
				initTable();
			}
		});
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				String firstName = txtFirstName.getText();
				String lastName = txtLastName.getText();
				String role = cboRole.getSelectedItem().toString();
				
				Account newAccount = new Account(username, password, firstName, lastName, role);
				
				accounts.create(newAccount);
				btnChangePassword.setEnabled(false);
				txtUsername.setEnabled(true);
				txtPassword.setEnabled(true);
				initTable();
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchTerm = txtSearchTerm.getText().trim();
				if( ! searchTerm.isEmpty() ) {
					accountsRS = accounts.search(searchTerm);
					try {
						modelAccounts = DB.buildTableModel(accountsRS);
						tblUsers.setModel(modelAccounts);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = txtFirstName.getText();
				String lastName = txtLastName.getText();
				String role = cboRole.getSelectedItem().toString();
				
				account.setFirstName(firstName);
				account.setLastName(lastName);
				account.setRole(role);
				
				accounts.update(account.getId(), account);
				
				btnUpdate.setEnabled(false);
				btnChangePassword.setEnabled(false);
				txtUsername.setEnabled(true);
				txtPassword.setEnabled(true);
				initTable();
				
			}
		});
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPassword = JOptionPane.showInputDialog("Enter new password");
				account.setPassword(newPassword);
				accounts.update(account.getId(), account);
				
				initTable();
			}
		});
		
	}
	
	private void initTable() {
		accountsRS = accounts.get();
				
		try {
			modelAccounts = DB.buildTableModel(accountsRS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblUsers.setModel(modelAccounts);
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setTitle("User Management");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 810, 947);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		lblUsername = new JLabel("Username");
		
		lblPassword = new JLabel("Password");
		
		lblFirstName = new JLabel("First Name");
		
		lblLastName = new JLabel("Last Name");
		
		lblRole = new JLabel("Role");
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		
		txtPassword = new JPasswordField();
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		
		cboRole = new JComboBox();
		cboRole.setModel(new DefaultComboBoxModel(new String[] {"user", "admin"}));
		
		separator = new JSeparator();
		
		btnReset = new JButton("Reset");
		
		btnCreate = new JButton("Create");
		
		btnUpdate = new JButton("Update");
		
		btnUpdate.setEnabled(false);
		
		separator_2 = new JSeparator();
		
		lblSearch = new JLabel("Search");
		
		txtSearchTerm = new JTextField();
		txtSearchTerm.setColumns(10);
		
		btnSearch = new JButton("Search");
		
		btnChangePassword = new JButton("Change Password");
		
		btnChangePassword.setEnabled(false);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUsername)
									.addGap(29)
									.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(32)
									.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)))
							.addGap(32)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblLastName))
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtLastName, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
								.addComponent(txtFirstName, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)))
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCreate)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnUpdate)
							.addPreferredGap(ComponentPlacement.RELATED, 440, Short.MAX_VALUE)
							.addComponent(btnChangePassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnReset))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(lblRole)
							.addGap(61)
							.addComponent(cboRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSearch)
							.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
							.addComponent(txtSearchTerm, GroupLayout.PREFERRED_SIZE, 596, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearch))
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFirstName))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLastName)
								.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addComponent(lblRole))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(cboRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnCreate)
						.addComponent(btnUpdate)
						.addComponent(btnChangePassword))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(txtSearchTerm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSearch))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tblUsers = new JTable();
		scrollPane.setViewportView(tblUsers);
		contentPane.setLayout(gl_contentPane);
	}
}
