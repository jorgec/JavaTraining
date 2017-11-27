/*
 * Programming 2
 * GUI Module
 * 03 - Responding to button clicks
 * 
 * Spring Valley Training Modules
 * Java Programming
 * Jorge Cosgayon
 * 
 * Reference material:
 * https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		// add a listener to btn
		btn.addActionListener(new ActionListener() {
			
			// this method is performed whenever the button btn is clicked
			public void actionPerformed(ActionEvent arg0) {
				// for now we'll just append some text to the textArea object
				textArea.append("Nyan\n");
			}
			
		});
		
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
