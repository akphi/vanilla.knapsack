package main.java.vanilla.beans.knapsack;

/**
 * Bean for item with integer value and cost.
 * 
 * @author An Phi
 *
 */
public class IntegerItem implements Item<Integer> {
	Integer value;
	Integer cost;

	public IntegerItem() {
	}

	public IntegerItem(Integer value, Integer cost) {
		this.value = value;
		this.cost = cost;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Value: " + value + ", Cost: " + cost;
		// for a more structural output
		// return "Item [value=" + value + ", cost=" + cost + "]";
	}

	@Override
	public boolean equals(Object object) {
		boolean isSame = false;
		if (object != null && object instanceof IntegerItem) {
			IntegerItem objectItem = (IntegerItem) object;
			isSame = (this.value == objectItem.getValue()) && (this.cost == objectItem.getCost());
		}
		return isSame;
	}
}
