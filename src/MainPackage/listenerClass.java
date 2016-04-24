package MainPackage;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
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
	private mouseEmulationClass mouseEmulation;
	private handRollOverClass handRollOver;
	private circleClass circle;
	private swipeClass swipe;

	public listenerClass(mouseEmulationClass mouseEmulation,
			handRollOverClass handRollOver, circleClass circle, swipeClass swipe) {
		this.mouseEmulation = mouseEmulation;
		this.handRollOver = handRollOver;
		this.circle = circle;
		this.swipe = swipe;
	}
	
	public void onConnect(Controller controller) {
		controller.setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		
		controller.config().setBool("avoid_poor_performance", true);
		controller.config().setInt32("background_app_mode", 2);
		controller.config().setFloat("Gesture.Swipe.MinVelocity", 100f);
		controller.config().setFloat("Gesture.ScreenTap.MinForwardVelocity", 30.0f);
		controller.config().setFloat("Gesture.ScreenTap.HistorySeconds", .5f);
		controller.config().setFloat("Gesture.ScreenTap.MinDistance", 1.0f);
		controller.config().save();
	}
	
	public void onFrame(Controller controller) {
		
		try { robot = new Robot(); } catch(Exception e) {}
		
		Frame frame = controller.frame();

		if(mouseEmulation.isEmulatingMouse()) {
			mouseEmulation.emulateMouse(robot, frame);	
		}
		
		if(handRollOver.isHandRolledOver(frame)) {
			handRollOver.switchToNextWindow(robot);
			try { Thread.sleep(1000); } catch (InterruptedException e) {}
		}
		
		if(!handRollOver.isHandRolledOver(frame)) {
			if(handRollOver.altPressed()) {
				handRollOver.releaseAlt(robot);
			}
		}
						
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
					if(swipeGesture.state() == State.STATE_STOP) {
						if(swipe.isSwipeToTheRight(swipeGesture)) {			
							swipe.swipeRight(robot);
							try { Thread.sleep(1500); } catch(Exception e) {}
						} else {
							swipe.swipeLeft(robot);
							try { Thread.sleep(1500); } catch(Exception e) {}
						}
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
