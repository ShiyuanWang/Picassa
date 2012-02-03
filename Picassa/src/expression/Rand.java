package expression;

import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import model.RGBColor;
import expression_Factory.Factory;

public class Rand extends Factory implements Expression{
	private RGBColor myValue;
	private int numofop = 0;
	private final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\((random)\\)");
	
	public Rand(RGBColor value, int currentPosition) {
		super(currentPosition);
		myValue = value;
	}
	
	public Rand() {	
	}

	public RGBColor evaluate(double x, double y, double t) {
		Random ran=new Random();
		myValue = new RGBColor(ran.nextFloat());
		return myValue;
	}

	@Override
	public boolean isThisKindOfExpression(String myinput, int currentPosition) {
		
		return super.isThisKindOfExpression(myinput, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}

	 public Expression parseExpression(String myinput, int currentPosition, Map<String,Expression> map) {
	
		 Expression result =  super.parseExpression(myinput, currentPosition,EXPRESSION_BEGIN_REGEX, numofop, map, new Rand());
		 return result;
			
		}

		public Rand getFactory() {
			return this;

		}

	@Override
	public Expression setop(Expression[] operands, int myCurrentPosition) {
		return this;
	}
}
