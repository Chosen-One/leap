package MainPackage;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.leapmotion.leap.Controller;

public class mainClass extends JFrame implements WindowListener{

	private Controller controller = new Controller();
	private listenerClass listener = new listenerClass();
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new mainClass();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainClass() {
		super("Leap Motion");
		setVisible(true);
		setSize(400, 500);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller.addListener(listener);
		
		this.addWindowListener(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		controller.removeListener(listener);
		System.out.println("Controller Removed!");
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
