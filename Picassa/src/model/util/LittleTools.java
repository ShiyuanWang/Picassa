package model.util;

public class LittleTools {
private String myInput;
     public LittleTools(String input)
     {
    	 myInput = input;
     }
	 public boolean notAtEndOfString (int myCurrentPosition)
	    {
		 return myCurrentPosition < myInput.length();
	    }
	 
	  public char currentCharacter (int myCurrentPosition)
	    {
	        return myInput.charAt(myCurrentPosition);
	    }
	  public String toString()
	  {
		  return "tool";
	  }

	    public int skipWhiteSpace (int myCurrentPosition)
	    {   
	        while (notAtEndOfString(myCurrentPosition) && Character.isWhitespace(currentCharacter(myCurrentPosition)))
	        {
	            myCurrentPosition++;
	        }
	    return myCurrentPosition;
	    }
}
