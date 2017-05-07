package main.java.vanilla.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import main.java.vanilla.beans.knapsack.Item;
import main.java.vanilla.beans.knapsack.Knapsack;

/**
 * Algorithm to solve Maximum 0-1 Knapsack
 * 
 * @author An Phi
 *
 */
public class Knapsack2Approximation {

	/**
	 * Method to solve Maximum 0-1 Knapsack based on greedy 2-approximation
	 * approach. Do not support returning the items taken.
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
	 * Method to solve Maximum 0-1 Knapsack based on greedy 2-approximation
	 * approach. Potentially support returning the items taken.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @param itemTaken
	 *            the list of chosen items
	 * @return the maximum value can be achieved
	 */
	public static int solve(Knapsack<Integer> knapsack, ArrayList<Item<Integer>> itemTaken) {
		// greedy approach: sort items based on value:cost ratio
		Collections.sort(knapsack.getItemList(), new Comparator<Item<Integer>>() {
			public int compare(Item<Integer> x, Item<Integer> y) {
				return -1 * (new Double((double) x.getValue() / (double) x.getCost()))
						.compareTo(new Double((double) y.getValue() / (double) y.getCost()));
			}
		});

		int budget = knapsack.getBudget();

		for (int i = 0; i < knapsack.getItemListSize(); i++) {
			Item<Integer> item = knapsack.getItem(i);
			if (budget > 0) {
				if (item.getCost() <= budget) {
					itemTaken.add(item);
					budget -= item.getCost();
				}
			}
		}

		// assume that the cost of each item is less than the budget
		int maxValue = knapsack.getMaxValue();
		int currentTotalValue = 0;
		for (Item<Integer> item : itemTaken) {
			currentTotalValue += item.getValue();
		}

		return maxValue > currentTotalValue ? maxValue : currentTotalValue;
	}

}
