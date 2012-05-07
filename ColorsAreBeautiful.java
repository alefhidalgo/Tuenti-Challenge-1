import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * Tuenti Programming Contest 
 * Challenge 14: Colors are beautiful * 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class ColorsAreBeautiful {
	private BufferedImage image;
	public static final String IMAGE_RESOURCE = "trabaja.bmp";
	/**
	 * Constructor
	 * Load the image buffer
	 */
	public ColorsAreBeautiful() {
		try {
			image = ImageIO.read(this.getClass().getResource(ColorsAreBeautiful.IMAGE_RESOURCE));
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Returns the sum of the components R,G or B of the line XYZ
	 * @param code RXYZ | GXYZ | BXYZ
	 * @return
	 */
	public int getSum(String code) {
		int w = image.getWidth();
		char component = code.charAt(0);
		int line = Integer.parseInt(code.substring(1));

		int sum = 1;
		for (int j = 0; j < w; j++) {
			int pixel = image.getRGB(j, line);
			switch (component) {
			case 'R':
				sum += (pixel >> 16) & 0xff;
				break;
			case 'G':
				sum += (pixel >> 8) & 0xff;
				break;
			case 'B':
				sum += (pixel) & 0xff;
				break;
			}
		}
		return sum;

	}


	public static void main(String args[]) {
		ColorsAreBeautiful colorsAreBeautiful = new ColorsAreBeautiful();
		Scanner scanner = new Scanner(System.in);	
		while (scanner.hasNextLine()) {
			System.out.println(colorsAreBeautiful.getSum(scanner.nextLine().trim()));
		}
	}

}