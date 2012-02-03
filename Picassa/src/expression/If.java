package expression;

import java.util.Map;
import java.util.regex.Pattern;

import model.RGBColor;
import model.util.ColorCombinations;

import expression_Factory.Factory;



public class If extends Factory implements Expression {
	private Expression[] opList;
	private int numofop = 3;
	private static final Pattern

	EXPRESSION_BEGIN_REGEX = Pattern.compile("\\((if)");

	public If() {
		// TODO Auto-generated constructor stub
	}

	public If(Expression[] operands, int currentPosition) {
		super(currentPosition);
		opList = operands;
		
	}

	public If setop(Expression[] operands, int currentPosition) {
		return new If(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {
         
		return ColorCombinations.ifcase(opList, x, y, t);
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String,Expression> map) {
		Expression result = super.parseExpression(myinput,currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new If());
		return result;

	}

	@Override
	public If getFactory() {
		return this;
	}

}

