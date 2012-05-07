import java.math.BigDecimal;
import java.util.Scanner;
/**
 * Super Hard Sum
 * @author alefhidalgo [at] gmail [dot] com
 */

public class SuperHardSum {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Scanner lineScanner = null;
		BigDecimal sum = BigDecimal.ZERO;
		while (in.hasNextLine()) {
			lineScanner = new Scanner(in.nextLine());
			while (lineScanner.hasNextBigDecimal()) {
				sum = sum.add(lineScanner.nextBigDecimal());
			}
			System.out.println(sum);
			sum = BigDecimal.ZERO;
		}

	}
}