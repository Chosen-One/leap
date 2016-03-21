package MainPackage;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Vector;
import com.sun.jna.platform.win32.WinUser.INPUT;


public class mouseEmulationClass {
	
	private boolean mouseEmulation;
//	private boolean mousePressed = false;
	
	public mouseEmulationClass() {
		if(new configClass().isEmulatingMouse())
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
			for(Finger finger : frame.fingers()) {
				if(finger.type() == Finger.Type.TYPE_INDEX) {
//					if(isGrabbed(frame) && !mousePressed()) {
//						robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//						mousePressed = true;
//					}		
					Vector fingerPostion = finger.stabilizedTipPosition();
					Vector normalizedFingerPosition = interactionBox.normalizePoint(fingerPostion);
					Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
					robot.mouseMove((int)(screenSize.width * normalizedFingerPosition.getX()),
							(int)((screenSize.height) - (normalizedFingerPosition.getY()) * screenSize.height));
				}	
			}
			
			for(Gesture gesture : frame.gestures()) {
				if(gesture.type() == Gesture.Type.TYPE_SCREEN_TAP) {
					robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				}
			}
		}
	}
	
//	public boolean mousePressed() { 
//		return mousePressed;
//	}
//	
//	public boolean isGrabbed(Frame frame) {
//		if(frame.hands().get(0).pinchStrength() > 0.99)
//			return true;
//		return false;
//	}
//	
//	public void releaseMouse(Robot robot) {
//		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
//		mousePressed = false;
//	}
	
	public void update(configClass config) {
		mouseEmulation = config.isEmulatingMouse();
	}
}
