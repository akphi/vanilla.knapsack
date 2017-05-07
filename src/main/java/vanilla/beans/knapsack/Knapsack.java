package main.java.vanilla.beans.knapsack;

import java.util.ArrayList;

/**
 * Interface for a Knapsack problem.
 * 
 * @author An Phi
 *
 * @param <T>
 *            generic type
 */
public interface Knapsack<T> {

	public T getBudget();

	public void setBudget(T budget);

	public T getTarget();

	public void setTarget(T target);

	public void addItem(Item<T> item);

	public Item<T> getItem(int index);

	public void setItemList(ArrayList<Item<T>> items);

	public ArrayList<Item<T>> getItemList();

	public int getItemListSize();

	public T getValue();

	public T getCost();

	public T getMaxValue();

	public T getMaxCost();

	@Override
	public String toString();
}
