package main.java.vanilla.algorithms;

import java.util.ArrayList;
import java.util.Arrays;

import main.java.vanilla.beans.knapsack.IntegerItem;
import main.java.vanilla.beans.knapsack.IntegerKnapsack;
import main.java.vanilla.beans.knapsack.Item;
import main.java.vanilla.beans.knapsack.Knapsack;
import main.java.vanilla.beans.subsetsum.IntegerSubsetSum;
import main.java.vanilla.beans.subsetsum.SubsetSum;
import main.java.vanilla.beans.threesat.Clause;
import main.java.vanilla.beans.threesat.Literal;
import main.java.vanilla.beans.threesat.ThreeSAT;

/**
 * Reduction from 3SAT to 1in3SAT, to SubsetSum, to Decision 0-1 Knapsack.
 * 
 * @author An Phi
 *
 */
public class Reduction {

	/**
	 * Method to reduce a 3SAT problem instance to a 1in3SAT problem instance.
	 * 
	 * @param ThreeSATInstance
	 *            the 3SAT problem instance
	 * @return the 1in3SAT problem instance
	 */
	public static ThreeSAT ThreeSAT_OneInThreeSAT(ThreeSAT instance) {
		ArrayList<Clause> result = new ArrayList<Clause>();
		ArrayList<Clause> clauseList = instance.getClauses();
		int literalCount = instance.getLiteralCount();
		boolean[] flipNegation = { true, false, true };

		for (Clause clause : clauseList) {
			Clause newClause;
			ArrayList<Literal> newLiterals = new ArrayList<Literal>();
			for (int i = 0; i < 4; i++) {
				Literal newLiteral = new Literal(literalCount++);
				newLiterals.add(newLiteral);
			}
			for (int i = 0; i < 3; i++) {
				newClause = new Clause();
				Literal origLiteral = clause.getLiteral(i);
				Literal newLiteral = new Literal(origLiteral.getId());
				if (flipNegation[i])
					newLiteral.flipNegation();
				newClause.addLiteral(newLiteral);
				newClause.addLiteral(newLiterals.get(i));
				newClause.addLiteral(newLiterals.get(i + 1));
				result.add(newClause);
			}
		}
		return new ThreeSAT(result, literalCount);
	}

	/**
	 * Method to reduce a 1in3SAT problem instance into a SubsetSum problem
	 * instance.
	 * 
	 * @param OneInThreeSAT
	 *            the 1in3SAT problem instance
	 * @return the SubsetSum problem instance
	 */
	public static SubsetSum<Integer> OneInThreeSAT_SubsetSum(ThreeSAT OneInThreeSAT) {
		int literalCount = OneInThreeSAT.getLiteralCount();
		ArrayList<Clause> clauses = OneInThreeSAT.getClauses();
		int numberLength = literalCount + clauses.size();
		ArrayList<Integer> subsetSumNumbers = new ArrayList<>();

		// formulate the target
		char[] targetChar = new char[numberLength];
		Arrays.fill(targetChar, '1');
		int target = Integer.parseInt(new String(targetChar));

		for (int i = 0; i < literalCount; i++) {
			// vTrue[i], vFalse[i] represents the TRUE/FALSE assignment of x_i
			// if set i^th digit in both vFalse and vTrue to 1 to forces
			// choosing
			// only 1 at the same time
			// by default all are 0
			char[] vTrue = new char[numberLength];
			char[] vFalse = new char[numberLength];
			Arrays.fill(vTrue, '0');
			Arrays.fill(vFalse, '0');
			vTrue[i] = '1';
			vFalse[i] = '1';

			// set digit according to presences in clauses
			for (int j = 0; j < clauses.size(); j++) {
				Clause clause = clauses.get(j);
				Literal toCheck = new Literal(i);
				// check either x_i or its negation is in the clause
				if (clause.containLiteral(toCheck)) {
					vTrue[literalCount + j] = '1';
				} else {
					toCheck.setNegation(true);
					if (clause.containLiteral(toCheck)) {
						vFalse[literalCount + j] = '1';
					}
				}
			}
			// potential improvement by just considering the clauses indexes
			// int[] newArray = Arrays.copyOfRange(oldArray, startIndex,
			// endIndex);

			subsetSumNumbers.add(Integer.parseInt(new String(vTrue)));
			subsetSumNumbers.add(Integer.parseInt(new String(vFalse)));
		}
		return new IntegerSubsetSum(subsetSumNumbers, target);
	}

	/**
	 * Method to reduce a SubsetSum problem instance to a Decision 0-1 Knapsack
	 * problem instance
	 * 
	 * @param ss
	 *            the SubsetSum problem instance
	 * @return the Decision 0-1 Knapsack problem instance
	 */
	public static Knapsack<Integer> SubsetSum_Decision01Knapsack(SubsetSum<Integer> ss) {
		Knapsack<Integer> knapsack = new IntegerKnapsack();

		knapsack.setBudget(ss.getTarget());
		knapsack.setTarget(ss.getTarget());

		ArrayList<Integer> numberSet = ss.getSet();
		for (Integer number : numberSet) {
			Item<Integer> i = new IntegerItem();
			i.setCost(number);
			i.setValue(number);
			knapsack.addItem(i);
		}
		return knapsack;
	}

	/**
	 * Method to reduce from a 3SAT problem instance to a Decision 0-1 Knapsack
	 * problem instance
	 * 
	 * @param instance
	 *            the 3SAT problem instance
	 * @return the Decision 0-1 Knapsack instance
	 */
	public static Knapsack<Integer> ThreeSAT_Decision01Knapsack(ThreeSAT instance) {
		return SubsetSum_Decision01Knapsack(OneInThreeSAT_SubsetSum(ThreeSAT_OneInThreeSAT(instance)));
	}
}
