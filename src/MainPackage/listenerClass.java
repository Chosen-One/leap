package MainPackage;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.Type;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;


public class listenerClass extends Listener {
	
	public Robot robot;
	private mouseEmulationClass mouseEmulation = new mouseEmulationClass();
	private handFlipClass handFlip = new handFlipClass();
	private circleClass circle = new circleClass();
	private swipeClass swipe = new swipeClass();

	public void onConnect(Controller controller) {
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.config().setFloat("Gesture.Swipe.MinVelocity", 750f);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.config().save();
	}
	
	public void onFrame(Controller controller) {
		
		try { robot = new Robot(); } catch(Exception e) {}
		
		Frame frame = controller.frame();

		if(mouseEmulation.isEmulatingMouse()) {
			mouseEmulation.emulateMouse(robot, frame);	
		}
		
		if(!mouseEmulation.isGrabbed(frame) && mouseEmulation.mousePressed()) {
			mouseEmulation.releaseMouse(robot);
		}
		
		if(handFlip.isHandFlipped(frame)) {
			handFlip.switchToNextWindow(robot);
			try { 
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
				
		for(Gesture gesture : frame.gestures()) {
			
			switch(gesture.type()) {
			
				case TYPE_CIRCLE:
					CircleGesture circleGesture = new CircleGesture(gesture);
					if(circle.isCircleClockWise(circleGesture)) {
						circle.executeClockWiseAction(robot);
						try { Thread.sleep(70); } catch(Exception e) {}
					} else {
						circle.executeAntiClockWiseAction(robot);
						try { Thread.sleep(70); } catch(Exception e) {}
					}
					break;
				
		
				case TYPE_SWIPE:
					SwipeGesture swipeGesture = new SwipeGesture(gesture);
					if(swipe.isSwipeToTheRight(swipeGesture)) {			
						swipe.swipeRight(robot);	
						try { Thread.sleep(2500); } catch(Exception e) {}
					} else {
						swipe.swipeLeft(robot);
						try { Thread.sleep(2500); } catch(Exception e) {}
					}
					break;
			}
		}
	}
				
	public void save(configClass config) {
		mouseEmulation.update(config);
		circle.update(config);
		swipe.update(config);	
	}
}
