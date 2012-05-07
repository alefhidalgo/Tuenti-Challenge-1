import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 6: The clock
 * 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TheClock {

	public int led[];
	public int[] ledMap;
	{
		ledMap = new int[10];
		ledMap[0] = 6;
		ledMap[1] = 2;
		ledMap[2] = 5;
		ledMap[3] = 5;
		ledMap[4] = 4;
		ledMap[5] = 5;
		ledMap[6] = 6;
		ledMap[7] = 3;
		ledMap[8] = 7;
		ledMap[9] = 6;
		led = new int[6];
	}

	private int getLeds() {
		int sum = 0;
		for (int d : led) {
			sum += ledMap[d];
		}
		return sum;
	}

	private void setTime(int h, int m, int s) {
		String shours = String.valueOf(h);
		String smin = String.valueOf(m);
		String ssec = String.valueOf(s);
		if (shours.length() > 1) {
			led[4] = Integer.parseInt(shours.substring(1, 2));
			led[5] = Integer.parseInt(shours.substring(0, 1));
		} else {
			led[4] = Integer.parseInt(shours.substring(0, 1));
			led[5] = 0;
		}

		if (smin.length() > 1) {
			led[2] = Integer.parseInt(smin.substring(1, 2));
			led[3] = Integer.parseInt(smin.substring(0, 1));
		} else {
			led[2] = Integer.parseInt(smin.substring(0, 1));
			led[3] = 0;
		}

		if (ssec.length() > 1) {
			led[0] = Integer.parseInt(ssec.substring(1, 2));
			led[1] = Integer.parseInt(ssec.substring(0, 1));
		} else {
			led[0] = Integer.parseInt(ssec.substring(0, 1));
			led[1] = 0;
		}
	}

	public int getTotalLeds(int segs) {
		int sum = 0;
		for (int i = 0; i <= segs; i++) {
			int seg = i % 60;
			int min = (i / 60) % 60;
			int hours = (i / 3600) % 12;
			setTime(hours, min, seg);
			sum += getLeds();
		}
		return sum;
	}

	public static void main(String args[]) {
		TheClock theClock = new TheClock();
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			System.out.println(theClock.getTotalLeds(in.nextInt()));
		}
	}
}