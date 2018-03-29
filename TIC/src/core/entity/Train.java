package core.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import core.Constants;
import core.TerminusException;
import core.utility.Clock;
import core.utility.RandomUtility;

public class Train extends Thread {

	//List of the different train
	public final static int SHORT_TYPE = 0;
	public final static int LONG_TYPE = 1;
	public final static int RESERVE_TYPE = 2;
	
	//Maximum capacity of the Train
	private int maxPassengers;
		
	//Number of current passenger
	private volatile int currentPassenger;
	
	//Canton of the train
	private Canton currentCanton;
	
	//Current position in the Canton
	private int currentPosition;
	
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
		this.currentPosition = currentCanton.getStartPoint();
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
				if (currentCanton.hasIncident()){
					Incident incident = line.getIncident(currentCanton);
					if (!incident.isLocatedBefore(this)) {
						currentCanton.setTrainBlocked(true);
						break;
					}
				}
				else {
					currentCanton.setTrainBlocked(false);
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
		System.out.println("-> " + getName() + " arrived at the terminus " + currentCanton.getStation().getName() + " (" + Clock.getInstance() + ")");
		currentCanton.exit();
	}
	
	/**
	 * Action specified that somes passengers enters on this train
	 * @param nbPassengers the number of passenger that wish enter on the train
	 * @return the number of passengers that can't enter on this train
	 */
	public int addPassengers(int nbPassengers){
		
		// The train is at the last station
		if (destinations.isEmpty()) return nbPassengers;
		
		int remainingPlace = maxPassengers - currentPassenger; // Number of remaining places
		int sadPassenger = 0; // Number of passengers that can't enter
		int newPassenger = 0; // Number of passenger that can enter
		
		if (nbPassengers > remainingPlace){
			sadPassenger = nbPassengers - remainingPlace;
			newPassenger = nbPassengers - sadPassenger;
		}
		else {
			newPassenger = nbPassengers;
		}
		
		// We generate the destination of these new passengers
		destinations = RandomUtility.generateDest(newPassenger, destinations);
		
		// We add the new passengers on the train and return the number of passengers
		// that can't enter on this
		currentPassenger += newPassenger;
		System.out.println("-> " + newPassenger + " passengers got on.");
		return sadPassenger;
	}
	
	/**
	 * Passengers on the train is getting off into a station
	 * @return the number of passengers getting off
	 */
	public int getOffPassengers(Station station){
		int nbPassengers = destinations.get(station);
		destinations.remove(station);
		currentPassenger -= nbPassengers;
		System.out.println("-> " + nbPassengers + " passengers got off.");
		return nbPassengers;
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
	
	@Override
	public String toString(){
		
		String type = "";
		switch(this.type){
			case SHORT_TYPE 	: type = "Short"; break;
			case LONG_TYPE  	: type = "Long"; break;
			case RESERVE_TYPE 	: type = "Reserve"; break;
			default : type = "Undefined";
		}
		
		String s = 	"- Type : " + type + "\n"
					+ "- Number of passengers : " + currentPassenger + "/" + maxPassengers + "\n" 
					+ "- Destinations :\n";	
		
		for (Entry<Station, Integer> entry : destinations.entrySet()){
			s += "[" + entry.getValue() + " => " + entry.getKey().getName() + "]\n";
		}
		
		return s; 					
	}
		
}
