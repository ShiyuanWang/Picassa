package expression_Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression.Constant;
import expression.Expression;
import expression.Rand;

import model.Parser;
import model.ParserException;
import model.RGBColor;
import model.util.LittleTools;

public abstract class Factory {
	private int myCurrentPosition;


	protected Factory(int currentPosition) {
		myCurrentPosition = currentPosition;
	}

	protected Factory() {
		this(0);
	}

	public abstract Expression parseExpression(String myinput,
			int CurrentPosition, Map<String, Expression> map);

	protected Expression parseExpression(String input, int CurrentPosition,
			Pattern regex, int numofop, Map<String, Expression> map,
			Expression type)

	{  
		LittleTools tool = new LittleTools(input);
		myCurrentPosition = CurrentPosition;
		Matcher expMatcher = regex.matcher(input);
		expMatcher.find(myCurrentPosition);
		myCurrentPosition = expMatcher.end();
		String numberMatch = input.substring(expMatcher.start(),
				expMatcher.end());
		if(numofop == -1)
		{
			if (map.containsKey(numberMatch)) {
				Expression val = map.get(numberMatch);
				val.setPosition(myCurrentPosition);
				return val;
			}
			else 
				throw new ParserException("Unknown Type of Expression is used to evaluate "
						+ input.substring(myCurrentPosition-1));
			
		}
		if (numofop == 0) {
			if (numberMatch.equals("x") | numberMatch.equals("y")|numberMatch.equals("t")) {
				return type.setop(null, myCurrentPosition);
			}
			if (numberMatch.equals("(random)")) {
				return new Rand(new RGBColor(), myCurrentPosition);
			} else
				return new Constant(new RGBColor(
						Double.parseDouble(numberMatch)), myCurrentPosition);
		}
		Expression[] operands = genOperands(input, numofop, map);
		myCurrentPosition = tool.skipWhiteSpace(myCurrentPosition);
		if (myCurrentPosition == input.length()) {
			throw new ParserException("The parens are not balanced!");
		}
		if (tool.currentCharacter(myCurrentPosition) == ')') {
			myCurrentPosition++;
			return type.setop(operands, myCurrentPosition);

		} else {
			throw new ParserException("Expected close paren, instead found "
					+ input.substring(myCurrentPosition));
		}

	}

	private Expression[] genOperands(String input, int num,
			Map<String, Expression> map) {
		Expression[] ops = new Expression[num];
		LittleTools tool = new LittleTools(input);
		for (int i = 0; i < num; i++) {
			ops[i] = new Parser().switchExpression(input, myCurrentPosition,
					map);
			myCurrentPosition = ops[i].getPosition();
			myCurrentPosition = tool.skipWhiteSpace(myCurrentPosition);
			if(tool.currentCharacter(myCurrentPosition) == ')')
				return ops;
           
		}
		return ops;
	}

	public abstract Expression getFactory();

	protected boolean isThisKindOfExpression(String input, int currentPosition,
			Pattern regex) {
		LittleTools tool = new LittleTools(input);
		currentPosition = tool.skipWhiteSpace(currentPosition);
		Matcher patternMatcher = regex
				.matcher(input.substring(currentPosition));
		boolean result = patternMatcher.lookingAt();
		return result;
	}

	public abstract boolean isThisKindOfExpression(String myinput,
			int currentPosition);

	public int getPosition() {

		return myCurrentPosition;
	}

	public void setPosition(int currentPosition) {

		myCurrentPosition = currentPosition;

	}
}
