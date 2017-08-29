package SBRM.CG;

import java.util.Iterator;
import java.util.List;

public class Objective1 {

	public static double CalculateFitnessForExploringAwayFromHighConfidenceConnected(List<CPLRules> rulesList) {
		double threshold = 0.9;
		double sumOfWeightedDistance = 0;
		double maxWeightedDistance = 0;

		// Assuming confidence is already calculated and isNormal=true means
		// rule is associated with normal status
		for (Iterator iterator = rulesList.iterator(); iterator.hasNext();) {
			CPLRules rule = (CPLRules) iterator.next();
			if (rule.isIsnormal()
					&& (rule.getConfidence() > threshold || (rule.getSupport() + rule.getViolation()) > 10)) {
				// rule.printRule();
				double weightedDistance = (rule.getConfidence() * rule.getDistance());
				sumOfWeightedDistance += weightedDistance;
			} // end of if
				// calculating the maximum weight by add all weights for all
				// connected status constraints
			if (rule.isIsnormal()) {
				maxWeightedDistance += rule.getConfidence();
			}
		} // end of iterator

		// Normalization of effectiveness measure using
		// (x-minimum/maximum-minimum) normalization function
		double normalizedSumOfWeightedDistance = (sumOfWeightedDistance / maxWeightedDistance);
		// double normalizedSumOfWeightedDistance =
		// sumOfWeightedDistance/(sumOfWeightedDistance+1); //(x/x+1)
		// normalization function
		// System.out.println("normalizedSumOfWeightedDistance is:-
		// "+normalizedSumOfWeightedDistance);
		double fitnessValue = 1 - normalizedSumOfWeightedDistance;
//		System.out.println("fitness value for away from the connected ones is:- " + fitnessValue);
		return fitnessValue;
	}

}
