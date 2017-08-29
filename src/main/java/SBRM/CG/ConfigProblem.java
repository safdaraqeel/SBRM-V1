package SBRM.CG;

import java.io.File;
import java.util.List;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.encodings.solutionType.ArrayIntSolutionType;
import jmetal.encodings.solutionType.ArrayRealSolutionType;
import jmetal.encodings.solutionType.BinaryRealSolutionType;
import jmetal.encodings.solutionType.IntSolutionType;
import jmetal.encodings.solutionType.RealSolutionType;
import jmetal.util.JMException;
import jmetal.util.wrapper.XInt;

/**
 * Class representing problem ZDT1
 */
public class ConfigProblem extends Problem {
	public  List<CPLRules> rulesList;

	/**
	 * Constructor. Creates a default instance of problem ZDT1 (30 decision
	 * variables)
	 * 
	 * @param solutionType
	 *            The solution type must "Real", "BinaryReal, and "ArrayReal".
	 *            ArrayReal, or ArrayRealC".
	 */
	public ConfigProblem(String solutionType) throws ClassNotFoundException {
		this(solutionType, numberOfVariables_);

	} 

	public ConfigProblem(String solutionType, Integer numberOfVariables) {
		
		String filePath = "AllRules.xlsx";
		if (!new File (filePath).exists()){
//			filePath = "ExampleFiles/ZeroIteration/PART/InitialRules.xlsx";
			filePath = "ExampleFiles/ZeroIteration/C45/InitialRules.xlsx";
		}
		this.rulesList = ReadCPLRulesFromXLFile.getRulesFromXLFile(filePath);

		numberOfVariables_ = numberOfVariables;
		numberOfObjectives_ = 3;
		numberOfConstraints_ = 0;
		problemName_ = "ConfigProblem";

		upperLimit_ = new double[numberOfVariables_];
		lowerLimit_ = new double[numberOfVariables_];

		/*
		 Configurable Parameters for Product-1:
		 p1_cp1 = {SIP,AIM,GoogleTalk,ICQ,iPPi,iptel.org,IRC, H323} //i.e., Default
		 Call Protocol
		 p1_cp2 = {on, off} //i.e., Listen Port for SIP Protocol
		 p1_cp3 = {Auto, UDP,TCP,TLS} //i.e., Default Transport Protocol
		 p1_cp4 = {on, off, bestEffort} //i.e., Encryption
		 p1_cp5 = {Auto, true, false} //i.e.,sipZrtpAttribute
		 p1_cp6 =
		 {Auto,opus-48000,SILK-24000,SILK-16000,G722-16000,speex-32000,speex-16000,PCMU-8000,PCMA-8000,iLBC-8000,GSM-8000,speex-8000,AMR-WB-16000,SILK-12000,SILK-8000,telephone-event-80000}
		 //i.e., Audio Codec
		 p1_cp7 = {Auto, h264,red,rtx,ulpfec,VP8} //i.e., Video Codec
		 p1_cp8 = {1080,720,480,360,240} //i.e., Resolution
		 p1_cp9 = {on, off} //i.e., Sip Mode
		 p1_cp10 = {on, off} //i.e., H323 Mode
		 p1_cp11 = 500 <= x<= 1500 //i.e., MTU- Network Max Transmission Unit
		 p1_cp12 = 50 <= x<= 500 //i.e., Default Call Rate
		 p1_cp13 = 50 <= x<= 500 //i.e., Max Received Call Rate
		 p1_cp14 = 50 <= x<= 500 //i.e., Max Transmited Call Rate

		 Configurable Parameters for Product-2:
		 p2_cp1 = {SIP,AIM,GoogleTalk,ICQ,iPPi,iptel.org,IRC,H323} //i.e., Default
		 Call Protocol
		 p2_cp2 = {on, off} //i.e., Listen Port for SIP Protocol
		 p2_cp3 = {Auto, UDP,TCP,TLS} //i.e., Default Transport Protocol
		 p2_cp4 = {on, off, bestEffort} //i.e., Encryption
		 p2_cp5 = {Auto, true, false} //i.e.,sipZrtpAttribute
		 p2_cp6 =
		 {Auto,opus-48000,SILK-24000,SILK-16000,G722-16000,speex-32000,speex-16000,PCMU-8000,PCMA-8000,iLBC-8000,GSM-8000,speex-8000,AMR-WB-16000,SILK-12000,SILK-8000,telephone-event-80000}
		 //i.e., Audio Codec
		 p2_cp7 = {Auto, h264,red,rtx,ulpfec,VP8} //i.e., Video Codec
		 p2_cp8 = {1080,720,480,360,240} //i.e., Resolution
		 p2_cp9 = {on, off} //i.e., Sip Mode
		 p2_cp10 = {on, off} //i.e., H323 Mode
		 p2_cp11 = 500 <= x<= 1500 //i.e., MTU- Network Max Transmission Unit
		 p2_cp12 = 50 <= x<= 5000 //i.e., Default Call Rate
		 p2_cp13 = 50 <= x<= 5000 //i.e., Max Received Call Rate
		 p2_cp14 = 50 <= x<= 5000 //i.e., Max Transmited Call Rate

		 ***Variables are encoded as integer and their domains are set***
		
		 * Configurable Parameters for Product-1 
		 * p1_cp1 = 0 <= x<= 7 
		 * p1_cp2 = 0 <= x<= 1
		 * p1_cp3 = 0 <= x<= 3
		 * p1_cp4 = 0 <= x<= 2
		 * p1_cp5 = 0 <= x<= 2
		 * p1_cp6 = 0 <= x<= 15
		 * p1_cp7 = 0 <= x<= 5
		 * p1_cp8 = 0 <= x<= 4 
		 * p1_cp9 = 0 <= x<= 1 
		 * p1_cp10 = 0 <= x<= 1 
		 * p1_cp11 = 500 <= x<= 1500
		 * p1_cp12 = 50 <= x<= 5000
		 * p1_cp13 = 50 <= x<= 5000 
		 * p1_cp14 = 50 <= x<= 5000
		 * 
		 * Configurable Parameters for Product-2: 
		 * p2_cp1 = 0 <= x<= 7 
		 * p2_cp2 = 0 <= x<= 1
		 * p2_cp3 = 0 <= x<= 3 
		 * p2_cp4 = 0 <= x<= 2 
		 * p2_cp5 = 0 <= x<= 2 
		 * p2_cp6 = 0 <= x<= 15 
		 * p2_cp7 = 0 <= x<= 5
		 * p2_cp8 = 0 <= x<= 4 
		 * p2_cp9 = 0 <= x<= 1 
		 * p2_cp10 = 0 <= x<= 1 
		 * p2_cp11 = 500 <= x<= 1500
		 * p2_cp12 = 50 <= x<= 5000 
		 * p2_cp13 = 50 <= x<= 5000 
		 * p2_cp14 = 50 <= x<= 5000
		 */

		// set the upper and lower limits of all configurable parameters, upper
		// and lower limits are included in the domain .
		for (int var = 0; var < numberOfVariables_; var++) {
			if (var == 1 || var == 8 || var == 9 || var == 15 || var == 22 || var == 23) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 1;
			} else if (var == 3 || var == 4 || var == 17 || var == 18) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 2;
			} else if (var == 2 || var == 16) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 3;
			} else if (var == 7 || var == 21) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 4;
			} else if (var == 6 || var == 20) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 5;
			} else if (var == 0 || var == 14) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 7;
			} else if (var == 5 || var == 19) {
				lowerLimit_[var] = 0;
				upperLimit_[var] = 15;
			} else if (var == 10 || var == 24) {
				lowerLimit_[var] = 499;
				upperLimit_[var] = 1499;
			} else if (var == 11 || var == 12 || var == 13 || var == 25 || var == 26 || var == 27) {
				lowerLimit_[var] = 49;
				upperLimit_[var] = 4999;
			}
		} // for

		if (solutionType.compareTo("Integer") == 0)
			solutionType_ = new IntSolutionType(this);
		else if (solutionType.compareTo("Real") == 0)
			solutionType_ = new RealSolutionType(this);
		else if (solutionType.compareTo("ArrayReal") == 0)
			solutionType_ = new ArrayRealSolutionType(this);
		else if (solutionType.compareTo("BinaryReal") == 0)
			solutionType_ = new BinaryRealSolutionType(this);
		else if (solutionType.compareTo("IntegerArray") == 0)
			solutionType_ = new ArrayIntSolutionType(this);
		else {
			System.out.println("Error: solution type " + solutionType + " invalid");
			System.exit(-1);
		}
	} // ConfigProblem

	/**
	 * Evaluates a solution.
	 * 
	 * @param solution
	 *            The solution to evaluate.
	 * @throws JMException
	 */
	public void evaluate(Solution solution) throws JMException {
		XInt x = new XInt(solution);
		double[] f = new double[numberOfObjectives_];
		double f1 = this.fitnessForobjective1(x);
		double f2 = this.fitnessForobjective2(x);
		double f3 = this.fitnessForobjective3(x);
		f[0] = f1;
		f[1] = f2;
		f[2] = f3;
		solution.setObjective(0, f[0]);
		solution.setObjective(1, f[1]);
		solution.setObjective(2, f[2]);
	} // evaluate

	
	
	private double fitnessForobjective1(XInt x) throws JMException {
		// initializing a configuration object with solution
		EncodedConfigurableParameters configuration = new EncodedConfigurableParameters();
		configuration = configuration.intializeConfigurationInstance(x);
		// calculating distances for all rules for the current solution
		this.rulesList = DistanceCalculation.CalculateDistanceForAllRules(this.rulesList, configuration);
		return Objective1.CalculateFitnessForExploringAwayFromHighConfidenceConnected(this.rulesList);
	}

	private double fitnessForobjective2(XInt x) throws JMException {
		return Objective2.CalculateFitnessForExploringNearLowConfidenceConnected(this.rulesList);
	} 

	private double fitnessForobjective3(XInt x) throws JMException {
		return Objective3.CalculateFitnessForExploringNearFailed(this.rulesList);
	} 

}