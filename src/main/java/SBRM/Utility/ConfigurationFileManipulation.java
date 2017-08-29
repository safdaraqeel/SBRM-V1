package SBRM.Utility;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ConfigurationFileManipulation {

	public static int countConfigurations(String filePath){
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines.size();
	}
	
	public static void removeExtraConfigurations(String filePath, int requiredPopulation){
		int totalConfiguraitons=0;
		String configurations = "";
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
			for (String line : lines) {
				totalConfiguraitons++;
				if (totalConfiguraitons<=requiredPopulation){
					configurations+=line+"\n";
				}	
			}
	    	// writing again
			Files.write(Paths.get(filePath), configurations.getBytes());			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	
	
}
