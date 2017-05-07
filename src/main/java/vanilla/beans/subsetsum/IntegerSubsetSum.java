package main.java.vanilla.beans.subsetsum;

import java.util.ArrayList;

/**
 * Bean for subsetsum problem with integer numbers.
 * 
 * @author An Phi
 *
 */
public class IntegerSubsetSum implements SubsetSum<Integer> {

	ArrayList<Integer> set = null;
	Integer target = null;

	public IntegerSubsetSum() {}
	
	public IntegerSubsetSum(ArrayList<Integer> numbers, Integer target) {
		this.set = numbers;
		this.target = target;
	}

	public ArrayList<Integer> getSet() {
		return set;
	}

	public void setSet(ArrayList<Integer> set) {
		this.set = set;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	@Override
	public String toString() {
		// for a more structural output
		return "SubsetSum [set=" + set + ", target=" + target + "]";
	}

}
