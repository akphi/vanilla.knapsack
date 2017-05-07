package main.java.vanilla;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Properties;

import main.java.vanilla.ProblemFactory;
import main.java.vanilla.beans.knapsack.Knapsack;
import main.java.vanilla.algorithms.KnapsackMaxValueDP;
import main.java.vanilla.algorithms.Knapsack2Approximation;
import main.java.vanilla.algorithms.KnapsackMinCostDP;
import main.java.vanilla.algorithms.KnapsackFPTAS;

/**
 * The Main method to run the program
 * 
 * @author An Phi
 *
 */
public class Main {

	private static Properties props = Config.getInstance().getConfig();

	public static int solve(Knapsack<Integer> instance, int algorithm) {
		switch (algorithm) {
		case 1:
			return KnapsackMaxValueDP.solve(instance);
		case 2:
			return Knapsack2Approximation.solve(instance);
		case 3:
			return KnapsackMinCostDP.solve(instance);
		case 4:
		default:
			return KnapsackFPTAS.solve(instance, Double.parseDouble(props.getProperty("FPTAS_EPSILON")));
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ProblemFactory pf = ProblemFactory.getInstance();
		PrintWriter pw = new PrintWriter(new File(((new File("")).getAbsolutePath()) + "/"
				+ props.getProperty("DATA_PATH") + "/" + props.getProperty("KNAPSACK_FILE_PATH")));
		StringBuilder sb = new StringBuilder();
		long startTime, endTime;

		sb.append("n");
		sb.append(',');
		sb.append("Budget");
		sb.append(',');
		sb.append("MaxValue");
		sb.append(',');
		sb.append("MaxValueDP");
		sb.append(',');
		sb.append("MaxValueDP Running Time");
		sb.append(',');
		sb.append("2-Approximation");
		sb.append(',');
		sb.append("2-Approximation Running Time");
		sb.append(',');
		sb.append("MinCostDP");
		sb.append(',');
		sb.append("MinCostDP Running Time");
		sb.append(',');
		sb.append("FPTAS");
		sb.append(',');
		sb.append("FPTAS Running Time");
		sb.append('\n');

		for (int k = Integer.parseInt(props.getProperty("KNAPSACK_MIN_ITEM_LIST_SIZE")); k <= Integer
				.parseInt(props.getProperty("KNAPSACK_MAX_ITEM_LIST_SIZE")); k += Integer
						.parseInt(props.getProperty("KNAPSACK_INCREMENT_ITEM_LIST_SIZE"))) {
			for (int i = 0; i < Integer.parseInt(props.getProperty("ITERATION")); i++) {
				Knapsack<Integer> instance = pf.generateIntegerKnapsack(k,
						Integer.parseInt(props.getProperty("KNAPSACK_ITEM_VALUE_MAX")),
						Integer.parseInt(props.getProperty("KNAPSACK_ITEM_COST_MAX")));
				sb.append(k);
				sb.append(',');
				sb.append(instance.getBudget());
				sb.append(',');
				sb.append(instance.getMaxValue());
				sb.append(',');
				for (int j = 1; j <= 4; j++) {
					startTime = System.nanoTime();
					sb.append(Main.solve(instance, j));
					endTime = System.nanoTime();
					sb.append(',');
					sb.append(endTime - startTime); // in nanosecond
					if (j != 4) {
						sb.append(',');
					}
				}
				sb.append("\n");
			}
		}
		pw.write(sb.toString());
		pw.close();
	}
}
