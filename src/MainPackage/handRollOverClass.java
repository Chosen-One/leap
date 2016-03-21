package MainPackage;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.Frame;

public class handRollOverClass {
	
	private boolean altPressed = false;
	
	public boolean isHandRolledOver(Frame frame) {
		if(frame.hands().get(0).palmNormal().getY() >= .95) {
			return true;
		}
		return false;
	}
	
	public void switchToNextWindow(Robot robot){
		if(!altPressed) {
			robot.keyPress(KeyEvent.VK_ALT);
			altPressed = true;
		}
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}
	
	public boolean altPressed() {
		return altPressed;
	}
	
	public void releaseAlt(Robot robot) {
		robot.keyRelease(KeyEvent.VK_ALT);
		altPressed = false;
	}
}
