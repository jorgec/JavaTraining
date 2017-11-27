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
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App {

	public static void main(String[] args) {
		
		// run the GUI on a thread
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				new MainFrame();
				
				
			}
			
		});
		
		

	}

}