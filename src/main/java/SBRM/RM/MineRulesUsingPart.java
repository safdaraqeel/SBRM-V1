package SBRM.RM;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import weka.classifiers.rules.PART;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;



/**
 * Hello world!
 *
 */
public class MineRulesUsingPart {

	
	public static void mine(String sourceFilePath,String destinationFilePath){
		try {

			if (destinationFilePath.contains("/Rules.txt")){
				File file= new File(destinationFilePath.replaceAll("/Rules.txt", ""));
				if (!file.exists()){
					file.mkdir();
				}
			}
			
			DataManipulation.converCSV2ARFF(sourceFilePath,"Data.arff");
			PART obj = new PART();
			DataSource source = new DataSource("Data.arff");
			 Instances data = source.getDataSet();
//			 System.out.println(data);
//			 System.out.println("Total Attributes are:"+data.numAttributes());

			 if (data.classIndex() == -1)
			   data.setClassIndex(data.numAttributes() - 1);
	        	System.out.println(data.toSummaryString());

	        	// writing the rules to outputfile
	        	System.setOut(new PrintStream(new FileOutputStream(destinationFilePath)));
	        	 
	        	String[] argv = {"-t","Data.arff","-i","-x","10","-c","last"};
	        	//String[] argv = {"-t","AllData.arff"};
	            PART.main(argv);
	            System.setOut(System.out);
	            System.out.println("Mining process is finished");
	            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		mine("ConfigurationStatus.csv","constraints.txt");

	}
}
