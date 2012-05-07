import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Tuenti Programming Contest 
 * Challenge 15: The Robot Known as Alfonso D. Artiste Jr.
 * @author alefhidalgo [at] gmail [dot] com
 */
public class Robot {

	private int w; // wide
	private int l; // long
	private int[][] canvas;
	private LinkedHashMap<Integer, ColorCount> colorsMap;

	/**
	 * Constructor	
	 * @param w
	 * @param l
	 * @param n
	 */
	public Robot(int w, int l, int n) {
		this.w = w;
		this.l = l;
		canvas = new int[l][w];
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < w; j++) {
				canvas[i][j] = 1;
			}
		}
		colorsMap = new LinkedHashMap<Integer, ColorCount>(n);
	}

	/**
	 * Add rectangle to the cavas
	 * 
	 * @param rectangle
	 */
	public void addRectangle(Rectangle rectangle) {
		for (int i = rectangle.ly; i <= rectangle.uy; i++) {
			for (int j = rectangle.lx; j <= rectangle.ux; j++) {
				canvas[i][j] = rectangle.color;
			}
		}
	}

	/**
	 * printColorsCount
	 */
	public void printColorsCount() {
		for (int i = 0; i < l; i++) {
			for (int j = 0; j < w; j++) {
				int c = canvas[i][j];
				if (colorsMap.containsKey(c)) {
					colorsMap.get(c).increase();
				} else {
					colorsMap.put(c, new ColorCount(c));
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		// Order and print colors count
		TreeSet<Integer> orderSet = new TreeSet<Integer>(colorsMap.keySet());
		for (Integer i : orderSet) {
			sb.append(colorsMap.get(i).color);
			sb.append(" ");
			sb.append(colorsMap.get(i).totalInCanvas);
			sb.append(" ");
		}
		System.out.println(sb.toString().trim());
	}
	
	/* PRINT - (FOR DEBUG PURPOSE)*/
	public void print() {
		for (int i = (l - 1); i >= 0; i--) {
			for (int j = 0; j < w; j++) {
				System.out.print(canvas[i][j]);
			}
			System.out.println();
		}
	}

	/* Class Rectangle */
	private static class Rectangle {
		int lx;
		int ly;
		int ux;
		int uy;
		int color;

		public Rectangle(int lx, int ly, int ux, int uy, int color) {
			this.lx = lx;
			this.ly = ly;
			this.ux = ux - 1;
			this.uy = uy - 1;
			this.color = color;
		}
	}

	/* ColorCount class */
	private static class ColorCount {
		int color;
		int totalInCanvas;

		public ColorCount(int color) {
			this.totalInCanvas = 1;
			this.color = color;
		}

		public void increase() {
			totalInCanvas++;
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Scanner lineScanner = null;
		Robot robot = null;
		int w, l, n, lx, ly, ux, uy, color;		
		while (scanner.hasNextLine()) {
			lineScanner = new Scanner(scanner.nextLine());
			w = lineScanner.nextInt();
			l = lineScanner.nextInt();
			n = lineScanner.nextInt();
			robot = new Robot(w, l, n);
			for (int i = 0; i < n; i++) {
				lx = lineScanner.nextInt();
				ly = lineScanner.nextInt();
				ux = lineScanner.nextInt();
				uy = lineScanner.nextInt();
				color = lineScanner.nextInt();
				robot.addRectangle(new Rectangle(lx, ly, ux, uy, color));
			}
			robot.printColorsCount();
		}

	}
}