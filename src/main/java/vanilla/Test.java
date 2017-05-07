package main.java.vanilla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import main.java.vanilla.ProblemFactory;
import main.java.vanilla.algorithms.Knapsack01Decision;
import main.java.vanilla.algorithms.Knapsack2Approximation;
import main.java.vanilla.algorithms.KnapsackFPTAS;
import main.java.vanilla.algorithms.KnapsackMaxValueDP;
import main.java.vanilla.algorithms.KnapsackMinCostDP;
import main.java.vanilla.algorithms.Reduction;
import main.java.vanilla.beans.knapsack.IntegerItem;
import main.java.vanilla.beans.knapsack.Item;
import main.java.vanilla.beans.knapsack.Knapsack;
import main.java.vanilla.beans.threesat.ThreeSAT;

/**
 * For testing purpose.
 * 
 * @author An Phi
 *
 */
public class Test {

	private static Properties props = Config.getInstance().getConfig();

	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(((new File("")).getAbsolutePath()) + "/"
				+ props.getProperty("DATA_PATH") + "/" + props.getProperty("TEST_FILE_PATH")));
		StringBuilder sb = new StringBuilder();

		ProblemFactory pf = ProblemFactory.getInstance();
		try {
			ThreeSAT instance = pf.generate3SAT(1);
			sb.append("=========================\nPROBLEM: ");
			sb.append(instance.toString(2));
			sb.append("=========================\nREDUCTION: ");
			Knapsack<Integer> ks = Reduction.ThreeSAT_Decision01Knapsack(instance);
			sb.append(ks);
			sb.append("=========================\nSOLUTION: ");
			sb.append("Success: " + Knapsack01Decision.decide(ks, 2));
		} catch (OutOfMemoryError | NumberFormatException e) {
			sb.append("Error: " + e);
		} finally {
			sb.append("\n=========================\n\n");
		}

		try {
			ThreeSAT instance = pf.generate3SAT(2);
			sb.append("=========================\nPROBLEM: ");
			sb.append(instance.toString(2));
			sb.append("=========================\nREDUCTION: ");
			Knapsack<Integer> ks = Reduction.ThreeSAT_Decision01Knapsack(instance);
			sb.append(ks);
			sb.append("=========================\nSOLUTION: ");
			sb.append("Success: " + Knapsack01Decision.decide(ks, 2));
		} catch (OutOfMemoryError | NumberFormatException e) {
			sb.append("Error: " + e);
		} finally {
			sb.append("\n=========================\n\n");
		}

		try {
			Knapsack<Integer> instance = pf.generateKnapsack();
			instance.setBudget(10);
			Item<Integer> item1 = new IntegerItem(3, 2);
			Item<Integer> item2 = new IntegerItem(4, 3);
			Item<Integer> item3 = new IntegerItem(5, 4);
			Item<Integer> item4 = new IntegerItem(6, 5);
			ArrayList<Item<Integer>> itemList = new ArrayList<>();
			itemList.add(item1);
			itemList.add(item2);
			itemList.add(item3);
			itemList.add(item4);
			instance.setItemList(itemList);
			sb.append("=========================\nPROBLEM: ");
			sb.append(instance);
			sb.append("=========================\nSOLUTION: ");
			sb.append("Max Value DP: " + KnapsackMaxValueDP.solve(instance) + "\n");
			sb.append("2-Approximation: " + Knapsack2Approximation.solve(instance) + "\n");
			sb.append("Min Cost DP: " + KnapsackMinCostDP.solve(instance) + "\n");
			sb.append("FPTAS: " + KnapsackFPTAS.solve(instance, 0.5));
		} catch (Exception e) {
			sb.append("Error: " + e);
		} finally {
			sb.append("\n=========================\n\n");
		}
		pw.write(sb.toString());
		pw.close();
	}

}
