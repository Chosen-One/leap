package MainPackage;

import java.awt.Robot;

public class circleClass {
	
	private int clockWiseAction, antiClockWiseAction;

	public circleClass(int clockWiseAction, int antiClockWiseAction) {
		this.clockWiseAction = clockWiseAction;
		this.antiClockWiseAction = antiClockWiseAction;
	}

	public int getClockWiseAction() {
		return clockWiseAction;
	}

	public void setClockWiseAction(int clockWiseAction) {
		this.clockWiseAction = clockWiseAction;
	}

	public int getAntiClockWiseAction() {
		return antiClockWiseAction;
	}

	public void setAntiClockWiseAction(int antiClockWiseAction) {
		this.antiClockWiseAction = antiClockWiseAction;
	}

	public void executeClockWiseAction(Robot robot) {
		robot.mouseWheel(getClockWiseAction());
		try { Thread.sleep(50); } catch(Exception e) {}
	}

	public void executeAntiClockWiseAction(Robot robot) {
		robot.mouseWheel(getAntiClockWiseAction());
		try { Thread.sleep(50); } catch(Exception e) {}
	}
}