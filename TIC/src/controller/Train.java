package controller;

import java.util.HashMap;
import java.util.Map;

public class Train extends Thread{

	//List of the different train
	public final static int SHORT_TYPE = 0;
	public final static int LONG_TYPE = 1;
	
	
	//Maximum capacity of the Train
	private int maxPassengers;
		
	//Number of current passenger
	private int currentPassenger;
	
	//Canton of the train
	private Canton currentCanton;
	
	//Current position in the Canton
	private int currentPosition;
	
	//Type of the train
	private int type;
	
	//Contain the number of the passenger and the Station where they want to go
	private Map<Integer,Integer> destination = new HashMap<Integer,Integer>();

	public Train(int currentPassenger, Canton currentCanton, int currentPosition, int type,
			Map<Integer, Integer> destination) {
		super();
		this.currentPassenger = currentPassenger;
		this.currentCanton = currentCanton;
		this.currentPosition = currentPosition;
		this.type = type;
		this.destination = destination;
		
		//MaxPassenger depend of the type of the train
	}
	
	
	
	
	
}
