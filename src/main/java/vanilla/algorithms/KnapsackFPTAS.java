package main.java.vanilla.algorithms;

import java.util.ArrayList;

import main.java.vanilla.beans.knapsack.Item;
import main.java.vanilla.beans.knapsack.Knapsack;
import main.java.vanilla.beans.knapsack.IntegerItem;
import main.java.vanilla.beans.knapsack.IntegerKnapsack;

/**
 * Algorithm to solve Maximum 0-1 Knapsack
 * 
 * @author An Phi
 *
 */
public class KnapsackFPTAS {

	/**
	 * Method to solve Maximum 0-1 Knapsack based on fully polynomial time
	 * approximation scheme approach. Do not support returning the items taken.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @param scaleFactor
	 *            epsilon indicates the error allowed
	 * @return the maximum value can be achieved
	 */
	public static int solve(Knapsack<Integer> knapsack, double epsilon) {
		ArrayList<Item<Integer>> itemTaken = new ArrayList<Item<Integer>>();
		return solve(knapsack, epsilon, itemTaken);
	}

	/**
	 * Method to solve Maximum 0-1 Knapsack based on fully polynomial time
	 * approximation scheme approach. Potentially support returning the items taken.
	 * 
	 * @param knapsack
	 *            the Knapsack instance
	 * @param scaleFactor
	 *            epsilon indicates the error allowed
	 * @param itemTaken
	 *            the list of chosen items
	 * @return the maximum value can be achieved
	 */
	public static int solve(Knapsack<Integer> knapsack, double epsilon, ArrayList<Item<Integer>> itemTaken) {
		ArrayList<Item<Integer>> scaledItems = new ArrayList<Item<Integer>>();
		double scalingFactor = epsilon * knapsack.getMaxValue() / knapsack.getItemListSize();

		for (Item<Integer> item : knapsack.getItemList()) {
			Item<Integer> scaledItem = new IntegerItem();
			scaledItem.setValue((int) Math.floor(item.getValue() / scalingFactor));
			scaledItem.setCost(item.getCost());
			scaledItems.add(scaledItem);
		}

		Knapsack<Integer> scaledKnapsack = new IntegerKnapsack();
		scaledKnapsack.setItemList(scaledItems);
		scaledKnapsack.setBudget(knapsack.getBudget());
		scaledKnapsack.setTarget(knapsack.getTarget());

		// rescale the optimal value
		int result = KnapsackMinCostDP.solve(scaledKnapsack);
		return (int) Math.floor(result * scalingFactor);
	}

}
