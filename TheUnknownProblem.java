import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Tuenti Programming Contest 
 * Challenge 17: The ¿? Problem
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TheUnknownProblem {
	
	/* Secret text */
	private static final String HIDDEN_STRING = "Good job crack! You have done well :) To finish this challenge you have to write the program that submits the following code: ";
	/* Secret code length */
	private static final int LONG_SECRET_CODE = 40;
	private BufferedImage image;
	
	/**
	 * Constructor
	 * @param image
	 */
	public TheUnknownProblem(BufferedImage image){
		this.image = image;
	}
	
	/**
	 * Get Hidden Code from image
	 * @return
	 */
	public String getHiddenCode(){
		int x = image.getWidth();
		int y = image.getHeight();
		StringBuilder sbLSB = new StringBuilder();
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				int pixel = image.getRGB(j, i);
				
				int r = (pixel >> 16) & 0xff;			
				int g  =(pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;
				String sr = Integer.toBinaryString(r);
				String sg = Integer.toBinaryString(g);
				String sb = Integer.toBinaryString(b);
				/* Get Less Significative Bit and append in order BLUE,GREEN,REED*/				
				sbLSB.append(sb.substring(sb.length()-1, sb.length())); 
				sbLSB.append(sg.substring(sg.length()-1, sg.length()));
				sbLSB.append(sr.substring(sr.length()-1, sr.length()));
			}
		}		
		StringBuilder decoded = new StringBuilder();		
		for(int i=0; i<sbLSB.length();i+=8) {			 
		 decoded.append((char)Integer.parseInt(sbLSB.substring(i,i+8), 2));	
		}
		//get secret CODE
		int indexCode= decoded.indexOf(TheUnknownProblem.HIDDEN_STRING) + TheUnknownProblem.HIDDEN_STRING.length();		
		return decoded.substring( indexCode ,indexCode + LONG_SECRET_CODE);		
	}	
	
	public static void main(String[] s) {		
		BufferedImage image= null;
		BASE64DecoderStream decoder = new BASE64DecoderStream(System.in);
		try {
			image = ImageIO.read(decoder);
		} catch (IOException e) {
			/* Never should happen */
		}
		TheUnknownProblem theUnknowProblem = new TheUnknownProblem(image);	
		System.out.println(theUnknowProblem.getHiddenCode());		    
	}
}