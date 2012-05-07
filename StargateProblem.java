import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Tuenti Programming Contest 
 * Challenge 12: The Stargate Problem
 * @author alefhidalgo [at] gmail [dot] com
 */
public class StargateProblem {
	
	public static final int BASE_TIME = 25000; /* Base Time */
	public static final String BAZINGA = "BAZINGA";
	public static final long INFINITY = 999999999999999999L;
	
	/** Edge Class */
	private static class Edge {
		int source;
		int dest;
		int weight;
		public Edge(int source,int dest, int weigth){
			this.source = source;
			this.dest = dest;
			this.weight = weigth;			
		}
	}
	/**
	 * Modified Bellman - Ford Algorithm
	 * @param edges
	 * @param nodecount
	 * @param source
	 * @param destiny
	 */
	public static void doBellmanFord(List<Edge> edges, int nodecount, int earthIndex, int atlantisIndex) {		
	   long []distance = new long[nodecount];
	   int edgecount = edges.size();
	   for (int i=0; i < nodecount; i++) {
	     distance[i] = INFINITY; 
	   }	  
	   distance[earthIndex] = 0;	  
	   for (int i=0; i < nodecount; i++) {
	       for (int j=0; j < edgecount; j++) {
	           if (distance[edges.get(j).source] != INFINITY) {
	        	   long new_distance = distance[edges.get(j).source] + edges.get(j).weight;
	               if (new_distance < distance[edges.get(j).dest])
	                 distance[edges.get(j).dest] = new_distance;
	           }
	       }
	   }
	   for (int i=0; i < edgecount; i++) {
	       if (distance[edges.get(i).dest] > distance[edges.get(i).source] + edges.get(i).weight) {
	    	   System.out.println("BAZINGA");   
	           return;
	       }
	   }
	   System.out.println(distance[atlantisIndex] == StargateProblem.INFINITY ? BAZINGA : (distance[atlantisIndex] + BASE_TIME));
	}


	public static void main(String args[]){	  
	   List<Edge> edges = null;
	   Scanner in = new Scanner(System.in);
	   Scanner lineScanner = null;
	   Scanner plannetScanner = null;
	   int nPlanets = 0;
	   int earthIndex = 0;
	   int atlantisIndex = 0;
	   while(in.hasNextLine()){
		   lineScanner = new Scanner(in.nextLine());
		   nPlanets = lineScanner.nextInt();
		   earthIndex = lineScanner.nextInt();
		   atlantisIndex = lineScanner.nextInt();
		   edges = new ArrayList<Edge>();
		   while(lineScanner.hasNext()){
			   plannetScanner = new Scanner(lineScanner.next());
			   plannetScanner.useDelimiter(",");
			   edges.add(new Edge(plannetScanner.nextInt(),plannetScanner.nextInt(),plannetScanner.nextInt()));				   
		   }		
		   doBellmanFord(edges, nPlanets+1, earthIndex, atlantisIndex);
	   }
		
	}
}