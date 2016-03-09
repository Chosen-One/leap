package MainPackage;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class configClass {
	
	private String configLocation = System.getProperty("user.dir")+"\\.config";
	
	public boolean isEmulatingMouse() {
		try {
			Stream<String> lines;
			lines = Files.lines(Paths.get(configLocation));
			Optional<String> emulateMouse = lines.filter(line -> line.contains("emulate_mouse")).findFirst();
			if(emulateMouse.isPresent()) {
				String value = emulateMouse.get();
				if(value.contains("true")) {
					lines.close();
					return true;
				}
			}
			lines.close();
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	public void setMouseEmulation(boolean mouseEmulation) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(configLocation));
				lines.remove(0);
				if(mouseEmulation)
					lines.add(0, "emulate_mouse=true");
				else
					lines.add(0, "emulate_mouse=false");
				Files.write(Paths.get(configLocation), lines);
			} catch (IOException e) {
			}
	}
	
	public boolean isCircleDefault() {
		try {
			Stream<String> lines;
			lines = Files.lines(Paths.get(configLocation));
			Optional<String> clockwiseToScrollDown = 
					lines.filter(line -> line.contains("circle_default")).findFirst();
			if(clockwiseToScrollDown.isPresent()) {
				String value = clockwiseToScrollDown.get();
				if(value.contains("true")) {
					lines.close();
					return true;
				}
			}
			lines.close();
			return false;
		} catch (IOException e) {
			return true;
		}
	}
	
	public void setCircle(boolean circle) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(configLocation));
			lines.remove(1);
			if(circle)
				lines.add(1, "circle_default=true");
			else
				lines.add(1, "circle_default=false");
			Files.write(Paths.get(configLocation), lines);
		} catch (IOException e) {
		}
	}
	
	public boolean isSwipeDefault() {
		try {
			Stream<String> lines;
			lines = Files.lines(Paths.get(configLocation));
			Optional<String> clockwiseToScrollDown = 
					lines.filter(line -> line.contains("swipe_default")).findFirst();
			if(clockwiseToScrollDown.isPresent()) {
				String value = clockwiseToScrollDown.get();
				if(value.contains("true")) {
					lines.close();
					return true;
				}
			}
			lines.close();
			return false;
		} catch (IOException e) {
			return true;
		}
	}
	
	public void setSwipe(boolean swipe) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(configLocation));
			lines.remove(2);
			if(swipe)
				lines.add(2, "swipe_default=true");
			else
				lines.add(2, "swipe_default=false");
			Files.write(Paths.get(configLocation), lines);
		} catch (IOException e) {
		}
	}
}
