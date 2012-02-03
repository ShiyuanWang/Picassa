package expression;


import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;

import model.RGBColor;

public class Value extends Factory implements Expression{
	int numofop = -1;
	private static final Pattern
	EXPRESSION_BEGIN_REGEX = Pattern.compile("([a-s]+)|([u-w]+)|([z]+)|([A-Z]+)|([x]+([a-z]+))|([y]+([a-z]+))");
	public Value(int currentPosition) {
		super(currentPosition);
	}

	public Value() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) { 
		boolean result= super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
		return result;
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new Value());
		return result;

	}

	@Override
	public Value getFactory() {
		return this;
	}

	public RGBColor evaluate(double x, double y, double t) {
		return null;
	}

	@Override
	public Expression setop(Expression[] operands, int myCurrentPosition) {
		return null;
	}
	
}


