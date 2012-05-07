import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 11: Gas stations
 * @author alefhidalgo [at] gmail [dot] com
 */
public class GasStations {
     
	public static final String NO_STOPS = "No stops";

	public String calculateGasStations(long autonomy, long totalDistanceToTravel, List<Long> distances) {
		if (autonomy >= totalDistanceToTravel) {
			return GasStations.NO_STOPS;
		}
		StringBuilder sbfGasStation = new StringBuilder();
		if(distances.size() == 1){
			return String.valueOf(distances.get(0));
		}		
		long lastDistance = 0;
		for (int i = 1; i < distances.size(); i++) {
			if ((distances.get(i) - lastDistance) > autonomy) {
				sbfGasStation.append((sbfGasStation.length() > 0 ? " " : "") + distances.get(i - 1));
				lastDistance = distances.get(i - 1);
				if ((lastDistance + autonomy) >= totalDistanceToTravel) {
					break;
				}
			}
		}
		return sbfGasStation.toString();
	}

	public static void main(String args[]) {
		GasStations gasStations = new GasStations();
		long autonomy;
		long totalDistanceToTravel;
		List<Long> distances;
		Scanner in = new Scanner(System.in);
		Scanner lineScanner = null;
		int nCases = Integer.parseInt(in.nextLine().trim());
		for (int i = 0; i < nCases; i++) {
			autonomy = Integer.parseInt(in.nextLine().trim());
			totalDistanceToTravel = Integer.parseInt(in.nextLine().trim());
			int totalDistances = Integer.parseInt(in.nextLine().trim());
			distances = new ArrayList<Long>(totalDistances);
			lineScanner = new Scanner(in.nextLine());
			for (int j = 0; j < totalDistances; j++) {
				distances.add(lineScanner.nextLong());
			}
			System.out.println(gasStations.calculateGasStations(autonomy, totalDistanceToTravel, distances));
		}
	}
}