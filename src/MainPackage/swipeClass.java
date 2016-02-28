package MainPackage;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class swipeClass {
	
	private int rightAction, leftAction;

	public swipeClass(int rightAction, int leftAction) {
		this.rightAction = rightAction;
		this.leftAction = leftAction;
	}

	public int getSwipeRightAction() {
		return rightAction;
	}
	
	public int getSwipeLeftAction() {
		return leftAction;
	}

	public void setSwipeRightAction(int rightAction) {
		this.rightAction = rightAction;
	}

	public void swipeLeftAction(int leftAction) {
		this.leftAction = leftAction;
	}
	
	public void Right(Robot robot) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_WINDOWS);
		robot.keyPress(getSwipeRightAction());
		robot.keyRelease(getSwipeRightAction());
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		try { Thread.sleep(2000); } catch(Exception e) {}
	}
	
	public void Left(Robot robot) {
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_WINDOWS);					
		robot.keyPress(getSwipeLeftAction());
		robot.keyRelease(getSwipeLeftAction());
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_WINDOWS);
		try { Thread.sleep(2000); } catch(Exception e) {}
	}
}
