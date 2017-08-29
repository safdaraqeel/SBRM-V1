package SBRM.CG;

import java.util.Iterator;
import java.util.List;

import SBRM.CG.CPLRules;

public class Objective3 {
	public static double CalculateFitnessForExploringNearFailed(List<CPLRules> rulesList) {
		double sumOfWeightedDistance = 0;
		double maxWeightedDistance = 0;
		// Assuming confidence is already calculated and isNormal=true means
		// rule is associated with normal status
		for (Iterator iterator = rulesList.iterator(); iterator.hasNext();) {
			CPLRules rule = (CPLRules) iterator.next();
			if (!rule.isIsnormal()) {
				// rule.printRule();
				double weightedDistance = (rule.getConfidence() * (1 - rule.getDistance()));
				sumOfWeightedDistance += weightedDistance;
				// calculating the maximum weight by add all weights for all
				// connected status constraints
				maxWeightedDistance += rule.getConfidence();
			} // end of if
		} // end of for

		// Normalization of effectiveness measure using
		// (x-minimum/maximum-minimum) normalization function
		double normalizedSumOfWeightedDistance = (sumOfWeightedDistance / maxWeightedDistance);
		// double normalizedSumOfWeightedDistance =
		// sumOfWeightedDistance/(sumOfWeightedDistance+1); //(x/x+1)
		// normalization function
		// System.out.println("normalizedSumOfWeightedDistance is:-
		// "+normalizedSumOfWeightedDistance);
		double fitnessValue = 1 - normalizedSumOfWeightedDistance;
//		System.out.println("fitness value for exploring near failed ones is:- " + fitnessValue);
		return fitnessValue;
	}
}
