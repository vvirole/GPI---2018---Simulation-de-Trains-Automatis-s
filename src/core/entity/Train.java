package core.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import core.Constants;
import core.TerminusException;

public class Train extends Thread {

	//List of the different train
	public final static int SHORT_TYPE = 0;
	public final static int LONG_TYPE = 1;
	public final static int RESERVE_TYPE = 2;
	
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
	private boolean arrived = false;
	
	// Indicate if the train is running
	private boolean running = false;
	
	// Speed of the train
	private int speed;
	
	
	/**************************************************************************/

	public Train(Canton currentCanton, int currentPassenger, int speed, int type) {
		this.currentCanton = currentCanton;
		this.currentPassenger = currentPassenger;
		this.speed = speed;
		this.type = type;
		currentCanton.enter(this);
		
		// Initialisation of the capacity of train according to the type
		switch(type){
			case SHORT_TYPE : maxPassengers = Constants.SHORT_TRAIN_CAPACITY; break;
			case LONG_TYPE : maxPassengers = Constants.LONG_TRAIN_CAPACITY; break;
			case RESERVE_TYPE : maxPassengers = (Constants.SHORT_TRAIN_CAPACITY + Constants.LONG_TRAIN_CAPACITY) / 2; break;
			default : break;
		}
	}
	
	@Override
	public void run(){
		running = true;
		while (!arrived){
			try {
				sleep(Constants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (running) {
				try {
					sleep(Constants.TIME_UNIT);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (currentPosition + speed >= currentCanton.getEndPoint()) {
					Line line = Line.getInstance();
					try {
						Canton nextCanton = line.getCantonByPosition(currentPosition + speed);
						nextCanton.enter(this);
					} catch (TerminusException e) {
						arrived = true;
						running = false;
						currentPosition = line.getLength();
					}
				} else {
					updatePosition();
				}
			}
		}
		currentCanton.exit();
	}
	
	public void updatePosition(){
		currentPosition += speed;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void setRunning(boolean running){
		this.running = running;
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
