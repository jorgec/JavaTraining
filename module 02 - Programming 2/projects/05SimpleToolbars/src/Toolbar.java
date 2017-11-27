/*
 * Programming 2
 * GUI Module
 * 05 - Simple Toolbars
 * 
 * Spring Valley Training Modules
 * Java Programming
 * Jorge Cosgayon
 * 
 * Reference material:s
 * https://docs.oracle.com/javase/tutorial/uiswing/components/toolbar.html
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/visual.html#flow
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel {
	private JButton btnHello;
	private JButton btnBye;
	
	public Toolbar() {
		btnHello = new JButton("Hello");
		btnBye = new JButton("Bye");
		
		setLayout(new FlowLayout());
		
		add(btnHello);
		add(btnBye);
	}

}
