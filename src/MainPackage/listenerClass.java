package MainPackage;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.Gesture.Type;

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Robot;
import java.awt.RenderingHints.Key;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.security.auth.x500.X500Principal;
import javax.sound.midi.ControllerEventListener;

public class listenerClass extends Listener {
	
	public Robot robot;
	public boolean emulateMouse = true;
	private circleClass circle = new circleClass(1, -1);
	private swipeClass swipe = new swipeClass(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

	public void onConnect(Controller controller) {
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.config().setFloat("Gesture.Swipe.MinVelocity", 2000f);
	}
	
	public void onFrame(Controller controller) {
		
		try { 
			robot = new Robot();
		} catch(Exception e) {}
		
		Frame frame = controller.frame();

		if(emulateMouse) {
			InteractionBox interactionBox = frame.interactionBox();
			for(Finger finger : frame.fingers()) {
				if(finger.type() == Finger.Type.TYPE_INDEX) {
					Vector fingerPostion = finger.stabilizedTipPosition();
					Vector normalizedFingerPosition = interactionBox.normalizePoint(fingerPostion);
					Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
					robot.mouseMove((int)(screenSize.width * normalizedFingerPosition.getX()),
							(int)(screenSize.height - normalizedFingerPosition.getY() * screenSize.height));
				}
			}	
		}
			
		if(isHandFlipped(frame)) {
			switchToMostRecentWindow(controller, robot);
		}
				
		for(Gesture gesture : frame.gestures()) {
			
			if(gesture.type() == Type.TYPE_CIRCLE) {
				CircleGesture circleGesture = new CircleGesture(gesture);
				if(isCircleClockWise(circleGesture)) {
					System.out.println("Swipe ClockWise Detected!");
					circle.executeClockWiseAction(robot);
				} else {
					System.out.println("Swipe AntiClockWise Detected!");
					circle.executeAntiClockWiseAction(robot);
				}
			}
	
			if(gesture.type() == Type.TYPE_SWIPE) {
				SwipeGesture swipeGesture = new SwipeGesture(gesture);
				if(isSwipeToTheRight(swipeGesture)) {			
					swipe.Right(robot);					
				} else {
					swipe.Left(robot);
				}
			}
			
			if(gesture.type() == Type.TYPE_KEY_TAP) {
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			}
		}
	}
	
	private boolean isHandFlipped(Frame frame) {
		if(frame.hands().get(0).palmNormal().getY() >= .95) {
			return true;
		}
		return false;
	}
	
	private void switchToMostRecentWindow(Controller controller, Robot robot) {
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_ALT);

	}
	
	private boolean isCircleClockWise(CircleGesture circleGesture) {
		if(circleGesture.pointable().direction().angleTo(circleGesture.normal()) <= Math.PI/2) {
			return true;
		}
		return false;
	}

	private boolean isSwipeToTheRight(SwipeGesture swipeGesture) {
		if(swipeGesture.direction().getX() > .1) {
			return true;
		}
		return false;
	}
}
