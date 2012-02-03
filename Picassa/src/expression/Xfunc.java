package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;

import model.RGBColor;
import model.util.ColorCombinations;

public class Xfunc extends Factory implements Expression {
	private final Pattern X = Pattern.compile("(x)");
	private int numofop = 0;

	public Xfunc() {
	}

	public Xfunc(int currentPosition) {
		super(currentPosition);
	}

	public Expression setop(Expression[] operands, int currentPosition) {
		return new Xfunc(currentPosition);
	}

	public RGBColor evaluate(double x, double y,double t) {
		return ColorCombinations.XYfunc(x);
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {
		return super.isThisKindOfExpression(input, currentPosition, X);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition,Map<String,Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition, X,
				numofop, map, new Xfunc());
		return result;

	}

	public Xfunc getFactory() {
		return this;
	}
}
