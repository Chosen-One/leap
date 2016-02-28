package MainPackage;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;

public class nativeAccessClass {
	
	private final int MAX_TITLE_LENGTH = 1024;
	private enumWindowsClass enumWindows = new enumWindowsClass();
	
	public String getActiveWindowTitle(){
	     char[] buffer = new char[MAX_TITLE_LENGTH * 2];
	     HWND foregroundWindow = User32.INSTANCE.GetForegroundWindow();
	     User32.INSTANCE.GetWindowText(foregroundWindow, buffer, MAX_TITLE_LENGTH); 
	     String windowTitle =  Native.toString(buffer);
	     return windowTitle;
	}
	
	public void minimizeCurrentWindow() {
		HWND foregroundWindow = User32.INSTANCE.GetForegroundWindow();
		User32.INSTANCE.ShowWindow(foregroundWindow, WinUser.SW_MINIMIZE);
	}
	
	public void goToNextWindow() {
		enumWindows.setNextWindowAsForegroundWindow();
	}
}
