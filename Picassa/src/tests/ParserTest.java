package tests;

import java.awt.Color;
import model.Parser;
import model.ParserException;
import model.RGBColor;
import static org.junit.Assert.*;
import org.junit.*;


public class ParserTest
{
    // useful constants
    private static final RGBColor BLACK = new RGBColor(Color.BLACK);
    private static final RGBColor GRAY = new RGBColor(0.5);
    private static final RGBColor WHITE = new RGBColor(Color.WHITE);

    
    private Parser myParser = new Parser();


    @Before
    public void setUp ()
    {
        // initialize stuff here
    }


    @Test
    public void testConstant ()
    {
        RGBColor actual = myParser.makeExpression("1",0).evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("-1",0).evaluate(0,0,0);
        assertTrue(BLACK.equals(actual));
        actual = myParser.makeExpression("0.5",0).evaluate(0,0,0);
        assertTrue(GRAY.equals(actual));
        actual = myParser.makeExpression(".5",0).evaluate(0,0,0);
        assertTrue(GRAY.equals(actual));
        try
        {
            myParser.makeExpression("0.5 0.5",0).evaluate(0,0,0);
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            // actually that's good
            assertEquals(ParserException.Type.EXTRA_CHARACTERS, e.getType());
        }
        assertTrue(GRAY.equals(actual));
    }


    @Test
    public void testBinaryOps ()
    {
        RGBColor actual = myParser.makeExpression("(plus .1 .9)",0).evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(plus (plus 0.01 0.09) (plus 0.4 0.5))",0).evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("    (plus(plus 0.01 0.09)(plus 0.4 0.5   ))    ",0).evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        actual = myParser.makeExpression("(minus (div 1.8 2) (mul -10 0.01))",0).evaluate(0,0,0);
        assertTrue(WHITE.equals(actual));
        try
        {
            myParser.makeExpression("(fooo 0.1 0.9)",0).evaluate(0,0,0);
            fail("Exception should have been thrown");
        }
        catch (ParserException e)
        {
            // actually that's good
            assertEquals(ParserException.Type.UNKNOWN_COMMAND, e.getType());
        }
    }
}
