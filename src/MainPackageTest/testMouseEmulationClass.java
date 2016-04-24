package MainPackageTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.leapmotion.leap.Finger;
import com.leapmotion.leap.FingerList;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.InteractionBox;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.Vector;

import MainPackage.configClass;
import MainPackage.mouseEmulationClass;

public class testMouseEmulationClass {

	private String configLocation = System.getProperty("user.dir")+"\\.testConfig";
	public configClass config = new configClass(configLocation);
	public mouseEmulationClass mouseEmulation = new mouseEmulationClass(config);
	public ScreenTapGesture mockedScreepTapGesture;
	public Pointable mockedPointable;
	public Vector mockedVector;
	public Vector mockedVector2;
	public Vector mockedVector3;
	public Robot mockedRobot;
	public Frame mockedFrame;
	public HandList mockedHandList;
	public Hand mockedHand;
	public InteractionBox mockedInteractionBox;
	public FingerList mockedFingerList;
	public Finger mockedFinger;
	public GestureList mockedGestureList;
	public Gesture mockedGesture;
	public Iterator mockedIterator;
	
	@Before
	public void setup() {
		mockedScreepTapGesture = mock(ScreenTapGesture.class);
		mockedPointable = mock(Pointable.class);
		mockedVector = mock(Vector.class);
		mockedVector2 = mock(Vector.class);
		mockedVector3 = mock(Vector.class);
		mockedRobot = mock(Robot.class);
		mockedFrame = mock(Frame.class);
		mockedHandList = mock(HandList.class);
		mockedHand = mock(Hand.class);
		mockedInteractionBox = mock(InteractionBox.class);
		mockedFingerList = mock(FingerList.class);
		mockedFinger = mock(Finger.class);
		mockedGestureList = mock(GestureList.class);
		mockedGesture = mock(Gesture.class);
		mockedIterator = mock(Iterator.class);
	}
	
	@Test
	public void testIsEmulatingMouse() {
		assertTrue(mouseEmulation.isEmulatingMouse());
	}
	
	@Test
	public void testEmulateMouse() {
		when(mockedFrame.hands()).thenReturn(mockedHandList);
		when(mockedFrame.hands().get(0)).thenReturn(mockedHand);
		when(mockedFrame.hands().get(0).palmNormal()).thenReturn(mockedVector);
		when(mockedFrame.hands().get(0).palmNormal().getY())
		.thenReturn(-1f)
		.thenReturn(1f);
		
		when(mockedFrame.interactionBox()).thenReturn(mockedInteractionBox);
		
		when(mockedFrame.fingers()).thenReturn(mockedFingerList);
		when(mockedFrame.fingers().frontmost()).thenReturn(mockedFinger);
		when(mockedFrame.fingers().frontmost().stabilizedTipPosition()).thenReturn(mockedVector2);

		when(mockedInteractionBox.normalizePoint(mockedVector2)).thenReturn(mockedVector3);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		float normalizedX = 0.5f; float normalizedY = 0.5f; 
		when(mockedVector3.getX()).thenReturn(normalizedX);
		when(mockedVector3.getY()).thenReturn(normalizedY);
		int optimizedWidth = (int)(screenSize.width * normalizedX);
		int height = (int) (Math.ceil((normalizedY) * screenSize.getHeight()));
		int optimizedHeight = (int)((screenSize.height) - height);
		
		when(mockedFrame.gestures()).thenReturn(mockedGestureList);
		when(mockedFrame.gestures().iterator()).thenReturn(mockedIterator);
		when(mockedFrame.gestures().iterator().next()).thenReturn(mockedGesture);
		
		mouseEmulation.emulateMouse(mockedRobot, mockedFrame);
		
		verify(mockedRobot).mouseMove(optimizedWidth, optimizedHeight);	
	}
	
	@Test
	public void testUpdate() {
		config.setMouseEmulation(false);
		mouseEmulation.update(config);
		
		assertFalse(mouseEmulation.isEmulatingMouse());
		
		config.setMouseEmulation(true);
	}
}
