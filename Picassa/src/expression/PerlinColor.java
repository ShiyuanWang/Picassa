package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;

import model.RGBColor;

import model.util.ColorCombinations;

public class PerlinColor extends Factory implements Expression {
	private Expression myOperand1;
	private Expression myOperand2;
	private int numofop = 2;
	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\((perlinColor)");

	public PerlinColor() {
	}

	public PerlinColor(Expression[] operands, int currentPosition) {
		super(currentPosition);
		myOperand1 = operands[0];
		myOperand2 = operands[1];
	}

	public PerlinColor setop(Expression[] operands, int currentPosition) {
		return new PerlinColor(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return ColorCombinations.perlinColor(myOperand1.evaluate(x, y, t),
				myOperand2.evaluate(x, y, t));
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String,Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new PerlinColor());
		return result;

	}

	public PerlinColor getFactory() {
		return this;
	}

}
/* Why I can not create a new Parser here */
