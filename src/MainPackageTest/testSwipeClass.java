package MainPackageTest;

import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import com.leapmotion.leap.SwipeGesture;
import com.leapmotion.leap.Vector;

import static org.mockito.Mockito.*;

import MainPackage.configClass;
import MainPackage.swipeClass;

public class testSwipeClass {
	
	public String configLocation = System.getProperty("user.dir")+"\\.testConfig";
	public configClass config = new configClass(configLocation);
	public swipeClass swipe = new swipeClass(config);
	
	public SwipeGesture mockedSwipeGesture;
	public Vector mockedVector;
	public Robot mockedRobot;
	
	@Before
	public void setup() {
		mockedSwipeGesture = mock(SwipeGesture.class);
		mockedVector = mock(Vector.class);
		mockedRobot = mock(Robot.class);
	}
	
	@Test
	public void testGetSwipeRightAndSwipeLeftAction() {
		assertEquals(KeyEvent.VK_RIGHT, swipe.getSwipeLeftAction());
		assertEquals(KeyEvent.VK_LEFT, swipe.getSwipeRightAction());
	}
	
	@Test
	public void testIsSwipeToTheRight() {
		when(mockedSwipeGesture.direction()).thenReturn(mockedVector);
		when(mockedSwipeGesture.direction().getX())
			.thenReturn((float)0.2)
			.thenReturn((float)0.05);
		
		assertEquals(true, swipe.isSwipeToTheRight(mockedSwipeGesture));
		
		assertEquals(false, swipe.isSwipeToTheRight(mockedSwipeGesture));
	}
	
	@Test
	public void testSwipeRight() {
		swipe.swipeRight(mockedRobot);
		
		verify(mockedRobot).keyPress(KeyEvent.VK_CONTROL);
		verify(mockedRobot).keyPress(KeyEvent.VK_WINDOWS);
		verify(mockedRobot).keyPress(swipe.getSwipeRightAction());
		verify(mockedRobot).keyRelease(swipe.getSwipeRightAction());
		verify(mockedRobot).keyRelease(KeyEvent.VK_CONTROL);
		verify(mockedRobot).keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	@Test
	public void testSwipeLeft() {
		swipe.swipeLeft(mockedRobot);
		
		verify(mockedRobot).keyPress(KeyEvent.VK_CONTROL);
		verify(mockedRobot).keyPress(KeyEvent.VK_WINDOWS);
		verify(mockedRobot).keyPress(swipe.getSwipeLeftAction());
		verify(mockedRobot).keyRelease(swipe.getSwipeLeftAction());
		verify(mockedRobot).keyRelease(KeyEvent.VK_CONTROL);
		verify(mockedRobot).keyRelease(KeyEvent.VK_WINDOWS);
	}
	
	@Test
	public void testUpdate() {
		config.setSwipe(false);
		swipe.update(config);
		
		assertEquals(KeyEvent.VK_LEFT, swipe.getSwipeLeftAction());
		assertEquals(KeyEvent.VK_RIGHT, swipe.getSwipeRightAction());
		
		config.setSwipe(true);
	}
}
