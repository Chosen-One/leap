package MainPackageTest;

import static org.junit.Assert.*;

import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

import MainPackage.configClass;

public class testConfigClass {
	
	public String configLocation = System.getProperty("user.dir")+"\\.testConfig";
	public configClass config = new configClass(configLocation);
	
	@Test
	public void testIsEmualatingMouse() {
		assertTrue(config.isEmulatingMouse());
	}
	
	@Test
	public void testIsCircleDefault() {
		assertTrue(config.isCircleDefault());
	}
	
	@Test
	public void testIsSwipeDefault() {
		assertTrue(config.isSwipeDefault());
	}
	
	@Test
	public void testSetMouseEmulation() {
		config.setMouseEmulation(false);
		
		assertFalse(config.isEmulatingMouse());
		
		config.setMouseEmulation(true);
	}
	
	
	@Test
	public void testSetCircle() {
		config.setCircle(false);
		
		assertFalse(config.isCircleDefault());
		
		config.setCircle(true);
	}	
	
	@Test
	public void testSetSwipe() {
		config.setSwipe(false);
		
		assertFalse(config.isSwipeDefault());
		
		config.setSwipe(true);
	}
}
