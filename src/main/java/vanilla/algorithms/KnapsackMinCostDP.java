package main.java.vanilla.algorithms;

import java.util.ArrayList;

import main.java.vanilla.beans.knapsack.Item;
import main.java.vanilla.beans.knapsack.Knapsack;

/**
 * Algorithm to solve Maximum 0-1 Knapsack
 * 
 * @author An Phi
 *
 */
public class KnapsackMinCostDP {

	/**
	 * Method to solve Maximum 0-1 Knapsack based on minimum cost dynamic
	 * programming approach. Do not support returning the items taken.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @return the maximum value can be achieved
	 */
	public static int solve(Knapsack<Integer> knapsack) {
		ArrayList<Item<Integer>> itemTaken = new ArrayList<>();
		return solve(knapsack, itemTaken);
	}

	/**
	 * Method to solve Maximum 0-1 Knapsack based on minimum cost dynamic
	 * programming approach. Potentially support returning the items taken.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @param itemTaken
	 *            the list of chosen items
	 * @return the maximum value can be achieved
	 */
	public static int solve(Knapsack<Integer> knapsack, ArrayList<Item<Integer>> itemTaken) {
		int itemListSize = knapsack.getItemListSize();
		int maxCost = knapsack.getMaxCost();
		int maxVal = knapsack.getMaxValue();

		int[][] table = new int[itemListSize][itemListSize * maxVal + 1];

		// base case: no cost incurred when target is 0
		// when initialized, the table already assign these to 0
		for (int i = 0; i < itemListSize; i++) {
			table[i][0] = 0;
		}

		// base case: if target can be achieved by taking the first item
		for (int value = 1; value <= knapsack.getItem(0).getValue(); value++) {
			table[0][value] = knapsack.getItem(0).getCost();
		}

		// base case: if target cannot be achieved by taking the first item
		// use a big value to represent infinity
		// to prevent overflow, subtract by the maximum cost
		for (int value = knapsack.getItem(0).getValue() + 1; value <= itemListSize * maxVal; value++) {
			table[0][value] = Integer.MAX_VALUE - maxCost;
		}

		for (int i = 1; i < itemListSize; i++) {
			for (int value = 1; value < itemListSize * maxVal + 1; value++) {
				Item<Integer> item = knapsack.getItem(i);
				int newTarget = Math.max(0, value - item.getValue());
				if (table[i - 1][value] <= table[i - 1][newTarget] + item.getCost()) {
					// skip item i
					table[i][value] = table[i - 1][value];
				} else {
					// take item i
					table[i][value] = table[i - 1][newTarget] + item.getCost();
				}
			}
		}

		// search the last row for the highest value that can be achieved
		// with cost less or equal to the budget
		int result = -1;
		for (int value = 0; value < itemListSize * maxVal + 1; value++) {
			if (table[itemListSize - 1][value] > knapsack.getBudget()) {
				result = value - 1;
				break;
			}
		}
		return result;
	}

}
