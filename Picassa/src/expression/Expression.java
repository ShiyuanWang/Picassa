package expression;

import model.RGBColor;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public interface Expression
{

    /**
     * Create expression representing the given constant value
     */
	//public RGBColor evaluate (double x, double y);
	public int getPosition();
	public Expression setop(Expression[] operands, int myCurrentPosition);
	public void setPosition(int myCurrentPosition);
    /**
     * @return string representation of expression
     */
 /*   public String toString ()
    {
        StringBuffer result = new StringBuffer();
        if (myCommand == null)
        {
            result.append(myValue); 
        }
        else
        {
            result.append("(");
            result.append(" " + myCommand + " ");
            result.append(myOperand1.toString());  
            result.append(myOperand2.toString());
            result.append(")");
        }
        return result.toString();
    }*/
	public RGBColor evaluate(double evalX, double evalY, double myCurrentTime);
	
}
