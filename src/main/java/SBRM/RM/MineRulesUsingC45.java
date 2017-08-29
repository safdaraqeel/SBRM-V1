package SBRM.RM;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


public class MineRulesUsingC45 {

	public static void mineRules(String sourceFilePath, String destinationFilePath) {
		// Instances data ;//= new Instances();
		try {			
			DataManipulation.converCSV2ARFF(sourceFilePath, "Data.arff");
			DataSource source = new DataSource("Data.arff");
			Instances data = source.getDataSet();
//			System.out.println(data);
//			System.out.println("Total Attributes are:" + data.numAttributes());

			if (data.classIndex() == -1)
				data.setClassIndex(data.numAttributes() - 1);
			 System.out.println(data.toSummaryString());

			// writing the rules to outputfile
			System.setOut(new PrintStream(new FileOutputStream(destinationFilePath)));

			// Make tree
			J48 tree = new J48();
			String[] argv = { "-t", "Data.arff", "-i", "-x", "10", "-c", "last" };
			tree.setOptions(argv);
			tree.buildClassifier(data);
			tree.main(argv);
//			System.out.println(tree.graph().toString());
//			System.setOut(System.out);
			System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mine(String sourceFilePath, String destinationFilePath, String rulesDestinationFilePath) {
		try {

			// creating directory 
			if (rulesDestinationFilePath.contains("/Rules.txt")){
				File file= new File(rulesDestinationFilePath.replaceAll("/Rules.txt", ""));
				if (!file.exists()){
					file.mkdir();
				}
			}
			
			mineRules(sourceFilePath, destinationFilePath);
			String tree = new String(Files.readAllBytes(Paths.get(destinationFilePath)), StandardCharsets.UTF_8);
			ExtractRuleFromDescisionTree.extractRules(destinationFilePath, rulesDestinationFilePath);
			System.out.println("Mining process is finished");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		mine("AllData.csv", "J48Rules.txt", "constraint.txt");
	}

}
