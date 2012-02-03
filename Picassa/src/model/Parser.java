package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import expression.Abs;
import expression.Average;
import expression.Ceil;
import expression.Clamp;
import expression.Constant;
import expression.ConstantColor;
import expression.Cos;
import expression.Divide;
import expression.Exp;
import expression.Expression;
import expression.Floor;
import expression.If;
import expression.Let;
import expression.Log;
import expression.Max;
import expression.Min;
import expression.Mod;
import expression.Multiply;
import expression.Neg;
import expression.PerlinBW;
import expression.PerlinColor;
import expression.Plus;
import expression.Product;
import expression.RGBtoYCrCb;
import expression.Rand;
import expression.Sin;
import expression.Subtract;
import expression.Sum;
import expression.Tan;
import expression.Tcase;
import expression.Value;
import expression.Wrap;
import expression.Xfunc;
import expression.YCrCbtoRGB;
import expression.Yfunc;
import expression_Factory.Factory;
import model.ParserException.Type;
import model.util.LittleTools;
/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser {
	// double is made up of an optional negative sign, followed by a sequence
	// of one or more digits, optionally followed by a period, then possibly
	// another sequence of digits

	// expression begins with a left paren followed by the command name,
	// which is a sequence of alphabetic characters
	
	// state of the parser
	private ArrayList<Factory> expressionKind = new ArrayList<Factory>();
	private Map<String, Expression> expressionMap = new HashMap<String, Expression>();

	public Parser() {
	    expressionKind.add(new Value().getFactory());
	    expressionKind.add(new Constant().getFactory());
	    expressionKind.add(new Let().getFactory());
		expressionKind.add(new PerlinBW().getFactory());
		expressionKind.add(new PerlinColor().getFactory());
		expressionKind.add(new Rand().getFactory());
		expressionKind.add(new Ceil().getFactory());
		expressionKind.add(new Wrap().getFactory());
		expressionKind.add(new Sin().getFactory());
		expressionKind.add(new Cos().getFactory());
		expressionKind.add(new Tan().getFactory());
		expressionKind.add(new Log().getFactory());
		expressionKind.add(new RGBtoYCrCb().getFactory());
		expressionKind.add(new YCrCbtoRGB().getFactory());
		expressionKind.add(new Abs().getFactory());
		expressionKind.add(new Clamp().getFactory());
		expressionKind.add(new Floor().getFactory());
		expressionKind.add(new Constant().getFactory());
		expressionKind.add(new ConstantColor().getFactory());
		expressionKind.add(new Divide().getFactory());
		expressionKind.add(new Exp().getFactory());
		expressionKind.add(new Mod().getFactory());
		expressionKind.add(new Multiply().getFactory());
		expressionKind.add(new Neg().getFactory());
		expressionKind.add(new Plus().getFactory());
		expressionKind.add(new Subtract().getFactory());
		expressionKind.add(new Xfunc().getFactory());
		expressionKind.add(new Yfunc().getFactory());
		expressionKind.add(new Sum().getFactory());
		expressionKind.add(new Product().getFactory());
		expressionKind.add(new Average().getFactory());
		expressionKind.add(new Max().getFactory());
		expressionKind.add(new Min().getFactory());
		expressionKind.add(new If().getFactory());
		expressionKind.add(new Tcase().getFactory());
	}

	/**
	 * Converts given string into expression tree.
	 * 
	 * @param input
	 *            expression given in the language to be parsed
	 * @return expression tree representing the given formula
	 */
	public Expression makeExpression(String input, int currentPosition) {
		LittleTools tool = new LittleTools(input);
	//	Map<String,Expression> map = new HashMap<String, Expression>();
		Expression result = switchExpression(input, currentPosition, expressionMap);
		currentPosition = result.getPosition();
		currentPosition = tool.skipWhiteSpace(currentPosition);
		if (tool.notAtEndOfString(currentPosition)) {
			throw new ParserException(
					"Unexpected characters at end of the string: "
							+ input.substring(currentPosition),
					ParserException.Type.EXTRA_CHARACTERS);
		}
		return result;
	}

	public Expression switchExpression(String input, int currentPosition, Map<String, Expression> map) {
		
		for (Factory Express : expressionKind) {
			if (Express.isThisKindOfExpression(input, currentPosition)) {
				Expression result = Express.parseExpression(input,
						currentPosition, map);
				return result;
			}
		}
		throw new ParserException("Wrong Command " + input,
				Type.UNKNOWN_COMMAND);
	}
}
