import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Tuenti Programming Contest 
 * Challenge 3: Emirps 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class Emirps {
	
	/* Pre-computated Emirps */
	private SortedSet<Long> preComputedEmirps;
	/* Last pre-computated up to */
	private long lastComputedUpTo = 0;
	/**
	 * Constructor	 
	 */
	public Emirps() {
		preComputedEmirps = new TreeSet<Long>();
	}
	/**
	 * Pre-computated Emirps numbers for a best performance
	 * @param from
	 * @param to
	 */
	private void preComp(long from, long to) {
		for (long i = from; i < to; i++) {
			if (isEmirp(i)) {				
				preComputedEmirps.add(i);
			}
		}
		lastComputedUpTo = to;
	}
	/**
	 * IsPrime
	 * @param n
	 * @return
	 */
	private boolean isPrime(Long n) {
		if (n < 2)
			return false;
		if (n == 2 || n == 3)
			return true;
		if (n % 2 == 0 || n % 3 == 0)
			return false;
		long sqrtN = (long) Math.sqrt(n) + 1;
		for (long i = 6L; i <= sqrtN; i += 6) {
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return false;
		}
		return true;
	}
	/**
	 * IsEmirp
	 * @param n
	 * @return
	 */
	private boolean isEmirp(Long n) {
		Long reverse = new Long(new StringBuffer(n.toString()).reverse().toString());
		return isPrime(n) && isPrime(reverse) && n.compareTo(reverse)!=0;
	}
	
	/**
	 * Calculate emirps sum
	 * @param number
	 * @return
	 */
	public Long calculate(Long number) {
		long result = 0;
		if (number > lastComputedUpTo) {
			preComp(lastComputedUpTo, number);
		}
		for (Long i : preComputedEmirps) {
			if (i < number) {
				result += i;
			}else {
				break;
			}
		}
		return result;
	}

	public static void main(String args[]) {
		Emirps emirps = new Emirps();
		Scanner in = new Scanner(System.in);
		 while (in.hasNextLine()) {
			 System.out.println(emirps.calculate(in.nextLong())); 
		 }
		
	}
}