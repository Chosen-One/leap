package MainPackage;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.leapmotion.leap.SwipeGesture;

public class swipeClass {
	
	private int rightAction, leftAction;
	public final int moveLeft = KeyEvent.VK_LEFT, 
			moveRight = KeyEvent.VK_RIGHT;

	public swipeClass(configClass config) {
		if(config.isSwipeDefault()) {
			rightAction = moveLeft;
			leftAction = moveRight;
		} else {
			rightAction = moveRight;
			leftAction = moveLeft;
		}
	}

	public int getSwipeRightAction() {
		return rightAction;
	}
	
	public int getSwipeLeftAction() {
		return leftAction;
	}
	
	public boolean isSwipeToTheRight(SwipeGesture swipeGesture) {
		if(swipeGesture.direction().getX() > .1) {
			return true;
		}
		return false;
	}

	public void swipeRight(Robot robot) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(getSwipeRightAction());
		robot.keyRelease(getSwipeRightAction());
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	public void swipeLeft(Robot robot) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_WINDOWS);					
		robot.keyPress(getSwipeLeftAction());
		robot.keyRelease(getSwipeLeftAction());
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	public void update(configClass config) {
		if(config.isSwipeDefault()) {
			rightAction = moveLeft;
			leftAction = moveRight;
		} else {
			rightAction = moveRight;
			leftAction = moveLeft;
		}
	}
}
