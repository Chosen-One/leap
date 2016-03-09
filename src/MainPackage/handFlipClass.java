package MainPackage;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.Frame;

public class handFlipClass {
	
	public boolean isHandFlipped(Frame frame) {
		if(frame.hands().get(0).palmNormal().getY() >= .95) {
			return true;
		}
		return false;
	}
	
	public void switchToNextWindow(Robot robot){
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {}
		robot.keyRelease(KeyEvent.VK_ALT);
	}
}
