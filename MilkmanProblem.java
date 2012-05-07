import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 5: The Milkman Problem
 * @author alefhidalgo [at] gmail [dot] com
 */
public class MilkmanProblem {

	
	private int optimize(int[] ps, int[] bs, int c) {
		int[] partialSol = new int[ps.length];
		int[] optimSol = new int[ps.length];
		int best = search(ps.length - 1, 0, c, 0, partialSol, optimSol, -1, ps,
				bs, c);
		return best;
	}

	private int search(int n_1, int i, int p, int b, int[] partialSol,
			int[] optimSol, int best, int[] ps, int[] bs, int c) {
		for (int k = 0; k <= 1; k++) {
			if (k * ps[i] <= p) {
				partialSol[i] = k;
				int np = p - k * ps[i];
				int nb = b + k * bs[i];
				if (i == n_1) {
					if (nb > best) {
						best = nb;
						for (int j = 0; j < ps.length; j++)
							optimSol[j] = partialSol[j];
					}
				} else {
					best = search(n_1, i + 1, np, nb, partialSol, optimSol,
							best, ps, bs, c);
				}

			}
		}
		return best;
	}
	/**
	 * calculate
	 * @param calculatecowWeight
	 * @param cowMilkProduction
	 * @param truckWeight
	 * @return
	 */
	public int calculate(String[] cowWeight, String[] cowMilkProduction, int truckWeight) {
	   int [] icowMilkProduction=new int[cowMilkProduction.length];
	   int [] icowWeight=new int[cowWeight.length];
	   for (int i = 0; i < cowWeight.length; i++) {
		   icowWeight[i]= Integer.parseInt(cowWeight[i]);
	   }
	   for (int i = 0; i < cowMilkProduction.length; i++) {
		   icowMilkProduction[i]= Integer.parseInt(cowMilkProduction[i]);
	   }
	   return optimize(icowWeight, icowMilkProduction,truckWeight);
   }

	
	public static void main(String args[]) {
		MilkmanProblem milkmanProblem = new MilkmanProblem();
		Scanner in = new Scanner(System.in);
		Scanner lineScanner = null;
		int truckWeight;
		String[] cowWeight = null;
		String[] cowMilkProduction = null;
		while (in.hasNextLine()) {
			lineScanner = new Scanner(in.nextLine());
			lineScanner.next();
			truckWeight = lineScanner.nextInt();
			cowWeight = lineScanner.next().split(",");
			cowMilkProduction = lineScanner.next().split(",");
			System.out.println(milkmanProblem.calculate(cowWeight, cowMilkProduction, truckWeight));
		}
	}
}