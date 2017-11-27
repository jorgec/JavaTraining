/**
 * Examples in this activity contain:
 * - HashMap (Java collections)
 * - DateFormatSymbols (Java text utils)
 * - Calendar class
 * - programatically setting combo box values
 * - exception handling
 * - OOP: inheritance
 * - OOP: polymorphism
 * - OOP: abstraction
 * - OOP: encapsulation
 */


package wbb.billing02.views;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCalculate;
	private JLabel lblBillAmount;
	private JComboBox<String> cboMonth;
	private JRadioButton rbPackage3;
	private JRadioButton rbPackage2;
	private JRadioButton rbPackage1;
	private JTextField txtHours;
	private ButtonGroup packageGroup;

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
		
		initComponents();
		initEvents();
		
	}

	private void initEvents() {
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double inputHours = 0.0;
				try {
					inputHours = ! txtHours.getText().isEmpty() ? Double.valueOf(txtHours.getText()) : -1;
				} catch( NumberFormatException e ) {
					JOptionPane.showMessageDialog(contentPane, "Invalid number of hours!");
				}
				
				String inputMonth = cboMonth.getSelectedItem().toString();				
				Double maxHours = Month.getMonthHours(inputMonth);				
				Double bill = 0.0;			
				
				AbstractPackage thePackage = null;
				
				if( rbPackage1.isSelected() ) {
					thePackage = new PackageOne();
					
				}else if( rbPackage2.isSelected() ) {
					thePackage = new PackageTwo();
					
				}else if( rbPackage3.isSelected() ) {
					thePackage = new PackageThree();
					
				}
				
				if( inputHours > maxHours || inputHours < 0 ) {
					JOptionPane.showMessageDialog(contentPane, "Invalid number of hours!");
				}else {
					if( thePackage != null) {
						thePackage.calculatePayable(inputHours);
						bill = thePackage.getPayable();
						lblBillAmount.setText(bill.toString());
					}else {
						JOptionPane.showMessageDialog(contentPane, "Please select a package!");
					}
				}
				
			}
		});		
	}

	private void initComponents() {
		setTitle("Billing Demo 02");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		
		rbPackage1 = new JRadioButton("Package 1");
		rbPackage1.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel lblNewLabel = new JLabel("<html>For Php200.00 per month, 10 hours of access are provided.<br>Additional hours are PHP15.00 per hour.</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		
		rbPackage2 = new JRadioButton("Package 2");
		rbPackage2.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel label = new JLabel("<html>For Php500.00 per month, 20 hours of access are provided. <br>Additional hours are Php10.00 per hour.</html>");
		label.setVerticalAlignment(SwingConstants.TOP);
		
		rbPackage3 = new JRadioButton("Package 3");
		rbPackage3.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel label_1 = new JLabel("<html>For Php900.00 per month, unlimited access is provided</html>");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		
		packageGroup = new ButtonGroup();
		packageGroup.add(rbPackage1);
		packageGroup.add(rbPackage2);
		packageGroup.add(rbPackage3);
		
		JLabel lblMonth = new JLabel("Month");
		
		cboMonth = new JComboBox<String>();
		String[] months = new DateFormatSymbols().getMonths();
		cboMonth.setModel(new DefaultComboBoxModel<String>(months));
		
		JLabel lblHours = new JLabel("Hours");
		
		txtHours = new JTextField();
		txtHours.setText("0.0");
		txtHours.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblBill = new JLabel("Bill:");
		
		lblBillAmount = new JLabel("New label");
		
		btnCalculate = new JButton("Calculate");		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rbPackage1)
								.addComponent(rbPackage2))
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE))
							.addGap(28))
						.addComponent(rbPackage3, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMonth)
								.addComponent(lblHours))
							.addGap(63)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cboMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(16, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 511, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(16, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblBill)
							.addGap(18)
							.addComponent(lblBillAmount)
							.addContainerGap(447, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnCalculate)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rbPackage1)
							.addGap(22)
							.addComponent(rbPackage2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rbPackage3)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMonth)
						.addComponent(cboMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHours)
						.addComponent(txtHours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBill)
						.addComponent(lblBillAmount))
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(btnCalculate)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
