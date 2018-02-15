package core.entity;

import java.util.HashMap;
import java.util.Map;

import core.Constants;
import core.TerminusException;
import core.utility.RandomUtility;

public class Station {
	
	// Name of the station
	private String name;
	
	// Position of the station on the line
	private int position;

	//Maximum capacity of the station
	private int maxPassengers;
	
	//Number of current passenger
	private int currentPassenger;
	
	//Contains the stations and the number of passengers which want to go on
	private Map<Station,Integer> destinations = new HashMap<Station,Integer>();
	
	//Satisfaction of the passenger in the Station initialised at 75 (value beetwen 0 and 100)
	private int satisfaction = 75;
	
	//Number of reserve trains
	private int numReserveTrain;
	
	//Crowd of the Station
	private int crowdLevel;
	
	//If an accident occurred on the Station
	private boolean accident = false;
	
	//The train in the Station
	private Train arrivalTrain = null;

	public Station(String name, int position, int maxPassengers, int numReserveTrain, int crowdLevel) {
		this.name = name;
		this.position = position;
		this.maxPassengers = maxPassengers;
		this.numReserveTrain = numReserveTrain;
		this.crowdLevel = crowdLevel;
	}
	
	public synchronized void enter(Train train) throws TerminusException {
		if (arrivalTrain == null){
			arrivalTrain = train;
			arrivalTrain.setCurrentPosition(position);
			try {
				// Pause for taking passengers of the station
				Thread.sleep(getPauseDuration());
				train.removeDestination(this);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {			
			// After taking passengers, the train try to leave his canton and enter on 
			// the next if there isn't incident declared
			Line line = Line.getInstance();
			Canton nextCanton = line.getCantonByPosition(position + train.getSpeed());
			if (!line.hasIncident(nextCanton)){
				nextCanton.enter(train);
			}
		}
	}

	public synchronized void exit() {
		arrivalTrain = null;
		notify();
	}
	
	/**
	 * @return the time of pause according to the period
	 */
	private int getPauseDuration(){
		Line line = Line.getInstance();
		int defaultTime = Constants.DEFAULT_PAUSE_STATION;
		switch(line.getPeriod()){
			case Line.PERIOD_VOID : 	return RandomUtility.rand(defaultTime/3, defaultTime);
			case Line.PERIOD_NORMAL : 	return RandomUtility.rand(defaultTime/2, (3 * defaultTime)/2);
			case Line.PERIOD_FULL : 	return RandomUtility.rand(defaultTime, (2 * defaultTime)/3);
			default : 					return defaultTime;
		}
	}
		
	
	/******************************************************/
	
	public String getName(){
		return name;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int position){
		this.position = position;
	}

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public int getCurrentPassenger() {
		return currentPassenger;
	}

	public void setCurrentPassenger(int currentPassenger) {
		this.currentPassenger = currentPassenger;
	}

	public int getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(int satisfaction) {
		this.satisfaction = satisfaction;
	}

	public int getNumReserveTrain() {
		return numReserveTrain;
	}

	public void setNumReserveTrain(int numReserveTrain) {
		this.numReserveTrain = numReserveTrain;
	}

	public int getCrowdLevel() {
		return crowdLevel;
	}

	public void setCrowdLevel(int crowdLevel) {
		this.crowdLevel = crowdLevel;
	}

	public boolean isAccident() {
		return accident;
	}

	public void setAccident(boolean accident) {
		this.accident = accident;
	}

	public Train getTrain() {
		return arrivalTrain;
	}

	public void setTrain(Train arrivalTrain) {
		this.arrivalTrain = arrivalTrain;
	}

	public Map<Station, Integer> getDestinations() {
		return destinations;
	}

	public void setDestinations(Map<Station, Integer> destinations) {
		this.destinations = destinations;
	}	
	
}
