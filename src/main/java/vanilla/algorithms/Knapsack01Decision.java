package main.java.vanilla.algorithms;

import main.java.vanilla.beans.knapsack.Knapsack;

/**
 * Algorithm to solve Decision 0-1 Knapsack
 * 
 * @author An Phi
 *
 */
public class Knapsack01Decision {

	/**
	 * Method to solve Decision 0-1 Knapsack based on the dynamic programming
	 * methods used to solve the Maximum 0-1 Knapsack problem.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @param algorithm
	 *            the dynamic programming algorithm used
	 * @return the result to indicate if there is an way of picking item that
	 *         satisfies the problem target and budget
	 */
	public static boolean decide(Knapsack<Integer> knapsack, int algorithm) {
		int maxValue = -1;
		switch (algorithm) {
		case 1:
			maxValue = KnapsackMaxValueDP.solve(knapsack);
			break;
		case 2:
		default:
			maxValue = KnapsackMinCostDP.solve(knapsack);
			break;
		}
		// check if target is reached
		if (maxValue >= (int) knapsack.getTarget()) {
			return true;
		}
		return false;
	}

}
