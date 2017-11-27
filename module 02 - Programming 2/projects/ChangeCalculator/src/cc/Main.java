package cc;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private Change change;
	private JTable tblDenominations;
	private DefaultTableModel modelDenominations;
	private JTextField txtBill;
	private JTextField txtPayment;
	private JButton btnCalculate;

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
		setTitle("Change Calculator");
		initComponents();
		initTable();
		initEvents();
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int bill = Integer.parseInt(txtBill.getText());
				int payment = Integer.parseInt(txtPayment.getText());
				
				change.setBill(bill);
				change.setPayment(payment);
				
				initTable();
			}
		});
		
	}
	
	private void initTable() {
		// update
		// Change c = new Change();
		// copy the change field to the local variable c	
		Change c = change;
		
		int[] breakdowns = c.makeChange();
				
		int[] denominations = c.getDenominations();
		String[][] tableContents = new String[denominations.length][2];
		modelDenominations.setRowCount(0);
		
		for(int row = 0; row < denominations.length; row++ ) {			
			tableContents[row][0] = String.valueOf(denominations[row]);
			tableContents[row][1] = String.valueOf(breakdowns[row]);			
			modelDenominations.addRow(tableContents[row]);
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// create the Change object
		change = new Change();
		
		// load defaults
		change.setBill(0);
		change.setPayment(0);
		// /end load defaults
		
		// load the demoninations set in the Change object
		int[] denominations = change.getDenominations();
		
		tblDenominations = new JTable();
		tblDenominations.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		// define the JTable headers
		String[] denominationsTableColumns = {"Denomination", "Qty"};
		
		// the data in JTable will come from the modelDenominations DefaultTableModel
		modelDenominations = new DefaultTableModel();
		// set the column headers
		modelDenominations.setColumnIdentifiers(denominationsTableColumns);
		// attach the model to the JTable
		tblDenominations.setModel(modelDenominations);
		
		JLabel lblBill = new JLabel("Bill:");
		
		txtBill = new JTextField();
		txtBill.setColumns(10);
		
		JLabel lblPayment = new JLabel("Payment:");
		
		txtPayment = new JTextField();
		txtPayment.setColumns(10);
		
		btnCalculate = new JButton("Make Change");
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tblDenominations, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblBill)
							.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
							.addComponent(txtBill, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPayment)
							.addGap(18)
							.addComponent(txtPayment, GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE))
						.addComponent(btnCalculate, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBill)
						.addComponent(txtBill, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPayment)
						.addComponent(txtPayment, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnCalculate)
					.addPreferredGap(ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
					.addComponent(tblDenominations, GroupLayout.PREFERRED_SIZE, 426, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		

		
		
		
		contentPane.setLayout(gl_contentPane);
	}
}
