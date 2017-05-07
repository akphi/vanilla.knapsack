package main.java.vanilla.beans.threesat;

import java.util.ArrayList;

/**
 * Bean for clause in 3SAT problem
 * 
 * @author An Phi
 *
 */
public class Clause {
	ArrayList<Literal> literalSet = null;
	int size = 0;

	public Clause() {
		literalSet = new ArrayList<Literal>();
	}

	public Clause(ArrayList<Literal> data) {
		if (data.size() == 3) {
			literalSet = data;
			size = 3;
		}
	}

	public void addLiteral(Literal l) {
		literalSet.add(l);
		size++;
	}

	public Literal getLiteral(int i) {
		if (i >= 0 && i < 3) {
			return literalSet.get(i);
		}
		return null;
	}

	public boolean containLiteral(Literal input) {
		for (Literal l : literalSet) {
			if (l.equals(input)) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return this.toString(0);
	}

	/**
	 * String that represents the clause
	 * 
	 * @param mode
	 *            0: structural output, 1: beautified output, 2: LaTeX output
	 * @return output
	 */
	public String toString(int mode) {
		String toPrint = "";
		switch (mode) {
		case 1:
			toPrint += "(";
			for (int i = 0; i < literalSet.size(); i++) {
				toPrint += literalSet.get(i).toString(mode);
				if (i != literalSet.size() - 1) {
					toPrint += " \u2228 ";
				}
			}
			toPrint += ")";
			return toPrint;
		case 2:
			toPrint += "(";
			for (int i = 0; i < literalSet.size(); i++) {
				toPrint += literalSet.get(i).toString(mode);
				if (i != literalSet.size() - 1) {
					toPrint += " \\lor ";
				}
			}
			toPrint += ")";
			return toPrint;
		case 0:
		default:
			// use this for a more structural output
			return "Clause [" + literalSet + ", size=" + size + "]\n";
		}
	}
}
