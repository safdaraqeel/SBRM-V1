package SBRM.Main;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import SBRM.CG.Decoding;
import SBRM.CG.InitialRandomConfigurationGeneration;
import SBRM.PIS.SimulatingInteraction;
import SBRM.RM.MineRulesUsingC45;
import SBRM.RM.MineRulesUsingPart;
import SBRM.RM.WriteRulesToXLFile;
import SBRM.Utility.ConfigurationFileManipulation;
import SBRM.Utility.MergeTwoFiles;
import jmetal.metaheuristics.nsgaII.NSGAII_main;

public class Main {
	
	public static void mineInitialRuleSet() {
		try {
			
			String mlAlgorithm="PART";
//			String mlAlgorithm="C45";
			String	baseFolder="ExampleFiles/ZeroIteration/";
			// Initial random configurations are generated (decoded ones) 
			
			if (!new File (baseFolder+"InitialConfigurations.csv").exists()){
				InitialRandomConfigurationGeneration.getInitialDecodedRandomConfigurations(baseFolder+"InitialConfigurations.csv", 2000);
				// states should be assigned to all configurations by making the products interact through executing certain functionalities of products 
				// In this example, we simulate. We get states based on rules defined on configurations
				SimulatingInteraction.captureSystemStates(baseFolder+"InitialConfigurations.csv",baseFolder+"InitialConfigurationsWithStates.csv") ;
				}
			// Mining rules using a rule mining algorithm (e.g., C4.5 , PART)
        	if (mlAlgorithm.equals("PART")){	 // Using PART
        		MineRulesUsingPart.mine(baseFolder+"InitialConfigurationsWithStates.csv", baseFolder+"PART/Rules.txt");
        		WriteRulesToXLFile.ExtractRules(baseFolder+"PART/Rules.txt",baseFolder+"PART/InitialRules.xlsx","Zero");
        	}
        	if (mlAlgorithm.equals("C45")){	// Using C45
        		MineRulesUsingC45.mine(baseFolder+"InitialConfigurationsWithStates.csv", baseFolder+"C45/C45Result.txt", baseFolder+"C45/Rules.txt");
        		WriteRulesToXLFile.ExtractRules(baseFolder+"C45/Rules.txt",baseFolder+"C45/InitialRules.xlsx","Zero");
        	}
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void refineRuleSet()
    {
		try {	
      
        // parameters for experiment runs and iterations
//      String mlAlgorithm="PART";
        String mlAlgorithm="C45";
        int totalIterations=5;
        int requiredPopulation=500;
        int evaluations=50000;
        int population=500;
        String [] para= {Integer.toString(population),Integer.toString(evaluations)};
        for (int iterationIndex=1;iterationIndex<=totalIterations;iterationIndex++){
        	    // Generating ConfigurationData
	        	NSGAII_main.main(para);
		        int count= ConfigurationFileManipulation.countConfigurations("VAR");
		        population=requiredPopulation-count;
		        while(population>0){
		            para[1]= Integer.toString(population);
		            NSGAII_main.main(para);
		        }
		       //Removing extra configurations if there are any
		        ConfigurationFileManipulation.removeExtraConfigurations("VAR",requiredPopulation);
		        ConfigurationFileManipulation.removeExtraConfigurations("FUN",requiredPopulation);
		        
		        //for converting integer variables to categorical
		        Decoding.DecodeAllConfigurationsInFile("VAR" ,"Configurations.csv") ;
		
		     // states should be assigned to all configurations by making the products interact through executing certain functionalities of products 
				// In this example, we simulate. We get states based on rules defined on configurations
				SimulatingInteraction.captureSystemStates("Configurations.csv","ExecutedConfigurations.csv") ;

				//Copy The Data Into Existing Data
				if (iterationIndex==1){
					FileUtils.copyFile(new File("ExampleFiles/ZeroIteration/InitialConfigurationsWithStates.csv"), new File("AllExecutedConfigurations.csv"));
//					Files.copy(Paths.get(), Paths.get());
				}
	        	MergeTwoFiles.merge("ExecutedConfigurations.csv","AllExecutedConfigurations.csv"); 

		    	// Mining rules using a rule mining algorithm (e.g., C4.5 , PART)
		    	 String sheetName= "Iteration-"+iterationIndex;
	        	if (mlAlgorithm.equals("PART")){	 // Using PART
	        		MineRulesUsingPart.mine("AllExecutedConfigurations.csv", "Rules.txt");
	        		WriteRulesToXLFile.ExtractRules("Rules.txt","AllRules.xlsx", sheetName);
	        	}
	        	if (mlAlgorithm.equals("C45")){	// Using C45
	        		MineRulesUsingC45.mine("AllExecutedConfigurations.csv", "C45Result.txt", "Rules.txt");
	        		WriteRulesToXLFile.ExtractRules("Rules.txt","AllRules.xlsx", sheetName);
	        	}
	   
		    	//Moving files to relevant folders
				File dest = new File("ExampleFiles/CompleteRun/"+"Iteration "+(iterationIndex)+"/"+mlAlgorithm+"/");
		    	FileUtils.moveFileToDirectory(new File("VAR"), dest, true);
		    	FileUtils.moveFileToDirectory(new File("FUN"), dest, true);
		    	FileUtils.moveFileToDirectory(new File("Configurations.csv"), dest, true);
		    	FileUtils.moveFileToDirectory(new File("ExecutedConfigurations.csv"), dest, true);
		    	FileUtils.moveFileToDirectory(new File("Rules.txt"), dest, true);
				if (mlAlgorithm.equals("C45")){	// moving C45Result.txt file
					FileUtils.moveFileToDirectory(new File("C45Result.txt"), dest, true);
		        	}
        	 }// end of iterations
        		File dest = new File("ExampleFiles/CompleteRun/AllRules/"+mlAlgorithm+"/");
        	 	FileUtils.moveFileToDirectory(new File("AllRules.xlsx"), dest, true);
        	 	dest = new File("ExampleFiles/CompleteRun/AllConfigurations/"+mlAlgorithm+"/");
        	 	FileUtils.moveFileToDirectory(new File("AllExecutedConfigurations.csv"), dest, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws IOException {
		
		mineInitialRuleSet();
		refineRuleSet( );
	
	}

}
