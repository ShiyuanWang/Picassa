package expression;


import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;


import model.RGBColor;
import model.util.ColorCombinations;

public class Wrap extends Factory implements Expression {
	private Expression myOperand1;
	int numofop = 1;
	private static final Pattern

	EXPRESSION_BEGIN_REGEX = Pattern.compile("\\((wrap)");

	public Wrap(Expression[] operands) {
		myOperand1 = operands[0];
	}

	public Wrap() {
		// TODO Auto-generated constructor stub
	}

	public Wrap(Expression[] operands, int currentPosition) {
		super(currentPosition);
		myOperand1 = operands[0];
	}

	public Wrap setop(Expression[] operands, int currentPosition) {
		return new Wrap(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return ColorCombinations.wrap(myOperand1.evaluate(x, y, t));
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new Wrap());
		return result;

	}

	@Override
	public Wrap getFactory() {
		return this;
	}
}


