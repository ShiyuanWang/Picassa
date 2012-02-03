package expression;

import java.util.Map;
import java.util.regex.Pattern;

import expression_Factory.Factory;
import model.RGBColor;


public class Constant extends Factory implements Expression {
	private RGBColor myValue;
	private int numofop = 0;
	private static final Pattern DOUBLE_REGEX = Pattern
			.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");


	public Constant(RGBColor value, int currentPosition) {
		super(currentPosition);
		myValue = value;
	}
	
	public Constant() {
	}

	public RGBColor evaluate(double x, double y, double t) {
		return myValue;
	}

	@Override
	public boolean isThisKindOfExpression(String myinput, int currentPosition) {
		return super.isThisKindOfExpression(myinput, currentPosition,
				DOUBLE_REGEX);
	}

	@Override
	 public Expression parseExpression(String myinput, int currentPosition, Map<String,Expression> map) {
		 Expression result =  super.parseExpression(myinput, currentPosition,DOUBLE_REGEX, numofop, map,new Subtract());
		 return result;	
		}

	public Constant getFactory() {
		return this;
	}

	@Override
	public Expression setop(Expression[] operands, int myCurrentPosition) {
		return this;
	}

	@Override
	public void setPosition(int currentPosition) {
		super.setPosition(currentPosition);
	}
}
