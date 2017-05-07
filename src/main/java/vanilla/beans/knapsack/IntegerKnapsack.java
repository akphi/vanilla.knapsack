package main.java.vanilla.beans.knapsack;

import java.util.ArrayList;

/**
 * Bean for Knapsack problem with integer item, budget and target value.
 * 
 * @author An Phi
 *
 */
public class IntegerKnapsack implements Knapsack<Integer> {
	ArrayList<Item<Integer>> itemList;
	Integer budget;
	Integer target;
	Integer value = 0;
	Integer cost = 0;

	public IntegerKnapsack() {
		itemList = new ArrayList<Item<Integer>>();
		value = 0;
	}

	public IntegerKnapsack(ArrayList<Item<Integer>> items, Integer budget, Integer target) {
		this.itemList = items;
		this.budget = budget;
		this.target = target;
		for (Item<Integer> item : items) {
			value += item.getValue();
			cost += item.getCost();
		}
	}

	public Integer getBudget() {
		return budget;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public void addItem(Item<Integer> item) {
		itemList.add(item);
		value += item.getValue();
		cost += item.getCost();
	}

	public Item<Integer> getItem(int index) {
		if (index < itemList.size()) {
			Item<Integer> item = itemList.get(index);
			return item;
		}
		return null;
	}

	public void setItemList(ArrayList<Item<Integer>> items) {
		this.itemList = items;
		for (Item<Integer> i : items) {
			value += i.getValue();
			cost += i.getCost();
		}
	}

	public ArrayList<Item<Integer>> getItemList() {
		value = 0;
		return itemList;
	}

	public int getItemListSize() {
		return itemList.size();
	}

	public Integer getValue() {
		return value;
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getMaxValue() {
		int maxVal = 0;
		for (Item<Integer> item : itemList) {
			if (item.getValue() > maxVal) {
				maxVal = item.getValue();
			}
		}
		return maxVal;
	}

	public Integer getMaxCost() {
		int maxCost = 0;
		for (Item<Integer> item : itemList) {
			if (item.getCost() > maxCost) {
				maxCost = item.getCost();
			}
		}
		return maxCost;
	}

	@Override
	public String toString() {
		String toPrint = "";
		toPrint += "Knapsack\nBudget: " + budget + "\nTarget: " + target + "\nMax Value: " + this.getMaxValue()
				+ "\nItem:\n";
		for (int i = 0; i < itemList.size(); i++) {
			toPrint += "  " + itemList.get(i) + "\n";
		}
		return toPrint;
		// for a more structural output
		// return "Knapsack [ budget=" + budget + ", target=" + target + ",
		// value=" + value + "\nitems="
		// + itemList.toString() + "]";
	}

}
