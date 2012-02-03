package expression;


import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;


import model.RGBColor;
import model.util.ColorCombinations;

public class YCrCbtoRGB extends Factory implements Expression {
	private Expression myOperand1;
	int numofop = 1;
	private static final Pattern

	EXPRESSION_BEGIN_REGEX = Pattern.compile("\\((yCrCbtoRGB)");

	public YCrCbtoRGB(Expression[] operands) {
		myOperand1 = operands[0];
	}

	public YCrCbtoRGB() {
		// TODO Auto-generated constructor stub
	}

	public YCrCbtoRGB(Expression[] operands, int currentPosition) {
		super(currentPosition);
		myOperand1 = operands[0];
	}

	public YCrCbtoRGB setop(Expression[] operands, int currentPosition) {
		return new YCrCbtoRGB(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return ColorCombinations.yCrCbtoRGB(myOperand1.evaluate(x, y, t));
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new YCrCbtoRGB());
		return result;

	}

	@Override
	public YCrCbtoRGB getFactory() {
		return this;
	}
}


