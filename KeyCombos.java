import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Tuenti Programming Contest 
 * Challenge 10: Key Combos 
 * @author alefhidalgo [at] gmail [dot] com
 */
public class KeyCombos {
	
	private static final String HEXES = "0123456789ABCDEF";
	private static Map<String,String> combinationMap;
	static {
		combinationMap = new HashMap<String,String>();
	}
	
	/**
	 * Unique Hash Code for each combinationKeys
	 * @param combinationKeys
	 * @return
	 */
	private static String getHashCode(List<String> combinationKeys) {
		Collections.sort(combinationKeys); //all orders are indexes as same hash
		try{
		MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
		String toEncode = "";
		for (String string : combinationKeys) {
			toEncode+=string;
		}
		digest.update(toEncode.getBytes());
	    byte[] hash = digest.digest();
	    return getHex(hash);
		} catch ( NoSuchAlgorithmException e) {
			/* Never should happen */
			return null;
		}
	}
	public static String getHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
	/**
	 * getCommand from combinationKeys
	 * @param combinationKeys
	 * @return
	 */
	private static String getCommand(List<String> combinationKeys){	
		return combinationMap.get(getHashCode(combinationKeys));		
	}
	
	/**
	 * addShortCut into combinationMap
	 * @param commandName
	 * @param combinationKeys
	 */
	private static void addShortCut(String commandName, List<String> combinationKeys){		
		combinationMap.put(getHashCode(combinationKeys), commandName);
	}
	
	public static void main(String args[]) {		
		Scanner in = new Scanner(System.in);
		Scanner lineScanner = null;
		List<String> combinationKeys = null;
		String commandName = null;
		int nKeyCombos = Integer.parseInt(in.nextLine().trim());	
		for(int i=0;i<nKeyCombos;i++){			
			lineScanner = new Scanner(in.nextLine());
			combinationKeys = new ArrayList<String>();
			while(lineScanner.hasNext()){
				combinationKeys.add(lineScanner.next());
			}
			commandName = in.nextLine().trim();
			//Map shortcut
			addShortCut(commandName,combinationKeys);
		}
		int nTestCases = Integer.parseInt(in.nextLine().trim());
		for (int i = 0; i < nTestCases; i++) {
			lineScanner = new Scanner(in.nextLine());
			combinationKeys = new ArrayList<String>();
			while(lineScanner.hasNext()){
				combinationKeys.add(lineScanner.next());
			}
			//return command
			System.out.println(getCommand(combinationKeys));
		}
	}
}