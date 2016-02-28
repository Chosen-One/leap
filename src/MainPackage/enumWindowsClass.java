package MainPackage;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;

public class enumWindowsClass {
	
	private static HWND [] runningApplicationHandles = new HWND[200]; 
	private static HWND nextWindow;
	private static HWND foregroundWindow = User32.INSTANCE.GetForegroundWindow();
	
	public void setNextWindowAsForegroundWindow() {
		User32.INSTANCE.EnumWindows(new WNDENUMPROC() {
	        int handlesCount = -1; 
	        @Override
	        public boolean callback(HWND hWnd, Pointer arg1) {
	        	char[] buffer = new char[1024 * 2];
	            User32.INSTANCE.GetWindowText(hWnd, buffer, 512);
	            String nameOfApplication = Native.toString(buffer);
	            
	            if (nameOfApplication.isEmpty()) {
	               return true;
	            }
	            else {
	            	handlesCount++;
	            	runningApplicationHandles[handlesCount] = hWnd;
	            }
	            
	            if(handlesCount==0) {
	            }
	            else if(runningApplicationHandles[handlesCount-1].equals(foregroundWindow)) {
	            	nextWindow = hWnd;
	            }
	            return true;
	         }
	      }, null);
		
		User32.INSTANCE.SetForegroundWindow(nextWindow);
	}
}