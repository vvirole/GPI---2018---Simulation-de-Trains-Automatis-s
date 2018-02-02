package utility;

import java.util.Random;

public class RandomNumber {
	
	public static int rand(int i, int j) {
        Random rnd = new Random();
        int number = i + rnd.nextInt(j - i + 1);
        return number;
    }
	
}
