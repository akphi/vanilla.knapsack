package main.java.vanilla.beans.subsetsum;

import java.util.ArrayList;

/**
 * Interface for a SubsetSum problem.
 * 
 * @author An Phi
 *
 * @param <T>
 *            generic type
 */
public interface SubsetSum<T> {

	public ArrayList<T> getSet();

	public void setSet(ArrayList<T> set);

	public T getTarget();

	public void setTarget(int target);

	@Override
	public String toString();

}
