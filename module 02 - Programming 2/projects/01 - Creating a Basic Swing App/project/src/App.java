/*
 * Programming 2
 * GUI Module
 * 01 - Creating a basic Swing application
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
				// create the frame
				JFrame frame = new JFrame("Hello, world!");
				
				// close the application when "x" button is clicked
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				// set the frame size
				frame.setSize(800, 600);
				
				// make the frame visible
				frame.setVisible(true);
				
			}
			
		});
		
		

	}

}

/*
 * What is the purpose of SwingUtilities.invokeLater?
 * Every program has a set of threads where the application logic begins. In standard programs, there's only one such thread: 
 * the thread that invokes the main method of the program class. In applets the initial threads are the ones that construct 
 * the applet object and invoke its init and start methods; these actions may occur on a single thread, or on two or three 
 * different threads, depending on the Java platform implementation. In this lesson, we call these threads the initial threads.
 * 
 * In Swing programs, the initial threads don't have a lot to do. Their most essential job is to create a Runnable object that 
 * initializes the GUI and schedule that object for execution on the event dispatch thread. Once the GUI is created, the program 
 * is primarily driven by GUI events, each of which causes the execution of a short task on the event dispatch thread. Application 
 * code can schedule additionals tasks on the event dispatch thread (if they complete quickly, so as not to interfere with event 
 * processing) or a worker thread (for long-running tasks).
 * 
 * An initial thread schedules the GUI creation task by invoking javax.swing.SwingUtilities.invokeLater or 
 * javax.swing.SwingUtilities.invokeAndWait . Both of these methods take a single argument: the Runnable that defines the new task. 
 * Their only difference is indicated by their names: invokeLater simply schedules the task and returns; invokeAndWait waits for 
 * the task to finish before returning.
 * 
 * https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
 */
