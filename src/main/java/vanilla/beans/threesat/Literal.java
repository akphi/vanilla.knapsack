package main.java.vanilla.beans.threesat;

/**
 * Bean for literal in clause.
 * 
 * @author An Phi
 *
 */
public class Literal {
	boolean value;
	boolean negation = false;
	// force to set ID
	int id = -1;

	public Literal() {
	}

	public Literal(boolean value) {
		super();
		this.value = value;
	}

	public Literal(int id) {
		super();
		this.id = id;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

	public void setNegation(boolean negation) {
		this.negation = negation;
	}

	public void flipNegation() {
		this.negation = !this.negation;
	}

	public boolean isNegated() {
		return negation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object object) {
		boolean isSame = false;
		if (object != null && object instanceof Literal) {
			Literal literal = (Literal) object;
			isSame = (this.id == literal.getId()) && (this.negation == literal.isNegated());
		}
		return isSame;
	}

	@Override
	public String toString() {
		return this.toString(0);
	}

	/**
	 * String that represents the literal
	 * 
	 * @param mode
	 *            0: structural output, 1: beautified output, 2: LaTeX output
	 * @return output
	 */
	public String toString(int mode) {
		String toPrint = "";
		char x;
		switch (mode) {
		case 1:
			if (value) {
				x = 'X';
			} else {
				x = 'x';
			}
			if (negation) {
				toPrint += x + "\u0305" + "_" + id;
			} else {
				toPrint += x + "_" + id;
			}
			return toPrint;
		case 2:
			if (value) {
				x = 'X';
			} else {
				x = 'x';
			}
			if (negation) {
				toPrint += "\\bar{" + x + "}_{" + id + "}";
			} else {
				toPrint += x + "_{" + id + "}";
			}
			return toPrint;
		case 0:
		default:
			// use this for a more structural output
			return "Literal [value=" + value + ", Negate=" + negation + ", id=" + id + "]";
		}
	}
}
