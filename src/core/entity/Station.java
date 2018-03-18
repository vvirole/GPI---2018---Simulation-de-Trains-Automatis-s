package core.entity;

import core.Constants;
import core.TerminusException;
import core.utility.RandomUtility;

public class Station {
	
	// Name of the station
	private String name;
	
	// Position of the station on the line
	private int position;
	
	//Crowd level of the Station
	private int crowdLevel;

	//Maximum capacity of the station
	private int maxPassengers;
	
	//Number of current passenger
	private volatile int currentPassenger;
	
	//Satisfaction of the passenger in the Station initialised at 75 (value beetwen 0 and 100)
	private volatile int satisfaction = 75;
	
	//Number of reserve trains
	private volatile int numReserveTrain;
	
	//The train in the Station
	private volatile Train arrivalTrain = null;

	public Station(String name, int position, int maxPassengers, int numReserveTrain, int crowdLevel) {
		this.name = name;
		this.position = position;
		this.maxPassengers = maxPassengers;
		this.numReserveTrain = numReserveTrain;
		this.crowdLevel = crowdLevel;
		if (maxPassengers < Constants.INITIAL_PASSENGER_STATION){
			this.currentPassenger = maxPassengers;
		}
		else {
			this.currentPassenger = Constants.INITIAL_PASSENGER_STATION;
		}
	}
	
	public synchronized void enter(Train train) throws TerminusException {
		if (arrivalTrain == null){
			arrivalTrain = train;
			arrivalTrain.setCurrentPosition(position);
			
			try {	
				// Pause for taking passengers of the station
				Thread.sleep(getPauseDuration());
				
				System.out.println("\n================== " + arrivalTrain.getName() + " arrived at " + name + " ==================\n");
				
				// Passengers getting off the train are now into the station
				currentPassenger += arrivalTrain.getOffPassengers(this);
				arrivalTrain.removeDestination(this);
				
				// New passengers on the train
				int nbDest = arrivalTrain.getDestination().size();
				if (nbDest > 0){
					int nbNewPassengers = RandomUtility.rand(0, currentPassenger/nbDest);
					currentPassenger -= nbNewPassengers;
					currentPassenger += arrivalTrain.addPassengers(nbNewPassengers);
				}
				
				if (currentPassenger > maxPassengers){
					currentPassenger = maxPassengers;
				}
				
				if (isTerminus()){
					exit();
					throw new TerminusException();
				}
				
				System.out.println(arrivalTrain);
				System.out.println("===================================================================\n");
				
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
	 * Somes passengers arrive into the station and somes leave the station
	 * according to the period of journey
	 */
	public void updatePassengers(){
		Line line = Line.getInstance();
		int newPassenger = 0, leavePassenger = 0;
		
		if (currentPassenger == 0) return;
		
		switch(line.getPeriod()){
			case Line.PERIOD_FULL :
				if (!isTerminus())
					newPassenger = RandomUtility.rand(0, maxPassengers/5);
				leavePassenger = RandomUtility.rand(0, maxPassengers/20);
				break;
				
			case Line.PERIOD_VOID :
				if (!isTerminus())
					newPassenger = RandomUtility.rand(0, maxPassengers/30);
				leavePassenger = RandomUtility.rand(0, maxPassengers/20);
				break;
				
			case Line.PERIOD_NORMAL :
				if (!isTerminus())
					newPassenger = RandomUtility.rand(0, maxPassengers/20);
				leavePassenger = RandomUtility.rand(0, maxPassengers/20);
				break;
				
			default : break;
		}
		
		if (isTerminus()){
			leavePassenger = currentPassenger;
		}	
		
		int futurePassenger = currentPassenger + newPassenger - leavePassenger;
		if (futurePassenger < 0){
			currentPassenger = 0;
		}
		else if (futurePassenger > maxPassengers){
			currentPassenger = maxPassengers;
		}
		else {
			currentPassenger = futurePassenger;
		}
	}
	
	/**
	 * Update the satisfaction level of the station
	 */
	public void updateSatisfaction(){
		if (currentPassenger > maxPassengers/2){
			if (satisfaction > 1) satisfaction -= 2;
		}
		if (Line.getInstance().hasIncident()){
			if (satisfaction > 0) satisfaction--;
		}
		else {
			if (satisfaction < 100) satisfaction++;
		}
	}
	
	/**
	 * @return the time of pause according to the period
	 */
	public int getPauseDuration(){
		Line line = Line.getInstance();
		int defaultTime = Constants.DEFAULT_PAUSE_STATION;
		switch(line.getPeriod()){
			case Line.PERIOD_VOID : 	return RandomUtility.rand(defaultTime/3, defaultTime);
			case Line.PERIOD_NORMAL : 	return RandomUtility.rand(defaultTime/2, (3 * defaultTime)/2);
			case Line.PERIOD_FULL : 	return RandomUtility.rand(defaultTime, (5 * defaultTime)/3);
			default : 					return defaultTime;
		}
	}
	
	/**
	 * @return true if the station is the terminus of the line, false else
	 */
	public boolean isTerminus(){
		return Line.getInstance().getTerminus().equals(this);
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

	public Train getTrain() {
		return arrivalTrain;
	}

	public void setTrain(Train arrivalTrain) {
		this.arrivalTrain = arrivalTrain;
	}
}
