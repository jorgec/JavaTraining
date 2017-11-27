/*
 * Programming 2
 * GUI Module
 * 02 - Adding components
 * 
 * Spring Valley Training Modules
 * Java Programming
 * Jorge Cosgayon
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