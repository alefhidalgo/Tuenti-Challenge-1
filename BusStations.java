import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 16: The Bus Stations
 * 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class BusStations {
	/* Required variables for Edmonds-Karp algorithm */
	public static final int WHITE = 0, GRAY = 1, BLACK = 2;
	private int[][] flow, capacity, residualCapacity;
	private int[] parent, color, queue;
	private int[] minCapacity;
	private int size, sourceStation, sinkStation;
	
	private Map<String, Integer> stationsMap; /* Map Station->Index */
	private int stationsIndexCounter = 0;
	
	/**
	 * Constructor
	 * @param n Number of stations
	 * @param stationFrom
	 * @param stationTo
	 */
	public BusStations(int n, String stationFrom, String stationTo) {
		this.size = n;
		capacity = new int[n][n];
		stationsMap = new HashMap<String, Integer>(n);		
		sourceStation = getNodeIndex(stationFrom);
		sinkStation = getNodeIndex(stationTo);
	}
	
	/**
	 * Edmonds-Karp algorithm with O(V³E) complexity
	 * 
	 * @return maxFlow between stationFrom to stationTo
	 */
	public int maxFlow() {
		int maxFlow = 0;
		flow = new int[size][size];
		residualCapacity = new int[size][size];
		parent = new int[size];
		minCapacity = new int[size];
		color = new int[size];
		queue = new int[size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				residualCapacity[i][j] = capacity[i][j];

		while (BFS(sourceStation)) {
			maxFlow += minCapacity[sinkStation];
			int v = sinkStation, u;
			while (v != sourceStation) {
				u = parent[v];
				flow[u][v] += minCapacity[sinkStation];
				flow[v][u] -= minCapacity[sinkStation];
				residualCapacity[u][v] -= minCapacity[sinkStation];
				residualCapacity[v][u] += minCapacity[sinkStation];
				v = u;
			}
		}

		return maxFlow;
	}
	/**
	 * addStationLink Add road between two stations with a maxFlow
	 * 
	 * @param stationA
	 * @param stationB
	 * @param maxFlow
	 */
	public void addStationLink(String stationA, String stationB, int maxFlow) {
		int indexA = getNodeIndex(stationA);
		int indexB = getNodeIndex(stationB);
		capacity[indexA][indexB] = maxFlow;
	}


	
	/**
	 * Get node index from stationName
	 * 	stationName is mapped if station does not exists in stationsMap
	 * @param stationName
	 * @return
	 */
	private int getNodeIndex(String stationName) {
		int pos = -1;
		if (stationsMap.containsKey(stationName)) {
			pos = stationsMap.get(stationName);
		} else {
			pos = stationsIndexCounter++;
			stationsMap.put(stationName, pos);
		}
		return pos;
	}

	
	/**
	 * BFS Breadth First Search in O(V<sup>2</sup>)
	 * @param source
	 * @return
	 */
	private boolean BFS(int source) {
		int first, last;		
		for (int i = 0; i < size; i++) {
			color[i] = WHITE;
			minCapacity[i] = Integer.MAX_VALUE;
		}
		first = last = 0;
		queue[last++] = source;
		color[source] = GRAY;
		
		// While "queue" not empty..
		while (first != last) {
			int v = queue[first++];
			for (int u = 0; u < size; u++)
				if (color[u] == WHITE && residualCapacity[v][u] > 0) {
					minCapacity[u] = Math.min(minCapacity[v],	residualCapacity[v][u]);
					parent[u] = v;
					color[u] = GRAY;
					if (u == sinkStation)
						return true;
					queue[last++] = u;
				}
		}
		return false;
	}

	public static void main(String args[]) {
		BusStations busStations = null;
		Scanner scanner = new Scanner(System.in);
		Scanner lineScanner = null;
		Scanner stationsScanner = null;
		while (scanner.hasNextLine()) {
			lineScanner = new Scanner(scanner.nextLine());
			int nStations = Integer.parseInt(lineScanner.next());
			String stationFrom = lineScanner.next();
			String stationTo = lineScanner.next();
			/* new BusStations */
			busStations = new BusStations(nStations, stationFrom, stationTo);
			while (lineScanner.hasNext()) {
				stationsScanner = new Scanner(lineScanner.next());
				stationsScanner.useDelimiter(",");
				String stationA = stationsScanner.next();
				String stationB = stationsScanner.next();
				int maxFlow = Integer.parseInt(stationsScanner.next());
				/* Add link between stations */
				busStations.addStationLink(stationA, stationB, maxFlow);
			}
			//Print maxFlow 
			System.out.println(busStations.maxFlow());
		}

	}
}