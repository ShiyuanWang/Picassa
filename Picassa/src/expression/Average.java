package expression;

import java.util.Map;
import java.util.regex.Pattern;

import model.RGBColor;
import model.util.ColorCombinations;

import expression_Factory.Factory;



public class Average extends Factory implements Expression {
	private Expression[] opList;
	private int numofop = 1024;
	private static final Pattern

	EXPRESSION_BEGIN_REGEX = Pattern.compile("\\((average)");

	public Average() {
		// TODO Auto-generated constructor stub
	}

	public Average(Expression[] operands, int currentPosition) {
		super(currentPosition);
		opList = operands;
		
	}

	public Average setop(Expression[] operands, int currentPosition) {
		return new Average(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {
         
		return ColorCombinations.average(opList, x, y, t);
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String,Expression> map) {
		Expression result = super.parseExpression(myinput,currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map,new Average());
		return result;

	}

	@Override
	public Average getFactory() {
		return this;
	}

}

