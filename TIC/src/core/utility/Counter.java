package core.utility;

/**
 * Counter of cycles
 * 
 * @author Maxime
 *
 */
public class Counter {
	
	private static Counter instance;
	private int turn;
	
	private Counter(){
		turn = 0;
	}
	
	public static Counter newInstance(){
		instance = new Counter();
		return instance;
	}
	
	public static Counter getInstance(){
		if (instance != null) return instance;
		else return new Counter();
	}
	
	public void increment(){
		turn++;
	}
	
	public int getValue(){
		return turn;
	}

}
