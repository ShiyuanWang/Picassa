package expression;


import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;


import model.RGBColor;
import model.util.ColorCombinations;

public class Cos extends Factory implements Expression {
	private Expression myOperand1;
	int numofop = 1;
	private static final Pattern

	EXPRESSION_BEGIN_REGEX = Pattern.compile("\\((Cos)");

	public Cos(Expression[] operands) {
		myOperand1 = operands[0];
	}

	public Cos() {
		// TODO Auto-generated constructor stub
	}

	public Cos(Expression[] operands, int currentPosition) {
		super(currentPosition);
		myOperand1 = operands[0];
	}

	public Cos setop(Expression[] operands, int currentPosition) {
		return new Cos(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return ColorCombinations.cos(myOperand1.evaluate(x, y, t));
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition,Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop,map, new Cos());
		return result;

	}

	@Override
	public Cos getFactory() {
		return this;
	}
}


