package MainPackageTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Vector;

import MainPackage.handRollOverClass;

public class testHandRollOverClass {

	public handRollOverClass handRollOver = new handRollOverClass();
	
	public Vector mockedVector;
	public Robot mockedRobot;
	public Frame mockedFrame;
	public HandList mockedHandList;
	public Hand mockedHand;
	
	@Before
	public void setup() {
		mockedVector = mock(Vector.class);
		mockedRobot = mock(Robot.class);
		mockedFrame = mock(Frame.class);
		mockedHandList = mock(HandList.class);
		mockedHand = mock(Hand.class);
	}
	
	@Test
	public void testIsHandRolledOver() {
		when(mockedFrame.hands()).thenReturn(mockedHandList);
		when(mockedFrame.hands().get(0)).thenReturn(mockedHand);
		when(mockedFrame.hands().get(0).palmNormal()).thenReturn(mockedVector);
		when(mockedFrame.hands().get(0).palmNormal().getY())
		.thenReturn((float)0.98)
		.thenReturn((float)0.5);
		
		assertTrue(handRollOver.isHandRolledOver(mockedFrame));
		
		assertFalse(handRollOver.isHandRolledOver(mockedFrame));
	}
	
	@Test
	public void testAltPressed() {
		assertFalse(handRollOver.altPressed());
	}
	
	@Test
	public void testSwitchToNextWindow() {
		handRollOver.switchToNextWindow(mockedRobot);
		
		verify(mockedRobot).keyPress(KeyEvent.VK_ALT);
		verify(mockedRobot).keyPress(KeyEvent.VK_TAB);
		verify(mockedRobot).keyRelease(KeyEvent.VK_TAB);
		
		assertTrue(handRollOver.altPressed());
	}
	
	@Test
	public void testReleaseAlt() {
		handRollOver.releaseAlt(mockedRobot);
		
		verify(mockedRobot).keyRelease(KeyEvent.VK_ALT);
		
		assertFalse(handRollOver.altPressed());
	}
	
	

}
