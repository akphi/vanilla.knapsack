package main.java.vanilla;

import java.util.Arrays;
import java.util.Random;

/**
 * Provide utilities for other classes
 * 
 * @author An Phi
 *
 */
public class Utils {

	/**
	 * Randomly generate boolean
	 * 
	 * @param rand
	 *            the random generator
	 * @return a boolean
	 */
	public static boolean generateBoolean(Random rand) {
		return Math.round(rand.nextDouble()) == 1 ? true : false;
	}

	/**
	 * Print multi-dimensional array
	 * 
	 * @param table
	 *            the array
	 */
	public static void printMultiDimArray(Object[] table) {
		System.out.println(Arrays.deepToString(table));
	}

	// public static void print2DArray(Object[][] table, int numTab) {
	// for (Object[] row : table) {
	// for (Object i : row) {
	// System.out.print(i);
	// System.out.print(String.join("", Collections.nCopies(numTab, "\t")));
	// }
	// System.out.println();
	// }
	// }

	// public static void main(String[] args) {
	// // STUB
	// }
}
