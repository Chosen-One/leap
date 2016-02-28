package MainPackage;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

import com.leapmotion.leap.Controller;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class mainClass extends JFrame implements WindowListener{

	private Controller controller = new Controller();
	private listenerClass listener = new listenerClass();
	private JPanel activeOption, activeOptionWindow;
	private boolean mouseEmulation = true;
		
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
		setSize(600, 500);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		controller.addListener(listener);
		
		this.addWindowListener(this);
		getContentPane().setLayout(null);
		
		JPanel header = new JPanel();
		header.setBackground(SystemColor.controlShadow);
		header.setBounds(0, 0, 584, 71);
		getContentPane().add(header);
		header.setLayout(null);
		
		JPanel sideBar = new JPanel();
		sideBar.setBackground(SystemColor.menu);
		sideBar.setBounds(0, 63, 178, 398);
		getContentPane().add(sideBar);
		sideBar.setLayout(null);
		
		JPanel option1Window = new JPanel();
		option1Window.setBackground(SystemColor.window);
		option1Window.setBounds(169, 63, 415, 398);
		getContentPane().add(option1Window);
		option1Window.setLayout(null);
		option1Window.setVisible(false);
		
		JPanel option2Window = new JPanel();
		option2Window.setBackground(SystemColor.window);
		option2Window.setBounds(169, 63, 415, 398);
		getContentPane().add(option2Window);
		option2Window.setLayout(null);
		option2Window.setVisible(false);
		
		JPanel option3Window = new JPanel();
		option3Window.setBackground(SystemColor.window);
		option3Window.setBounds(169, 63, 415, 398);
		getContentPane().add(option3Window);
		option3Window.setLayout(null);
		option3Window.setVisible(false);
		
		JPanel option4Window = new JPanel();
		option4Window.setBackground(SystemColor.window);
		option4Window.setBounds(169, 63, 415, 398);
		getContentPane().add(option4Window);
		option4Window.setLayout(null);
		option4Window.setVisible(false);
		
		setActiveOptionWindow(option1Window);
		
		JTextPane txtpnMoveYourMouse = new JTextPane();
		txtpnMoveYourMouse.setEditable(false);
		txtpnMoveYourMouse.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
		txtpnMoveYourMouse.setText("Move your mouse cursor on the screen using your index \r\nfinger. The Leap motion senseor tracks the coordinates \r\nof your finger and mirrors the movement using cursor \r\non your 2D screen.\r\n\r\n\t         \t\t");
		txtpnMoveYourMouse.setBounds(27, 69, 378, 88);
		option1Window.add(txtpnMoveYourMouse);
		
		JTextPane txtpnEnabledisableMouseEmulation = new JTextPane();
		txtpnEnabledisableMouseEmulation.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
		txtpnEnabledisableMouseEmulation.setEditable(false);
		txtpnEnabledisableMouseEmulation.setText("Enable/Disable Mouse Emulation");
		txtpnEnabledisableMouseEmulation.setBounds(27, 24, 343, 34);
		option1Window.add(txtpnEnabledisableMouseEmulation);
		
		JToggleButton tglbtnEnable = new JToggleButton("Disabled");
		if(mouseEmulation) {
			setButtonEnabled(tglbtnEnable);
		} else {
			setButtonDisabled(tglbtnEnable);
		}
		tglbtnEnable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tglbtnEnable.isSelected()) {
					setButtonEnabled(tglbtnEnable);
				} else {
					setButtonDisabled(tglbtnEnable);
				}
			}
		});
		tglbtnEnable.setBounds(27, 179, 121, 23);
		tglbtnEnable.setBorderPainted(false);
		option1Window.add(tglbtnEnable);
		
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
