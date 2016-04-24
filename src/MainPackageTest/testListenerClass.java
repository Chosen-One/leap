package MainPackageTest;

import org.junit.Before;
import org.junit.Test;

import com.leapmotion.leap.*;

import static org.mockito.Mockito.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Iterator;

import MainPackage.circleClass;
import MainPackage.configClass;
import MainPackage.handRollOverClass;
import MainPackage.listenerClass;
import MainPackage.mouseEmulationClass;
import MainPackage.swipeClass;

public class testListenerClass {
	
	public configClass mockedConfigClass;
	public mouseEmulationClass mockedMouseEmulation;
	public handRollOverClass mockedHandRollOver;
	public swipeClass mockedSwipe;
	public circleClass mockedCircle;
	public listenerClass listener;
	
	public Controller mockedController;
	public Config mockedConfig;
	public Frame mockedFrame;
	public Robot mockedRobot;
	public GestureList mockedGestureList;
	public Gesture mockedGesture;
	public Iterator mockedIterator;
	
	@Before
	public void setup() {
		mockedConfigClass = mock(configClass.class);
		mockedController = mock(Controller.class);
		mockedConfig = mock(Config.class);
		mockedFrame = mock(Frame.class);
		mockedRobot = mock(Robot.class);
		mockedMouseEmulation = mock(mouseEmulationClass.class);
		mockedHandRollOver = mock(handRollOverClass.class);
		mockedCircle = mock(circleClass.class);
		mockedSwipe = mock(swipeClass.class);
		mockedGestureList = mock(GestureList.class);
		mockedGesture = mock(Gesture.class);
		mockedIterator = mock(Iterator.class);
		listener = new listenerClass(mockedMouseEmulation, mockedHandRollOver,
				mockedCircle, mockedSwipe);
		
	}
	
	@Test
	public void testOnConnect() {
		doNothing().when(mockedController).setPolicy(any());
		doNothing().when(mockedController).enableGesture(any());
		when(mockedController.config()).thenReturn(mockedConfig);
	
		listener.onConnect(mockedController);

		verify(mockedController).setPolicy(Controller.PolicyFlag.POLICY_BACKGROUND_FRAMES);
		verify(mockedController).enableGesture(Gesture.Type.TYPE_CIRCLE);
		verify(mockedController).enableGesture(Gesture.Type.TYPE_SWIPE);
		verify(mockedController).enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
	}
	
	@Test
	public void testOnFrame() {
		when(mockedController.frame()).thenReturn(mockedFrame);
		when(mockedMouseEmulation.isEmulatingMouse()).thenReturn(true);
		when(mockedHandRollOver.isHandRolledOver(mockedFrame)).thenReturn(false);
		when(mockedHandRollOver.altPressed()).thenReturn(false);
		
		when(mockedFrame.gestures()).thenReturn(mockedGestureList);
		when(mockedFrame.gestures().iterator()).thenReturn(mockedIterator);
		when(mockedFrame.gestures().iterator().next()).thenReturn(mockedGesture);
		
		listener.onFrame(mockedController);
		
		verify(mockedMouseEmulation, times(1)).emulateMouse(listener.robot, mockedFrame);
		verify(mockedHandRollOver, times(0)).switchToNextWindow(listener.robot);
		verify(mockedHandRollOver, times(1)).altPressed();
	}
	
	@Test
	public void testSave() {
		listener.save(mockedConfigClass);
		 verify(mockedMouseEmulation, times(1)).update(mockedConfigClass);
		 verify(mockedCircle, times(1)).update(mockedConfigClass);
		 verify(mockedSwipe, times(1)).update(mockedConfigClass);
	}
}
