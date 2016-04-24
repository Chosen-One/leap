package MainPackageTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Vector;

import MainPackage.circleClass;
import MainPackage.configClass;

import static org.mockito.Mockito.*;

import java.awt.Robot;

public class testCircleClass {
	
	private String configLocation = System.getProperty("user.dir")+"\\.testConfig";
	public configClass config = new configClass(configLocation);
	public circleClass circle = new circleClass(config);
	public CircleGesture mockedCircleGesture;
	public Pointable mockedPointable;
	public Vector mockedVector;
	public Robot mockedRobot;
	
	@Before
	public void setup() {
		mockedCircleGesture = mock(CircleGesture.class);
		mockedPointable = mock(Pointable.class);
		mockedVector = mock(Vector.class);
		mockedRobot = mock(Robot.class);
	}
	
	@Test
	public void testGetClockWiseAndAntiClockWiseAction() {
		assertEquals(1, circle.getClockWiseAction());
		assertEquals(-1, circle.getAntiClockWiseAction());
	}
	
	@Test
	public void testIsCircleClockWise() {
		when(mockedCircleGesture.normal()).thenReturn(mockedVector);
		when(mockedCircleGesture.pointable()).thenReturn(mockedPointable);
		when(mockedCircleGesture.pointable().direction()).thenReturn(mockedVector);
		when(mockedCircleGesture.pointable().direction().angleTo(mockedCircleGesture.normal())).
			thenReturn((float)1).thenReturn((float)2);
		
		boolean actual = circle.isCircleClockWise(mockedCircleGesture);
		boolean expected = true;
		assertEquals(expected, actual);
		
		actual = circle.isCircleClockWise(mockedCircleGesture);
		expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExecuteClockWiseAndAntiClockWiseAction() {
		circle.executeClockWiseAction(mockedRobot);
		verify(mockedRobot).mouseWheel(circle.getClockWiseAction());
		
		circle.executeAntiClockWiseAction(mockedRobot);
		verify(mockedRobot).mouseWheel(circle.getAntiClockWiseAction());
	}
	
	@Test
	public void testUpdate() {
		config.setCircle(false);
		circle.update(config);
		
		assertEquals(-1, circle.getClockWiseAction());
		assertEquals(1, circle.getAntiClockWiseAction());
		
		config.setCircle(true);
	}
}
