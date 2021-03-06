package MainPackage;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Vector;

public class mouseEmulationClass {
	
	private boolean mouseEmulation;
	
	public mouseEmulationClass(configClass config) {
		if(config.isEmulatingMouse())
			mouseEmulation = true;
		else
			mouseEmulation = false;
	}
	
	public boolean isEmulatingMouse() {
		return mouseEmulation;
	}
	
	public void emulateMouse(Robot robot, Frame frame) {
		if(frame.hands().get(0).palmNormal().getY() < -.5) {
			InteractionBox interactionBox = frame.interactionBox();
			Vector fingerPostion = frame.fingers().frontmost().stabilizedTipPosition();
			Vector normalizedFingerPosition = interactionBox.normalizePoint(fingerPostion);
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
			int optimizedWidth = (int)(screenSize.width * normalizedFingerPosition.getX());
			int height = (int) (Math.ceil((normalizedFingerPosition.getY()) * screenSize.getHeight()));
			int optimizedHeight = (int)((screenSize.height) - height);
			robot.mouseMove(optimizedWidth, optimizedHeight);
	
			for(Gesture gesture : frame.gestures()) {
				if(gesture.type() == Gesture.Type.TYPE_SCREEN_TAP) {
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				}
			}
		}
	}
	
	public void update(configClass config) {
		mouseEmulation = config.isEmulatingMouse();
	}
}
