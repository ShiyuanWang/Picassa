package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;


import model.RGBColor;
import model.util.ColorCombinations;

public class ConstantColor extends Factory implements Expression {

	private Expression myOperand1;
	private Expression myOperand2;
	private Expression myOperand3;
	public static int myCurrentPosition;
	String myInput;
	int numofop = 3;
	protected static final Pattern

	EXPRESSION_BEGIN_REGEX = Pattern.compile("\\((color)");

	public ConstantColor(Expression red, Expression blue, Expression green) {
		myOperand1 = red;
		myOperand2 = blue;
		myOperand3 = green;
	}

	public ConstantColor() {
	}

	public ConstantColor(Expression[] operands, int currentPosition) {
		super(currentPosition);
		myOperand1 = operands[0];
		myOperand2 = operands[1];
		myOperand3 = operands[2];
	//	myCurrentPosition = currentPosition;
	}

	public ConstantColor setop(Expression[] operands, int currentPosition) {
		return new ConstantColor(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return ColorCombinations.color(myOperand1.evaluate(x, y, t),
				myOperand2.evaluate(x, y, t), myOperand3.evaluate(x, y,t));

	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {

		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);

	}

	@Override
	public Expression parseExpression(String myinput, int currentPosition, Map<String, Expression> map) {
		Expression result = super.parseExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX, numofop, map, new ConstantColor());
		myCurrentPosition = super.getPosition();
		return result;

	}


	public ConstantColor getFactory() {
		return this;
	}

}
