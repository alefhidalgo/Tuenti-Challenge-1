import java.util.Scanner;
/**
 * Tuenti Programming Contest 
 * Challenge 13: The other clock
 * @author alefhidalgo [at] gmail [dot] com
 */
public class TheOtherClock {
	
	public static final int SECOND_ZERO_LEDS = 36; /*Total leds ON at t=0*/
	private int[] ledDisplay;
	private int[] lastLedDisplay;	
	{	/* initialization block */	
		ledDisplay = new int[6];
		lastLedDisplay = new int[6];
	}
	
	/**
	 * getLedsVariations: Get led variations between possible digits transition
	 * @param lastDigit
	 * @param newDigit
	 * @return
	 */
	private int getLedsVariations(int lastDigit, int newDigit) {
		//all possible transitions in a led display
		if(lastDigit == 0 && newDigit == 1) return 0;
		if(lastDigit == 1 && newDigit == 2)	return 4;
		if(lastDigit == 2 && newDigit == 3)	return 1;
		if(lastDigit == 3 && newDigit == 4)	return 1;
		if(lastDigit == 4 && newDigit == 5)	return 2;
		if(lastDigit == 5 && newDigit == 6)	return 1;
		if(lastDigit == 6 && newDigit == 7)	return 1;
		if(lastDigit == 7 && newDigit == 8)	return 4;
		if(lastDigit == 8 && newDigit == 9)	return 0;
		if(lastDigit == 9 && newDigit == 0)	return 1;
		if(lastDigit == 5 && newDigit == 0)	return 2;
		return 0; /*no variations*/
	}
	
	/**
	 * Get Total led variations between last and new state
	 * @return
	 */
	private int getTotalVariations(){
		int sumVariations = 0;
		for (int i = 0; i < ledDisplay.length; i++) {			
			sumVariations += getLedsVariations (lastLedDisplay[i], ledDisplay[i]);			
		}
		return sumVariations;
	}
		
	/**
	 * Set time in the led Display
	 * @param h
	 * @param m
	 * @param s
	 */
	private void setTime(int h, int m, int s) {
		String sHours = String.valueOf(h);
		String sMin = String.valueOf(m);
		String sSec = String.valueOf(s);
		if (sHours.length() > 1) {
			ledDisplay[4] = Integer.parseInt(sHours.substring(1, 2));
			ledDisplay[5] = Integer.parseInt(sHours.substring(0, 1));
		} else {
			ledDisplay[4] = Integer.parseInt(sHours.substring(0, 1));
			ledDisplay[5] = 0;
		}

		if (sMin.length() > 1) {
			ledDisplay[2] = Integer.parseInt(sMin.substring(1, 2));
			ledDisplay[3] = Integer.parseInt(sMin.substring(0, 1));
		} else {
			ledDisplay[2] = Integer.parseInt(sMin.substring(0, 1));
			ledDisplay[3] = 0;
		}

		if (sSec.length() > 1) {
			ledDisplay[0] = Integer.parseInt(sSec.substring(1, 2));
			ledDisplay[1] = Integer.parseInt(sSec.substring(0, 1));
		} else {
			ledDisplay[0] = Integer.parseInt(sSec.substring(0, 1));
			ledDisplay[1] = 0;
		}
		
	}
	
	/**
	 * getTotalLedsOn 
	 * 	Get total leds ON in a 7 display clock from t=0 to t=seconds
	 * @param seconds
	 * @return
	 */
	public int getTotalLedsOn(int seconds) {
		int sumLedsOn = TheOtherClock.SECOND_ZERO_LEDS;		
		for (int i = 0; i <= seconds; i++) {
			int seg = i % 60;
			int min = (i / 60) % 60;
			int hours = (i / 3600) % 12;
			setTime(hours, min, seg);								
			if(i>0) {
				//get only variations between off/on leds
				sumLedsOn += getTotalVariations();
			}
			//save the last display state in order to detect the variations
			System.arraycopy(ledDisplay, 0, lastLedDisplay, 0, ledDisplay.length);
		}
		return sumLedsOn;
	}
	
	/* For Debug purpose */
//	private void printLedDisplay() {
//		System.out.println(ledDisplay[5] + "" + ledDisplay[4] + ":" + ledDisplay[3] + "" + ledDisplay[2] + ":" + ledDisplay[1] + ""	+ ledDisplay[0]);
//	}
//	
	public static void main(String args[]) {
		TheOtherClock theClock = new TheOtherClock();		
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			System.out.println(theClock.getTotalLedsOn(in.nextInt()));
		}
	}
}