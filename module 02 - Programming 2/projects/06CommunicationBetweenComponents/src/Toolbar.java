/*
 * Programming 2
 * GUI Module
 * 06 - Communication between components
 * 
 * Spring Valley Training Modules
 * Java Programming
 * Jorge Cosgayon
 * 
 * Reference material:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener{
	private JButton btnHello;
	private JButton btnBye;
	
	private StringListener textListener;
	
	public Toolbar() {
		btnHello = new JButton("Hello");
		btnHello.addActionListener(this);
		
		btnBye = new JButton("Bye");
		btnBye.addActionListener(this);
		
		setLayout(new FlowLayout());
		
		add(btnHello);
		add(btnBye);
	}
	
	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}

	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		
		if(clicked == btnHello) {
			if( textListener != null ) {
				textListener.textCreated("Hello\n");
			}
		}else {
			if( textListener != null ) {
				textListener.textCreated("Goodbye\n");
			}
		}
		
	}

}
