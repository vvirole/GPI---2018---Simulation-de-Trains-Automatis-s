package core.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import core.Constants;
import core.TerminusException;
import core.utility.RandomUtility;

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
	
	//Contains the stations and the number of passengers which want to get off
	private Map<Station,Integer> destinations = new HashMap<Station,Integer>();
	
	// Indicate if the train reached the end of line
	private boolean arrived = false;
	
	// Indicate if the train is running
	private boolean running = false;
	
	// Speed of the train
	private int speed;
	
	// The line where the train is running
	private Line line = Line.getInstance();
	
	
	/**************************************************************************/

	public Train(Canton currentCanton, int currentPassenger, int speed, int type) {
		this.currentCanton = currentCanton;
		this.currentPassenger = currentPassenger;
		this.speed = speed;
		this.type = type;
		currentCanton.enter(this);
		
		// Initialisation of the map destination 
		for (Station station : line.getStationList()){
			destinations.put(station, 0); // There are no passengers at the beginning
		}
		
		// Initialisation of the capacity of train according to the type
		switch(type){
			case SHORT_TYPE : 	maxPassengers = Constants.SHORT_TRAIN_CAPACITY; break;
			case LONG_TYPE : 	maxPassengers = Constants.LONG_TRAIN_CAPACITY; break;
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
					try {
						Station station = currentCanton.getStation();
						station.enter(this);
					} catch (TerminusException e) {
						arrived = true;
						running = false;
						line.getTrains().remove(this);
					}
				} 
				else {
					updatePosition();
				}
			}
		}
		currentCanton.exit();
	}
	
	/**
	 * Action specified that somes passengers enters on this train
	 * @param nbPassengers the number of passenger that can enter on the train
	 */
	public void addPassengers(int nbPassengers){
		int remaining = nbPassengers;
		for (int i = 0 ; i < nbPassengers ; i++){
			for (Entry<Station, Integer> entry : destinations.entrySet()){
				Station dest = entry.getKey();
				Integer number = entry.getValue();
				
				// ================ A AFFINER ======================
				int rand = RandomUtility.rand(0, remaining);
				int newValue = rand + number;		
				// ===================================================		
				destinations.put(dest, newValue);
				remaining -= rand;
			}
		}
	}
	
	/**
	 * Remove a station destination from the map destinations
	 */
	public void removeDestination(Station station){
		destinations.remove(station);
	}
	
	/**
	 * Update the position of train according to his own speed
	 */
	public void updatePosition(){
		currentPosition += speed;
	}
	
	/*******************************************************************/
	
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

	public Map<Station, Integer> getDestination() {
		return destinations;
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
	
	public void setDestinations(Map<Station, Integer> destination) {
		this.destinations = destination;
	}
		
}
