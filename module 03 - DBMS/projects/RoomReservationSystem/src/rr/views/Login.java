package rr.views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import rr.models.Account;
import rr.models.AccountDataAccessObject;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	
	private AccountDataAccessObject accounts;
	private static Login frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		
		accounts = new AccountDataAccessObject();
		
		initComponents();
		initEvents();
		
	}

	private void initEvents() {
		// TODO Auto-generated method stub
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtUsername.getText();
				String password = txtPassword.getText();
				
				Account account = accounts.authenticate(username, password);
				
				if( account != null ) {
					if( account.getRole().equals("admin") ) {
						// show admin form
						AdminMainPage adminMainPage = new AdminMainPage();
						adminMainPage.setVisible(true);
						frame.hide();
					}else {
						UserMainPage userMainPage = new UserMainPage(account);
						userMainPage.setVisible(true);
						frame.hide();
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Invalid username or password!");
				}
				
			}
		});
		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsername = new JLabel("Username");
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		txtPassword = new JPasswordField();
		
		btnLogin = new JButton("Login");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addComponent(lblUsername)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
						.addComponent(btnLogin, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(159)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(79, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
