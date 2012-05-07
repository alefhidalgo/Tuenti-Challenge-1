import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 7: The Tile Game
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TheTileGame {
	
	public int levenshtein(char[] string1, char[] string2, int costSub, int costIn, int costDel) {
		int len1 = string1.length;
		int len2 = string2.length;
		int[] row0 = new int[len2 + 1];
		int[] row1 = new int[len2 + 1];
		int[] row2 = new int[len2 + 1];
		int i, j;

		for (j = 0; j <= len2; j++)
			row1[j] = j * costIn;
		for (i = 0; i < len1; i++) {
			int[] dummy;

			row2[0] = (i + 1) * costDel;
			for (j = 0; j < len2; j++) {
				/* substitution */
				row2[j + 1] = row1[j] + costSub
						* ((string1[i] != string2[j]) ? 1 : 0);
				/* deletion */
				if (row2[j + 1] > row1[j + 1] + costDel)
					row2[j + 1] = row1[j + 1] + costDel;
				/* insertion */
				if (row2[j + 1] > row2[j] + costIn)
					row2[j + 1] = row2[j] + costIn;
			}

			dummy = row0;
			row0 = row1;
			row1 = row2;
			row2 = dummy;
		}

		i = row1[len2];

		return i;
	}


	public static void main(String args[]) throws UnsupportedEncodingException {		
		TheTileGame theTileGame = new TheTileGame();
		Scanner in = new Scanner(new InputStreamReader(System.in, "UTF-8"));	
		Scanner lineScanner = null;
		Scanner intScanner = null;
		String tileA = null;
		String tileB = null;
		int costIn, costDel, costSub;
		while (in.hasNextLine()) {
			lineScanner = new Scanner(in.nextLine());
			tileA = ""+lineScanner.next();
			tileB= ""+lineScanner.next();
			intScanner = new Scanner(lineScanner.next());
			intScanner.useDelimiter(",");
			costIn = intScanner.nextInt();
			costDel = intScanner.nextInt();
			costSub = intScanner.nextInt();		
			System.out.println(theTileGame.levenshtein(tileA.toCharArray(), tileB.toCharArray(), costSub, costIn, costDel));			
		}
		
	}
}