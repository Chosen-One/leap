package MainPackage;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import com.leapmotion.leap.Config;
import com.leapmotion.leap.Controller;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class mainClass extends JFrame implements WindowListener {

	private Controller controller = new Controller();
	private listenerClass listener = new listenerClass();
	private JPanel activeOption, activeOptionWindow;
	private configClass config = new configClass();
	private final String images = "C:\\Users\\bilas\\Documents\\Workspace\\leap\\images\\";
		
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

	public mainClass() {
		super("Leap Motion");
		setVisible(true);
		setSize(600, 700);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller.addListener(listener);
		
		this.addWindowListener(this);
		getContentPane().setLayout(null);
		
		//header------------------------------------------------
		
		JPanel header = new JPanel();
		header.setBackground(Color.LIGHT_GRAY);
		header.setBounds(0, 0, 594, 71);
		getContentPane().add(header);
		header.setLayout(null);
		
		JLabel lblSettings = new JLabel("Help & Settings");
		lblSettings.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSettings.setBounds(10, 11, 179, 49);
		header.add(lblSettings);
		
		//siderBar-----------------------------------------------
		
		JPanel sideBar = new JPanel();
		sideBar.setBackground(SystemColor.menu);
		sideBar.setBounds(0, 63, 178, 608);
		getContentPane().add(sideBar);
		sideBar.setLayout(null);
		
		//sideBar-option1All-------------------------------------
		
		JPanel option1Window = new JPanel();
		option1Window.setBackground(SystemColor.window);
		option1Window.setBounds(169, 63, 425, 608);
		getContentPane().add(option1Window);
		option1Window.setLayout(null);
		option1Window.setVisible(false);
		
		setActiveOptionWindow(option1Window);
		
		JTextPane txtpnMoveYourMouse = new JTextPane();
		txtpnMoveYourMouse.setEditable(false);
		txtpnMoveYourMouse.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtpnMoveYourMouse.setText("Move your mouse cursor on the screen using your index \r\nfinger. The Leap motion senseor tracks the coordinates \r\nof your finger and mirrors the movement using cursor \r\non your 2D screen.\r\n\r\nYou can also tap on the screen which is recognised when \r\nthe finger pokes on the screen and immediately returns to\r\nthe approximate original position.\r\n\r\n\t         \t\t");
		txtpnMoveYourMouse.setBounds(29, 324, 378, 165);
		option1Window.add(txtpnMoveYourMouse);
		
		JTextPane txtpnEnabledisableMouseEmulation = new JTextPane();
		txtpnEnabledisableMouseEmulation.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnEnabledisableMouseEmulation.setEditable(false);
		txtpnEnabledisableMouseEmulation.setText("Enable/Disable mouse emulation");
		txtpnEnabledisableMouseEmulation.setBounds(29, 510, 343, 34);
		option1Window.add(txtpnEnabledisableMouseEmulation);
		
		JToggleButton mouseEmulationTgl = new JToggleButton("Disabled");
		mouseEmulationTgl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		if(config.isEmulatingMouse())
			setButtonEnabled(mouseEmulationTgl);
		else 
			setButtonDisabled(mouseEmulationTgl);
		mouseEmulationTgl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(mouseEmulationTgl.isSelected()) {
					setButtonEnabled(mouseEmulationTgl);
					config.setMouseEmulation(true);
					listener.save(config);
				} else {
					setButtonDisabled(mouseEmulationTgl);
					config.setMouseEmulation(false);
					listener.save(config);
				}
			}
		});
		mouseEmulationTgl.setBounds(29, 555, 121, 23);
		mouseEmulationTgl.setBorderPainted(false);
		option1Window.add(mouseEmulationTgl);
		
		JTextPane txtpnEmulateTheMouse = new JTextPane();
		txtpnEmulateTheMouse.setEditable(false);
		txtpnEmulateTheMouse.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnEmulateTheMouse.setText("Emulate the mouse with your finger");
		txtpnEmulateTheMouse.setBounds(27, 29, 380, 40);
		option1Window.add(txtpnEmulateTheMouse);
		
		JLabel screeenTapImage = new JLabel("");
		screeenTapImage.setIcon(new ImageIcon("C:\\Users\\bilas\\Documents\\Workspace\\leap\\images\\Leap_Gesture_Tap2.png"));
		screeenTapImage.setBounds(29, 80, 360, 240);
		option1Window.add(screeenTapImage);
		
		JPanel option1 = new JPanel();
		option1.setBounds(0, 8, 178, 55);
		option1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				option1.setBackground(SystemColor.scrollbar);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				option1.setBackground(SystemColor.menu);
				performActionIfActiveWindow(option1);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				activeOption.setBackground(SystemColor.menu);
				setActiveOption(option1);
				activeOptionWindow.setVisible(false);
				setActiveOptionWindow(option1Window);
			}
		});
		setActiveOption(option1);
		sideBar.add(option1);
		option1.setLayout(null);
		
		JLabel lblMouseEmulation = new JLabel("Mouse Emulation");
		lblMouseEmulation.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMouseEmulation.setBounds(44, 11, 96, 33);
		option1.add(lblMouseEmulation);
		
		//sideBar-option2All-------------------------------------
		
		JPanel option2Window = new JPanel();
		option2Window.setBackground(SystemColor.window);
		option2Window.setBounds(169, 63, 425, 608);
		getContentPane().add(option2Window);
		option2Window.setLayout(null);
		
		JTextPane txtpnChangeTheDirection = new JTextPane();
		txtpnChangeTheDirection.setEditable(false);
		txtpnChangeTheDirection.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnChangeTheDirection.setText("Change how you scroll");
		txtpnChangeTheDirection.setBounds(25, 489, 341, 33);
		option2Window.add(txtpnChangeTheDirection);
		
		JTextPane txtpnDefaultAClockwise = new JTextPane();
		txtpnDefaultAClockwise.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtpnDefaultAClockwise.setText("Default: Scroll down with a clockwise circle gesture with a finger and scroll up with an anti-clockwise circle gesture. \r\n\r\nInverted: Scroll up with a clockwise circle gesture with a finger and scroll down with an anti-clockwise circle gesture. ");
		txtpnDefaultAClockwise.setBounds(25, 318, 341, 160);
		option2Window.add(txtpnDefaultAClockwise);
		option2Window.setVisible(false);
		
		JToggleButton circleGestureTgl = new JToggleButton("Default");
		circleGestureTgl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		if(config.isCircleDefault())
			setButtonDefault(circleGestureTgl);
		else 
			setButtonInverted(circleGestureTgl);
		circleGestureTgl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(circleGestureTgl.isSelected()) {
					setButtonDefault(circleGestureTgl);
					config.setCircle(true);
					listener.save(config);
				} else {
					setButtonInverted(circleGestureTgl);
					config.setCircle(false);
					listener.save(config);
				}
			}
		});
		circleGestureTgl.setBounds(25, 540, 121, 23);
		circleGestureTgl.setBorderPainted(false);
		option2Window.add(circleGestureTgl);
		
		JTextPane txtpnDrawACircle = new JTextPane();
		txtpnDrawACircle.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnDrawACircle.setEditable(false);
		txtpnDrawACircle.setText("Draw a circle with your finger");
		txtpnDrawACircle.setBounds(25, 21, 345, 33);
		option2Window.add(txtpnDrawACircle);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\bilas\\Documents\\Workspace\\leap\\images\\Leap_Gesture_Circle.png"));
		lblNewLabel.setBounds(25, 65, 366, 242);
		option2Window.add(lblNewLabel);
		
		JPanel option2 = new JPanel();
		option2.setBounds(0, 62, 178, 55);
		option2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				option2.setBackground(SystemColor.scrollbar);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				option2.setBackground(SystemColor.menu);
				performActionIfActiveWindow(option2);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				activeOption.setBackground(SystemColor.menu);
				setActiveOption(option2);
				activeOptionWindow.setVisible(false);
				setActiveOptionWindow(option2Window);
			}
		});
		sideBar.add(option2);
		option2.setLayout(null);
		
		JLabel lblCircleGesture = new JLabel("Circle Gesture");
		lblCircleGesture.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCircleGesture.setBounds(51, 11, 80, 33);
		option2.add(lblCircleGesture);
		
		//sideBar-option3All-------------------------------------
		
		JPanel option3Window = new JPanel();
		option3Window.setBackground(SystemColor.window);
		option3Window.setBounds(169, 63, 425, 608);
		getContentPane().add(option3Window);
		option3Window.setLayout(null);
		option3Window.setVisible(false);
		
		JToggleButton swipeGestureTgl = new JToggleButton("Default");
		swipeGestureTgl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		if(config.isSwipeDefault())
			setButtonDefault(swipeGestureTgl);
		else 
			setButtonInverted(swipeGestureTgl);
		swipeGestureTgl.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(swipeGestureTgl.isSelected()) {
					setButtonDefault(swipeGestureTgl);
					config.setSwipe(true);
					listener.save(config);
				} else {
					setButtonInverted(swipeGestureTgl);
					config.setSwipe(false);
					listener.save(config);
				}
			}
		});
		swipeGestureTgl.setBounds(25, 561, 121, 23);
		swipeGestureTgl.setBorderPainted(false);
		option3Window.add(swipeGestureTgl);
		
		JTextPane txtpnSwipeYourHand = new JTextPane();
		txtpnSwipeYourHand.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnSwipeYourHand.setEditable(false);
		txtpnSwipeYourHand.setText("Swipe your hand to change workspaces");
		txtpnSwipeYourHand.setBounds(25, 26, 379, 37);
		option3Window.add(txtpnSwipeYourHand);
		
		JLabel swipeImage = new JLabel("New label");
		swipeImage.setIcon(new ImageIcon("C:\\Users\\bilas\\Documents\\Workspace\\leap\\images\\Leap_Gesture_Swipe.png"));
		swipeImage.setBounds(25, 74, 360, 240);
		option3Window.add(swipeImage);
		
		JTextPane txtpnDefaultSwipeTo = new JTextPane();
		txtpnDefaultSwipeTo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtpnDefaultSwipeTo.setEditable(false);
		txtpnDefaultSwipeTo.setText("Important: This feature only works on Windows 10.\r\n\r\nDefault: Swipe to the left to switch to the workspace on the right. Swipe to the right to switch to the workspace on the left.\r\n\r\nInverted: Default: Swipe to the right to switch to the workspace on the right. Swipe to the left to switch to the workspace on the left.");
		txtpnDefaultSwipeTo.setBounds(25, 353, 360, 117);
		option3Window.add(txtpnDefaultSwipeTo);
		
		JTextPane txtpnChangeTheSwipe = new JTextPane();
		txtpnChangeTheSwipe.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnChangeTheSwipe.setEditable(false);
		txtpnChangeTheSwipe.setText("Change the swipe action");
		txtpnChangeTheSwipe.setBounds(25, 496, 352, 30);
		option3Window.add(txtpnChangeTheSwipe);
		
		JPanel option3 = new JPanel();
		option3.setBounds(0, 117, 178, 55);
		option3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				option3.setBackground(SystemColor.scrollbar);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				option3.setBackground(SystemColor.menu);
				performActionIfActiveWindow(option3);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				activeOption.setBackground(SystemColor.menu);
				setActiveOption(option3);
				activeOptionWindow.setVisible(false);
				setActiveOptionWindow(option3Window);
			}
		});
		sideBar.add(option3);
		option3.setLayout(null);
		
		JLabel lblMultitasking = new JLabel("Swipe Gesture");
		lblMultitasking.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblMultitasking.setBounds(53, 11, 76, 33);
		option3.add(lblMultitasking);
		
		//sideBar-option4All-------------------------------------
		
		JPanel option4Window = new JPanel();
		option4Window.setBackground(SystemColor.window);
		option4Window.setBounds(169, 63, 425, 608);
		getContentPane().add(option4Window);
		option4Window.setLayout(null);
		
		JTextPane txtpnChangeTheOrientation = new JTextPane();
		txtpnChangeTheOrientation.setEditable(false);
		txtpnChangeTheOrientation.setFont(new Font("Segoe UI", Font.BOLD, 20));
		txtpnChangeTheOrientation.setText("Roll over to your hand to Multitask\r\n\r\n");
		txtpnChangeTheOrientation.setBounds(26, 38, 369, 38);
		option4Window.add(txtpnChangeTheOrientation);
		
		JTextPane txtpnCompletelyRollOver = new JTextPane();
		txtpnCompletelyRollOver.setEditable(false);
		txtpnCompletelyRollOver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtpnCompletelyRollOver.setText("Completely roll over your hand and remain in that \r\nposition to cycle through all the opened windows. \r\nTo select a desired window just roll over your hand \r\nback to original position.");
		txtpnCompletelyRollOver.setBounds(26, 101, 351, 111);
		option4Window.add(txtpnCompletelyRollOver);
		option4Window.setVisible(false);
		
		JPanel option4 = new JPanel();
		option4.setBounds(0, 172, 178, 55);
		option4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				option4.setBackground(SystemColor.scrollbar);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				option4.setBackground(SystemColor.menu);
				performActionIfActiveWindow(option4);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				activeOption.setBackground(SystemColor.menu);
				setActiveOption(option4);
				activeOptionWindow.setVisible(false);
				setActiveOptionWindow(option4Window);
			}
		});
		sideBar.add(option4);
		option4.setLayout(null);
		
		JLabel handRollOver = new JLabel("Multitasking");
		handRollOver.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		handRollOver.setBounds(53, 10, 76, 33);
		option4.add(handRollOver);
	}

	private void performActionIfActiveWindow(JPanel panel) {
		if(activeOption == panel) {
			panel.setBackground(SystemColor.activeCaption);
		}
	}
	
	private void setActiveOption(JPanel panel) {
		activeOption = panel;
		panel.setBackground(SystemColor.activeCaption);
	}

	private void setActiveOptionWindow(JPanel panel) {
		activeOptionWindow = panel;
		activeOptionWindow.setVisible(true);
	}
	
	private void setButtonEnabled(JToggleButton button) {
		button.setSelected(true);
		button.setBackground(SystemColor.activeCaption);
		button.setText("Enabled");
	}
	
	private void setButtonDisabled(JToggleButton button) {
		button.setSelected(false);
		button.setBackground(SystemColor.scrollbar);
		button.setText("Disabled");
	}
	
	private void setButtonDefault(JToggleButton button) {
		button.setSelected(true);
		button.setBackground(SystemColor.activeCaption);
		button.setText("Default");
	}
	
	private void setButtonInverted(JToggleButton button) {
		button.setSelected(false);
		button.setBackground(SystemColor.scrollbar);
		button.setText("Inverted");
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		controller.removeListener(listener);
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
