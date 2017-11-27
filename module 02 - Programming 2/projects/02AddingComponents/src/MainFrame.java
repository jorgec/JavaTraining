/*
 * Programming 2
 * GUI Module
 * 02 - Adding components
 * 
 * Spring Valley Training Modules
 * Java Programming
 * Jorge Cosgayon
 * 
 * Reference material:
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html
 */

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {
	
	private JTextArea textArea;
	private JButton btn;
	
	public MainFrame() {
		super("Hello World");
		
		// create a layout
		setLayout(new BorderLayout());
		
		// add the components
		textArea = new JTextArea();
		btn = new JButton("Click Me!");
		
		// position the components
		add(textArea, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		// close the application when "x" button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set the frame size
		setSize(800, 600);
		
		// make the frame visible
		setVisible(true);
		
		// note: we are no longer referencing frame because these are already methods of MainFrame, which inherits from JFrame
	}
}
