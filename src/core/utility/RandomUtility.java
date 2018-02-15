package core.utility;

import java.util.Random;

public class RandomUtility {
	
	/**
	 * Generate a random number beetwen 
	 * @param min the inferior born
	 * @param max the maximum born
	 * @return a random number
	 */
	public static int rand(int min, int max) {
        Random rnd = new Random();
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }
	
}
