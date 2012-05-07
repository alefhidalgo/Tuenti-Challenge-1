import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 9: ChristmasLights
 * @author alefhidalgo [at] gmail [dot] com
 */
public class ChristmasLights {

	public static final String ALL_OF = "All lights are off :(";

	/**
	 * optimizedTurnLights -When time > nLigths, even and odd lights are lighted
	 * On/off
	 * 
	 * @param nLights
	 * @param time
	 * @return
	 */
	public String optimizedTurnLights(int nLights, long time) {
		if (time < (nLights + 2)) {
			return turnLights(nLights, time);
		} else {
			int aux = (int) ((time - nLights) % 2);
			long newTime = nLights + aux; // all lights switch between even and odd from t=nLigths
			return turnLights(nLights, newTime);
		}

	}

	private String turnLights(int nLights, long time) {
		String ret = "";
		boolean[] lights = new boolean[nLights];
		lights[0] = true;
		for (int t = 1; t < time; t++) {
			turn(lights);
		}
		for (int i = 0; i < lights.length; i++) {
			if (lights[i]) {
				ret += ((ret != "") ? " " : "") + i;
			}
		}
		return ret.length() > 0 ? ret : ChristmasLights.ALL_OF;
	}

	private void turn(boolean[] ligths) {
		for (int i = 0; i < ligths.length; i++) {
			if (ligths[i]) {
				if (i < ligths.length - 1) {
					ligths[i + 1] = true;
				}
				if (i > 0) {
					ligths[i - 1] = true;
				}
				ligths[i] = false;
				i++;
			}
		}
	}

	public static void main(String args[]) {
		ChristmasLights christmasLights = new ChristmasLights();
		Scanner in = new Scanner(System.in);
		int nCases = in.nextInt();
		for (int i = 0; i < nCases; i++) {
			System.out.println(christmasLights.optimizedTurnLights(in.nextInt(), in.nextLong()));
		}
	}
}