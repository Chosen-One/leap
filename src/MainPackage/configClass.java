package MainPackage;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class configClass {
	
	private String configLocation = System.getProperty("user.dir")+"\\.config";
	
	public boolean emulateMouse() {
		try {
			Stream<String> lines;
			lines = Files.lines(Paths.get(configLocation));
			Optional<String> emulateMouse = lines.filter(line -> line.contains("emulateMouse")).findFirst();
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
	
	public  void setMouseEmulation(boolean mouseEmulation) {
			try {
				List<String> lines = Files.readAllLines(Paths.get(configLocation));
				lines.remove(0);
				if(mouseEmulation)
					lines.add(0, "emulateMouse=true");
				else
					lines.add(0, "emulateMouse=false");
				Files.write(Paths.get(configLocation), lines);
			} catch (IOException e) {
			}
	}
}
