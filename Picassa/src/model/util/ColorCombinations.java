package model.util;

import java.awt.List;

import expression.Constant;
import expression.Expression;

import model.RGBColor;


/**
 * Combine two colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of
 * ways to combine colors, many may exist and we do not want to keep
 * modifying the RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations
{
    /**
     * Combine two colors by adding their components.
     */
    public static RGBColor add (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() + right.getRed(), 
                            left.getGreen() + right.getGreen(),
                            left.getBlue() + right.getBlue());
    }

    /**
     * Combine two colors by subtracting their components.
     */
    public static RGBColor subtract (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() - right.getRed(), 
                            left.getGreen() - right.getGreen(),
                            left.getBlue() - right.getBlue());
    }

    /**
     * Combine two colors by multiplying their components.
     */
    public static RGBColor multiply (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() * right.getRed(), 
                            left.getGreen() * right.getGreen(),
                            left.getBlue() * right.getBlue());
    }

    /**
     * Combine two colors by dividing their components.
     */
    public static RGBColor divide (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() / right.getRed(), 
                            left.getGreen() / right.getGreen(),
                            left.getBlue() / right.getBlue());
    }
  
    /**
     * Combine two colors by moding their components.
     */
    public static RGBColor mod (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() % right.getRed(), 
                            left.getGreen() % right.getGreen(),
                            left.getBlue() % right.getBlue());
    }
    
    /**
     * Combine two colors by exping their components.
     */
    public static RGBColor exp (RGBColor left, RGBColor right)
    {   
        return new RGBColor( Math.pow(left.getRed(), right.getRed()), 
        		             Math.pow(left.getGreen(), right.getGreen()),
                             Math.pow(left.getBlue(), right.getBlue()));
    }
    
    /**
     * Invert a specific colour 
     */
    public static RGBColor neg (RGBColor value)
    {   
        return new RGBColor( -value.getRed(),
        		             -value.getGreen(),
        		             -value.getBlue());
    }
    
    /**
     * Use blue, red, green to colour
     */
      public static RGBColor color (double red, double green, double blue)
    {   
        return new RGBColor(red, green, blue);
        		       
    }
    
      /**
       * Use X to colour
       */  
      public static RGBColor XYfunc (double coordinate)
      {   
          return new RGBColor(coordinate);
          		       
      }
      
      public static RGBColor color(RGBColor op1, RGBColor op2, RGBColor op3)
      {
      	return new RGBColor(op1.getRed(),op2.getGreen(),op3.getBlue());
      }

     	public static RGBColor floor(RGBColor value) {
		return new RGBColor(Math.floor(value.getRed()),
				            Math.floor(value.getGreen()),
						    Math.floor(value.getBlue()));
	 }
     	
     	public static RGBColor ceil(RGBColor value) {
    		return new RGBColor(Math.ceil(value.getRed()),
    				            Math.ceil(value.getGreen()),
    						    Math.ceil(value.getBlue()));
    	 }

		public static RGBColor abs(RGBColor value) {
			return new RGBColor(Math.abs(value.getRed()),
		            Math.abs(value.getGreen()),
				    Math.abs(value.getBlue()));
		}

		public static RGBColor clamp(RGBColor value) {
			value.clamp();
			return value;
		}

		public static RGBColor sin(RGBColor value) {
			
			       return new RGBColor(Math.sin(value.getRed()),
		            Math.sin(value.getGreen()),
				    Math.sin(value.getBlue()));
		}

		public static RGBColor tan(RGBColor value) {
			return new RGBColor(Math.tan(value.getRed()),
		                        Math.tan(value.getGreen()),
				                 Math.tan(value.getBlue()));
		}

		public static RGBColor cos(RGBColor value) {
			return new RGBColor(Math.cos(value.getRed()),
		            Math.cos(value.getGreen()),
				    Math.cos(value.getBlue()));
		}

		public static RGBColor log(RGBColor value) {
			return new RGBColor(Math.log(value.getRed()),
		               Math.log(value.getGreen()),
				       Math.log(value.getBlue()));
		}

		public static RGBColor rgbToYCrCb(RGBColor value) {
			return ColorModel.rgb2ycrcb(value);
		}

		public static RGBColor wrap(RGBColor value) {
			value.wrap();
			return value;
		}

		public static RGBColor yCrCbtoRGB(RGBColor value) {
			return ColorModel.ycrcb2rgb(value);
		}

		public static RGBColor perlinColor(RGBColor left, RGBColor right) {
			
			return PerlinNoise.colorNoise(left,right);
		}

		public static RGBColor perlinBW(RGBColor left, RGBColor right) {
			return PerlinNoise.greyNoise(left,right);
		}

		public static RGBColor sum(Expression[] opList,double x, double y, double t) {
			RGBColor result = new RGBColor(0);
			for(Expression op : opList)
			{   if(op!=null)
				result = add(result, op.evaluate(x,y,t));
			else 
				break;
			}
			return result;
		}

		public static RGBColor product(Expression[] opList, double x, double y,double t) {
			RGBColor result = new RGBColor(0);
			for(Expression op : opList)
			{   if(op!=null)
				result = multiply(result, op.evaluate(x,y,t));
			else 
				break;
			}
			return result;
		}

		public static RGBColor average(Expression[] opList, double x, double y, double t) {
			RGBColor result = new RGBColor(0);
			int i = 0;
			for(Expression op : opList)
			{   if(op!=null)
				{result = add(result, op.evaluate(x,y,t));
				 i++;}
			else 
				break;
			}
			return new RGBColor(result.getRed()/i,
					            result.getGreen()/i,
					            result.getBlue()/i);
		}

		public static RGBColor min(Expression[] opList, double x, double y, double t) {
			RGBColor result = new RGBColor(1);
			for(Expression op : opList)
			{   if(op!=null)
			    {
				RGBColor current = op.evaluate(x, y, t);
				if(current.compareTo(result)<0)result = current;
			    }
			else 
				break;
			}
			return result;
		}

		public static RGBColor max(Expression[] opList, double x, double y, double t) {
			RGBColor result = new RGBColor(-1);
			for(Expression op : opList)
			{   if(op!=null)
			    {
				RGBColor current = op.evaluate(x, y, t);
				if(current.compareTo(result)>0)result = current;
			    }
			else 
				break;
			}
			return result;
		}

		public static RGBColor ifcase(Expression[] opList, double x, double y, double t) {
			if(opList[0].evaluate(x,y,t).compareTo(new RGBColor (0))>0)
			{
				return opList[1].evaluate(x,y,t);
			}
			return opList[2].evaluate(x,y,t);
		}


}
