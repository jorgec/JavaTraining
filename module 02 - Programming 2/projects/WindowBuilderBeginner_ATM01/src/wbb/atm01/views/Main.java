/**
 * Project Reference:
 * - Week 8 Machine Problem: ICS112-MachineProblem.pdf
 * 
 * Examples in this activity contain:
 * - HashMap (Java collections)
 * - ArrayList
 * - Reading from a CSV file
 * - JTable
 * - Table model
 * - Parsing a CSV file
 * - OOP: abstraction
 * - OOP: encapsulation
 * 
 * Suggested next steps
 * - exception handling for empty/nonexistent file
 * - exception handling for invalid PIN
 * - adding accounts
 * - saving the data to CSV
 * - separate class for button methods
 */

package wbb.atm01.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPIN;
	private AccountController accountController;
	private Account activeAccount;
	private JLabel lblBalanceValue;
	private JButton btnLogin;
	private JButton btnDeposit;
	private JButton btnWithdraw;
	private JTextField txtAmount;
	private JTable accountsTable;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setResizable(false);
		
		accountController = new AccountController();
		
		initComponents();
		initEvents();
		
		
	}
	
	public void initTable() {
		ArrayList<String[]> accounts = accountController.getAccountsAsCSV();
		model.setRowCount(0);
		for( String[] account: accounts ) {
			model.addRow(account);
		}
	}
	
	public void initEvents() {
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int pin = Integer.valueOf(txtPIN.getText());
				activeAccount = accountController.getAccount(pin);
				Double balance = activeAccount.getBalance();
				
				lblBalanceValue.setText(balance.toString());
				
				initTable();
				
			}
		});
		
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double amount = Double.valueOf(txtAmount.getText());
				if( activeAccount != null ) {
					if( activeAccount.deposit(amount) ) {
						
						JOptionPane.showMessageDialog(contentPane, "Deposit successful");
						Double balance = activeAccount.getBalance();
						accountController.updateAccount(activeAccount.getPin(), balance);
						
						lblBalanceValue.setText(balance.toString());
						initTable();
					}else {
						JOptionPane.showMessageDialog(contentPane, "Error message here");
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "No account selected");
				}
			}
		});
		
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double amount = Double.valueOf(txtAmount.getText());
				if( activeAccount != null ) {
					if( activeAccount.withdraw(amount) ) {
						JOptionPane.showMessageDialog(contentPane, "Withdraw successful");
						Double balance = activeAccount.getBalance();
						accountController.updateAccount(activeAccount.getPin(), balance);
						
						lblBalanceValue.setText(balance.toString());
						initTable();
					}else {
						JOptionPane.showMessageDialog(contentPane, "Error message here");
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "No account selected");
				}
			}
		});
		
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblEnterPin = new JLabel("Enter PIN:");
		
		txtPIN = new JTextField();
		txtPIN.setColumns(10);
		
		btnLogin = new JButton("Login");
		
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblBalance = new JLabel("Balance:");
		
		lblBalanceValue = new JLabel("New label");
		lblBalanceValue.setText("0.0");
		
		JLabel lblActions = new JLabel("Actions");
		
		JLabel lblAmount = new JLabel("Amount:");
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		
		btnWithdraw = new JButton("Withdraw");
		
		
		btnDeposit = new JButton("Deposit");
		
		accountsTable = new JTable();
		accountsTable.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		String[] accountsTableColumns = {"ID", "PIN", "Balance"};
		model = new DefaultTableModel();		
		model.setColumnIdentifiers(accountsTableColumns);
		
		accountsTable.setModel(model);
				
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(accountsTable, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnDeposit)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnWithdraw))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblEnterPin)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(txtPIN, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(347)
										.addComponent(btnLogin))
									.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblBalance)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(lblBalanceValue))
									.addComponent(lblActions)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblAmount)
										.addGap(18)
										.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
							.addGap(10)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPin)
						.addComponent(txtPIN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogin)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBalance)
						.addComponent(lblBalanceValue))
					.addGap(11)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblActions)
							.addGap(25))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAmount)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnWithdraw)
						.addComponent(btnDeposit))
					.addGap(43)
					.addComponent(accountsTable, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
