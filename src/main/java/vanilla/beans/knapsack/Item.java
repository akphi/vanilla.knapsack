package main.java.vanilla.beans.knapsack;

/**
 * Interface for an item.
 * 
 * @author An Phi
 *
 * @param <E>
 *            generic type
 */
public interface Item<E> {

	public E getValue();

	public void setValue(E value);

	public E getCost();

	public void setCost(E cost);

	@Override
	public String toString();

	@Override
	public boolean equals(Object object);
}
