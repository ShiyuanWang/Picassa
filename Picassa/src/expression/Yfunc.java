package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;

import model.RGBColor;
import model.util.ColorCombinations;

public class Yfunc extends Factory implements Expression {
	private final Pattern Y = Pattern.compile("(y)");
	private int numofop = 0;

	public Yfunc() {
	}

	public Yfunc(int currentPosition) {
		super(currentPosition);
	}

	public Expression setop(Expression[] operands, int currentPosition) {
		return new Yfunc(currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {
		return ColorCombinations.XYfunc(y);
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {
		return super.isThisKindOfExpression(input, currentPosition, Y);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition, Y,
				numofop, map, new Yfunc());
		return result;
	}

	public Yfunc getFactory() {
		return this;
	}
}
