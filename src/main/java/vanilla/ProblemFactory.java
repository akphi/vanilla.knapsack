package main.java.vanilla;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import main.java.vanilla.Config;
import main.java.vanilla.Utils;
import main.java.vanilla.beans.knapsack.Item;
import main.java.vanilla.beans.knapsack.IntegerItem;
import main.java.vanilla.beans.knapsack.Knapsack;
import main.java.vanilla.beans.knapsack.IntegerKnapsack;
import main.java.vanilla.beans.threesat.Clause;
import main.java.vanilla.beans.threesat.Literal;
import main.java.vanilla.beans.threesat.ThreeSAT;

/**
 * Generate instances of different problems
 * 
 * @author An Phi
 *
 */
public class ProblemFactory {

	private static ProblemFactory instance;
	private static Properties props;
	private static Random rand = new Random();

	/**
	 * Constructor
	 */
	private ProblemFactory() {
		props = Config.getInstance().getConfig();
		long randomSeed = Long.parseLong(props.getProperty("RANDOM_SEED"));
		if (randomSeed != -1) {
			rand.setSeed(randomSeed);
		}

	}

	/**
	 * Method to get the singleton of problem generator
	 * 
	 * @return instance of problem factory
	 */
	public static synchronized ProblemFactory getInstance() {
		if (instance == null) {
			instance = new ProblemFactory();
		}
		return instance;
	}

	/**
	 * Method to generate a Knapsack problem instance with integer items
	 * 
	 * @param sizeItemList
	 *            number of item
	 * @param maxItemValue
	 *            maximum value of any item
	 * @param maxItemCost
	 *            maximum cost of any item
	 * @return a Knapsack problem instance
	 */
	public Knapsack<Integer> generateIntegerKnapsack(int sizeItemList, int maxItemValue, int maxItemCost) {
		Knapsack<Integer> knapsack = new IntegerKnapsack();
		for (int i = 0; i < sizeItemList; i++) {
			Item<Integer> item = new IntegerItem();
			item.setCost(1 + rand.nextInt(maxItemCost));
			item.setValue(1 + rand.nextInt(maxItemValue));
			knapsack.addItem(item);
		}
		// set budget and target value
		// target value is in range [0, total value]
		knapsack.setTarget(rand.nextInt(knapsack.getValue() + 1));
		// budget must be in range [maximum cost for an item, total cost]
		knapsack.setBudget(rand.nextInt(knapsack.getCost() - knapsack.getMaxCost() + 1) + knapsack.getMaxCost());
		return knapsack;
	}

	/**
	 * Method to generate a Knapsack problem instance with integer items. This
	 * is a wrapper for the actual method. Values specified in the configuration
	 * file are used for the parameters.
	 * 
	 * @return a Knapsack problem instance
	 */
	public Knapsack<Integer> generateKnapsack() {
		return generateIntegerKnapsack(Integer.parseInt(props.getProperty("KNAPSACK_MIN_ITEM_LIST_SIZE")),
				Integer.parseInt(props.getProperty("KNAPSACK_ITEM_VALUE_MAX")),
				Integer.parseInt(props.getProperty("KNAPSACK_ITEM_COST_MAX")));
	}

	/**
	 * Generate a 3SAT problem instance. In this method, we make sure one
	 * literal cannot appear twice in the same clause.
	 * 
	 * @param numClause
	 *            number of clause for the problem
	 * @return a 3SAT problem instance
	 */
	public ThreeSAT generate3SAT(int numClause) {
		ArrayList<Integer> usedLiteralIndexList = new ArrayList<Integer>();
		ArrayList<Clause> clauseList = new ArrayList<Clause>();

		for (int i = 0; i < numClause; i++) {
			Clause clause = new Clause();

			while (clause.size() < 3) {
				Literal literal = new Literal();
				if (Utils.generateBoolean(rand) && usedLiteralIndexList.size() > 0) {
					// reuse a variable
					literal.setId(usedLiteralIndexList.get(rand.nextInt(usedLiteralIndexList.size())));
					literal.setNegation(Utils.generateBoolean(rand));
				} else {
					// create a new variable
					literal.setId(usedLiteralIndexList.size());
					usedLiteralIndexList.add(literal.getId());
					literal.setNegation(Utils.generateBoolean(rand));
				}
				// make sure the literal is unique
				if (!clause.containLiteral(literal)) {
					clause.addLiteral(literal);
				}
			}
			clauseList.add(clause);
		}
		return new ThreeSAT(clauseList, usedLiteralIndexList.size());
	}

	/**
	 * Wrapper method to generate 3SAT problem instance that uses values in the
	 * configuration file
	 * 
	 * @return a3SAT problem instance
	 */
	public ThreeSAT generate3SAT() {
		return generate3SAT(Integer.parseInt(props.getProperty("3SAT_MAX_CLAUSE_NUMBER")));
	}
}
