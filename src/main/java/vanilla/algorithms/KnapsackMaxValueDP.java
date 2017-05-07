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
public class KnapsackMaxValueDP {

	/**
	 * Method to solve Maximum 0-1 Knapsack based on maximum value dynamic
	 * programming approach. Do not support returning the items taken.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @return the maximum value can be achieved
	 */
	public static int solve(Knapsack<Integer> knapsack) {
		ArrayList<Item<Integer>> itemTakenList = new ArrayList<>();
		return solve(knapsack, itemTakenList);
	}

	/**
	 * Method to solve Maximum 0-1 Knapsack based on maximum value dynamic
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
		int maxBudget = knapsack.getBudget();

		// every cells are automatically initialized to 0
		// this handles the base case as well
		int[][] table = new int[itemListSize + 1][maxBudget + 1];

		for (int i = 1; i <= itemListSize; i++) {
			for (int budget = 1; budget <= maxBudget; budget++) {
				Item<Integer> item = knapsack.getItem(i - 1);
				if (item.getCost() > budget) {
					// skip this item due to high cost
					table[i][budget] = table[i - 1][budget];
				} else {
					table[i][budget] = Math.max(table[i - 1][budget - item.getCost()] + item.getValue(),
							table[i - 1][budget]);
				}
			}
		}

		return table[itemListSize][maxBudget];
	}

}
