package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;


import model.RGBColor;
import model.util.ColorCombinations;

public class Divide extends Factory implements Expression {
	private Expression myOperand1;
	private Expression myOperand2;
	private int numofop = 2;
	protected static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("(\\((div))|(\\((\\\\))");
	public Divide() {
	}

	public Divide(Expression[] operands, int currentPosition) {
		super(currentPosition);
		myOperand1 = operands[0];
		myOperand2 = operands[1];
	}

	public Divide setop(Expression[] operands, int currentPosition) {
		return new Divide(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return ColorCombinations.divide(myOperand1.evaluate(x, y, t),
				myOperand2.evaluate(x, y, t));
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new Divide());
		return result;
	}

	@Override
	public Divide getFactory() {
		return this;
	}

}
