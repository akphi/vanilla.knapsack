package main.java.vanilla.beans.threesat;

import java.util.ArrayList;

/**
 * Bean for 3SAT problem instance.
 * 
 * @author An Phi
 *
 */
public class ThreeSAT {
	ArrayList<Clause> clauses = null;
	int literalCount = 0;

	public ThreeSAT(ArrayList<Clause> Sentence, int literalCount) {
		clauses = Sentence;
		this.literalCount = literalCount;
	}

	public ArrayList<Clause> getClauses() {
		return clauses;
	}

	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}

	public int getLiteralCount() {
		return literalCount;
	}

	public void setLiteralCount(int literalCount) {
		this.literalCount = literalCount;
	}

	@Override
	public String toString() {
		return this.toString(0);
	}
	
	/**
	 * String that represents the problem instance
	 * 
	 * @param mode
	 *            0: structural output, 1: beautified output, 2: LaTeX output
	 * @return output
	 */
	public String toString(int mode) {
		String toPrint = "";
		switch (mode) {
		case 1:
			toPrint += "3SAT \nNumber of Literals: " + literalCount + "\nClauses:\n   ";
			for (int i = 0; i < clauses.size(); i++) {
				toPrint += clauses.get(i).toString(mode) + "\n";
				if (i != clauses.size() - 1) {
					toPrint += " \u2227 ";
				}
			}
			return toPrint;
		case 2:
			toPrint += "3SAT \nNumber of Literals: " + literalCount + "\nClauses:\n $$";
			for (int i = 0; i < clauses.size(); i++) {
				toPrint += clauses.get(i).toString(mode) + "\n";
				if (i != clauses.size() - 1) {
					toPrint += " \\land ";
				}
			}
			toPrint += "$$";
			return toPrint;
		case 0:
		default:
			// use this for a more structural output
			return "3SAT \n[clauses=\n" + clauses + "\nliteralCount=" + literalCount + "\n]\n";
		}
	}

}
