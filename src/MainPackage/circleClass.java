package MainPackage;

import java.awt.Robot;

import com.leapmotion.leap.CircleGesture;

public class circleClass {

	private int clockWiseAction, antiClockWiseAction;
	public final int scrollDown = 1, scrollUp = -1;

	public circleClass(configClass config) {
		if(config.isCircleDefault()) {
			clockWiseAction = scrollDown;
			antiClockWiseAction = scrollUp;
		} else {
			clockWiseAction = scrollUp;
			antiClockWiseAction = scrollDown;
		}
	}

	public int getClockWiseAction() {
		return clockWiseAction;
	}

	public int getAntiClockWiseAction() {
		return antiClockWiseAction;
	}
	
	public boolean isCircleClockWise(CircleGesture circleGesture) {
		if(circleGesture.pointable().direction().angleTo(circleGesture.normal()) <= Math.PI/2) {
			return true;
		}
		return false;
	}

	public void executeClockWiseAction(Robot robot) {
		robot.mouseWheel(getClockWiseAction());
	}

	public void executeAntiClockWiseAction(Robot robot) {
		robot.mouseWheel(getAntiClockWiseAction());
	}
	
	public void update(configClass config) {
		if(config.isCircleDefault()) {
			clockWiseAction = scrollDown;
			antiClockWiseAction = scrollUp;
		} else {
			clockWiseAction = scrollUp;
			antiClockWiseAction = scrollDown;
		}
	}
}