package expression;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expression_Factory.Factory;

import model.Parser;
import model.ParserException;
import model.RGBColor;

import model.util.LittleTools;

public class Let extends Factory implements Expression {
	private Expression value;
	private String key;
	private int myCurrentPosition;
	private int numofop = 2;
	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\((let)");
	private static final Pattern EXPRESSION_VALUE_REGEX = Pattern
			.compile("([a-z]+)|([A-Z]+)");

	public Let() {
	}

	public Let(Expression[] operands, int currentPosition) {
		super(currentPosition);
		value = operands[1];
	}

	public Let setop(Expression[] operands, int currentPosition) {
		return new Let(operands, currentPosition);
	}

	public RGBColor evaluate(double x, double y, double t) {

		return value.evaluate(x,y,t);
	}

	@Override
	public boolean isThisKindOfExpression(String input, int currentPosition) {
         
		return super.isThisKindOfExpression(input, currentPosition,
				EXPRESSION_BEGIN_REGEX);
	}
	@Override
	public Expression parseExpression(String input, int currentPosition, Map<String, Expression> map) {
		 LittleTools tool = new LittleTools(input);
	     myCurrentPosition = currentPosition; 
	     Matcher expMatcher =EXPRESSION_BEGIN_REGEX.matcher(input);
         expMatcher.find(myCurrentPosition);
         myCurrentPosition = expMatcher.end();
         myCurrentPosition = tool.skipWhiteSpace(myCurrentPosition);
         key = genKey(input,myCurrentPosition);
         Expression[] operands = this.genOperands(input, numofop, map);
         myCurrentPosition = tool.skipWhiteSpace(myCurrentPosition);
         if(myCurrentPosition == input.length())
         {
         	throw new ParserException("The parens are not balanced!");
         }
        if (tool.currentCharacter(myCurrentPosition) == ')')
        {  
            myCurrentPosition++;
            return this.setop(operands,myCurrentPosition);
        }
        else
        {   
            throw new ParserException("Expected close paren, instead found " +
                                      input.substring(myCurrentPosition));
        }
    	
	}
	
	
	private Expression[] genOperands(String input, int num, Map<String, Expression>map)
    {    
    	  Expression[] ops = new Expression[num];
    	  for(int i = 0; i< num; i++)
    	  {    
    		  ops[i] = new Parser().switchExpression(input, myCurrentPosition, map);
    		  if(i == 0) map.put(key,ops[0]);
    		  myCurrentPosition = ops[i].getPosition();
    		  myCurrentPosition = new LittleTools(input).skipWhiteSpace(myCurrentPosition);

    	  }
    	return ops;
    }
    

	private String genKey(String input, int myCurrentPosition2) {
		 Matcher expMatcher =EXPRESSION_VALUE_REGEX.matcher(input);
         expMatcher.find(myCurrentPosition);
         myCurrentPosition = expMatcher.end();
         String name = input.substring(expMatcher.start(),
        		 expMatcher.end());
         myCurrentPosition = new LittleTools(input).skipWhiteSpace(myCurrentPosition);
         return name;
	}

	public Let getFactory() {
		return this;
	}

}

