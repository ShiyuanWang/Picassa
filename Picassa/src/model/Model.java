package model;

import java.awt.Dimension;

import expression.Expression;





/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model
{
	  public static final double DOMAIN_MIN = -1;
	    public static final double DOMAIN_MAX = 1;
	    public static final int NUM_FRAMES = 50;

	    private double myCurrentTime = 0;


	    /**
	     * Advance to the next frame in the animation.
	     */
	    public void reset ()
	    {
	        myCurrentTime = 0;
	    }


	    /**
	     * Advance to the next frame in the animation.
	     */
	    public void nextFrame ()
	    {
	        myCurrentTime += 1.0 / NUM_FRAMES;
	    }

    public Pixmap evaluate (String input, Dimension size)
    {
        Pixmap result = new Pixmap(size);
        // create expression to evaluate just once
        Expression toEval = new Parser().makeExpression(input,0);
        return SetPixmap(result, toEval);
    }
    public Pixmap SetPixmap (Pixmap result, Expression toEval)
    {   
    	Dimension size = result.getSize();
    	for (int imageY = 0; imageY < size.height; imageY++)
        {
            double evalY = imageToDomainScale(imageY, size.height);
            
            for (int imageX = 0; imageX < size.width; imageX++)
            {   
                double evalX = imageToDomainScale(imageX, size.width);
                result.setColor(imageX, imageY,
                                toEval.evaluate(evalX,evalY,myCurrentTime).toJavaColor());
            }
        }
        return result; 
    }
    
    

    
    
    
    /**
     * Convert from image space to domain space
     */
    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double)value / bounds) * range + DOMAIN_MIN;
    }
}
