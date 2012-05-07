import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Tuenti Programming Contest 
 * Challenge 18: The Riddle
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TheRiddle {
	
	/* Secret text constants*/
	private static final String HIDDEN_BEGIN_STRING =  "you can also answer this simple question to solve the challenge: how much is";
	private static final String HIDDEN_END_STRING =  "? Crack!";
	private BufferedImage image;
	
	/**
	 * Constructor
	 * @param image
	 */
	public TheRiddle(BufferedImage image){
		this.image = image;	
	}
		
	/**
	 * Get Hidden solution from image
	 *  - Read binary value for all stripes pixels until red begin (skipping green background)
	 *  - Convert binary string to legible ascii grouping in bytes
	 *  - Search the problem statement ( A operation B)
	 *  
	 * @return The solution for the given expression.
	 */
	public String getHiddenSolution(){
		int x = image.getWidth();
		int y = image.getHeight();
		StringBuilder sbDecoded = new StringBuilder();
	f1: for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				int pixel = image.getRGB(i, j); //Get pixel value		
				int r = (pixel >> 16) & 0xff;			
				int g  =(pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;			
				if(r==255) break f1; /* until red */
				if(g!=255){ /* skip green */
					//append 8byte -> ascii chars
					sbDecoded.append((char)r);
					sbDecoded.append((char)g);
					sbDecoded.append((char)b);													
				}			 
			}
		
		}		
		//Get secret expression to solve (A operation B)
		int indexBeginCode= sbDecoded.indexOf(TheRiddle.HIDDEN_BEGIN_STRING) + TheRiddle.HIDDEN_BEGIN_STRING.length();
		int indexEndCode = sbDecoded.indexOf(TheRiddle.HIDDEN_END_STRING);		
		return evalExpression(sbDecoded.substring(indexBeginCode,indexEndCode).trim());		
	}	
	
	/**
	 * evalExpression
	 * 	Eval expression using JS engine
	 *  	A + B
	 *  	A - C
	 *  	A / C
	 *  	...
	 * @param expression to solve
	 * @return solved expression value
	 */
	private String evalExpression(String expression){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Double ret = null;
        try {
			ret = (Double) engine.eval(expression);
		} catch (ScriptException e) {
			/* Never should happen */
			return "NOT A VALID EXPRESSION";			
		}
		return String.valueOf(ret.intValue());
	}
	
	
	public static void main(String[] s) {		
		BufferedImage image= null;
		BASE64DecoderStream decoder = new BASE64DecoderStream(System.in);
		try {
			image = ImageIO.read(decoder);
		} catch (IOException e) {
			/* Never should happen */
		}
		TheRiddle theRiddle = new TheRiddle(image);
		System.out.println(theRiddle.getHiddenSolution()); 
	}

}