package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;

import model.RGBColor;
import model.util.ColorCombinations;

public class Tcase extends Factory implements Expression {
	private final Pattern X = Pattern.compile("(t)");
	private int numofop = 0;

	public Tcase() {
	}

	public Tcase(int currentPosition) {
		super(currentPosition);
	}

	public Expression setop(Expression[] operands, int currentPosition) {
		return new Tcase(currentPosition);
	}

	public RGBColor evaluate(double x, double y,double t) {
		return new RGBColor(t);
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {
		return super.isThisKindOfExpression(input, currentPosition, X);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition,Map<String,Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition, X,
				numofop, map, new Tcase());
		return result;

	}

	public Tcase getFactory() {
		return this;
	}
}
