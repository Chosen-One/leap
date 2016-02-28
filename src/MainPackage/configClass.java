package MainPackage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class configClass {

	public static boolean emulateMouse() throws IOException{
		Stream<String> lines = Files.lines(Paths.get(System.getProperty("user.dir")+"\\.config"));
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
	}
}
