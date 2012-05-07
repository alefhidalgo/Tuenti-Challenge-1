import java.util.Scanner;
/**
 * Tuenti Programming Contest 
 * Challenge 8: The Biologist Problem
 * @author alefhidalgo [at] gmail [dot] com
 */
public class BiologistProblem {
	
	/**
	 * maxPattern returns max pattern between a and b
	 * @param a
	 * @param b
	 * @return
	 */
	public static String maxPattern(String a, String b) {
		String cad = a;
		String pattern = b;
		if (b.length() > a.length()) {
			cad = b;
			pattern = a;
		}
		String sub = null;
		String maxPattern = "";
		for (int i = pattern.length(); i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				sub = pattern.substring(j, i);
				if (cad.contains(sub)) {
					if (sub.length() > maxPattern.length()) {
						maxPattern = sub;
					}
				}
			}
		}
		return maxPattern;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Scanner lineScanner = null;
		String seqA = null;
		String seqB = null;
		while (in.hasNextLine()) {
			lineScanner = new Scanner(in.nextLine());
			seqA = lineScanner.next();
			seqB = lineScanner.next();
			System.out.println(maxPattern(seqA, seqB));
		}

	}
}