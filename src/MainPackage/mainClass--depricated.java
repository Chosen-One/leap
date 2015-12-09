//package MainPackage;
//
//import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;
//
//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//
//import com.leapmotion.leap.*;
//
//public class mainClass {
//		
//	public static void main(String [] args) {
//		
//		
//		
//		new newFrame();
//		
////		try {
////			System.in.read();
////		} catch(Exception e) {}
////		
////		controller.removeListener(listener);
//	}
//}
//
//class newFrame extends JFrame implements WindowListener {
//	
//	private Controller controller = new Controller();
//	private listenerClass listener = new listenerClass();
//	
//	public newFrame() {	
////		controller.addListener(listener);
////		// TODO Auto-generated constructor stub
////		SwingUtilities.invokeLater(new Runnable() {
////			
////			@Override
////			public void run() {
//				super("Leap Motion");
//				setVisible(true);
//				setSize(400, 500);
//				setResizable(true);
//				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//				controller.addListener(listener);
////			}
////		});
//	
//	this.addWindowListener(this);
//	}
//
//	@Override
//	public void windowOpened(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowClosing(WindowEvent e) {
//		// TODO Auto-generated method stub
//		controller.removeListener(listener);
//		System.out.println("Controller Removed!");
//		
//	}
//
//	@Override
//	public void windowClosed(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowIconified(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowDeiconified(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowActivated(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void windowDeactivated(WindowEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}
