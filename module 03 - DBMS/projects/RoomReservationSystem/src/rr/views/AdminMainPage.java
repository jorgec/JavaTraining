/**
 * OOP Design Patterns
 * This project illustrates the use of the Data Access Object design pattern
 * 
 * 
 * Suggestions for future exploration
 * --- explore how to apply the Singleton pattern on the DAO objects
 * --- explore how to apply the MVC pattern by separating the logic from the Views
 * ------ see also: Observer, Listener patterns
 * 
 * 
 * Java SMP+ Training for Teachers
 * 
 * Jorge Cosgayon
 * Head of Development, Operations, and SPR/I.NT
 * Spring Valley Tech Corp.
 * 
 */

package rr.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AdminMainPage extends JFrame {

	private JPanel contentPane;
	private JButton btnRoomMgt;
	private JButton btnUserMgt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMainPage frame = new AdminMainPage();
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
	public AdminMainPage() {
		
		initComponents();
		initEvents();
		
		
	}

	private void initEvents() {
		btnUserMgt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminUserFormPage userPage = new AdminUserFormPage();
				userPage.setVisible(true);
			}
		});
		btnRoomMgt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Admin Portal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 2384, GroupLayout.PREFERRED_SIZE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		);
		
		btnUserMgt = new JButton("User Management");
		
		btnUserMgt.setMnemonic('U');
		toolBar.add(btnUserMgt);
		
		btnRoomMgt = new JButton("Room Management");
		
		toolBar.add(btnRoomMgt);
		contentPane.setLayout(gl_contentPane);
	}
}
