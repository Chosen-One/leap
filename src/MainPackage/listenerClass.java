package MainPackage;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.Type;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO.PI;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.omg.CORBA.PRIVATE_MEMBER;


public class listenerClass extends Listener {
	
	public Robot robot;
	private mouseEmulationClass mouseEmulation = new mouseEmulationClass();
	private handRollOverClass handRollOver = new handRollOverClass();
	private circleClass circle = new circleClass();
	private swipeClass swipe = new swipeClass();
	private nativeAccessClass nativeAccess = new nativeAccessClass();

	public void onConnect(Controller controller) {
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		
		controller.config().setBool("avoid_poor_performance", true);
		controller.config().setInt32("background_app_mode", 2);
		controller.config().setFloat("Gesture.Swipe.MinVelocity", 100f);
		controller.config().save();
	}
	
	public void onFrame(Controller controller) {
		
		try { robot = new Robot(); } catch(Exception e) {}
		
		Frame frame = controller.frame();

		if(mouseEmulation.isEmulatingMouse()) {
			mouseEmulation.emulateMouse(robot, frame);	
		}
		
//		if(!mouseEmulation.isGrabbed(frame) && mouseEmulation.mousePressed()) {
//			mouseEmulation.releaseMouse(robot);
//		}
		
		if(handRollOver.isHandRolledOver(frame)) {
			handRollOver.switchToNextWindow(robot);
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
		}
		
		if(!handRollOver.isHandRolledOver(frame)) {
			if(handRollOver.altPressed()) {
				handRollOver.releaseAlt(robot);
			}
		}
		
//		if(frame.hands().get(0).grabStrength() == 1) {
//			nativeAccess.minimizeCurrentWindow();
//			try { Thread.sleep(2000); } catch (InterruptedException e) {}
//		}
				
		for(Gesture gesture : frame.gestures()) {
			
			switch(gesture.type()) {
			
				case TYPE_CIRCLE:
					CircleGesture circleGesture = new CircleGesture(gesture);
					if(circleGesture.progress() > 0.8) {
						if(circle.isCircleClockWise(circleGesture)) {
							circle.executeClockWiseAction(robot);
							try { Thread.sleep(70); } catch(Exception e) {}
						} else {
							circle.executeAntiClockWiseAction(robot);
							try { Thread.sleep(70); } catch(Exception e) {}
						}
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
				
				default:
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
