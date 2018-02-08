package core.entity;

import java.util.HashMap;
import java.util.Map;

import core.TerminusException;
import gui.GUIConstants;

public class Train extends Thread {

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
	private int currentPosition = 0;
	
	//Type of the train
	private int type;
	
	//Contain the number of the passenger and the Station where they want to go
	private Map<Integer,Integer> destination = new HashMap<Integer,Integer>();
	
	// Indicate if the train reach the end of line
	private boolean hasArrived = false;
	
	// Speed of the train
	private int speed;
	
	
	/**************************************************************************/

	public Train(Canton currentCanton, int currentPassenger, Map<Integer, Integer> destination, int speed, int type) {
		this.currentCanton = currentCanton;
		this.currentPassenger = currentPassenger;
		this.destination = destination;
		this.speed = speed;
		this.type = type;
		currentCanton.enter(this);
	}
	
	@Override
	public void run(){
		while (!hasArrived) {
			try {
				sleep(GUIConstants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (currentPosition + speed >= currentCanton.getEndPoint()) {
				Line line = Line.getInstance();
				try {
					Canton nextCanton = line.getCantonByPosition(currentPosition + speed);
					nextCanton.enter(this);
				} catch (TerminusException e) {
					hasArrived = true;
					currentPosition = line.getLength();
				}
			} else {
				updatePosition();
			}
		}
		currentCanton.exit();	
	}
	
	public void updatePosition(){
		currentPosition += speed;
	}

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public int getCurrentPassenger() {
		return currentPassenger;
	}

	public Canton getCurrentCanton() {
		return currentCanton;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public int getType() {
		return type;
	}
	
	public int getSpeed(){
		return speed;
	}

	public Map<Integer, Integer> getDestination() {
		return destination;
	}

	public void setCurrentPassenger(int currentPassenger) {
		this.currentPassenger = currentPassenger;
	}

	public void setCurrentCanton(Canton currentCanton) {
		this.currentCanton = currentCanton;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void setDestination(Map<Integer, Integer> destination) {
		this.destination = destination;
	}
		
}
