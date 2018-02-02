package controller;

public class Station {

	//Maximum capacity of the station
	private int maxPassengers;
	
	//Number of current passenger
	private int currentPassenger;
	
	//Satisfaction of the passenger in the Station
	private int satisfaction;
	
	//Number of reserve trains
	private int numReserveTrain;
	
	//Crowd of the Station
	private int crowdLevel;
	
	//If an accident occurred on the Station
	private boolean accident = false;
	
	//The train in the Station
	private Train train = null;

	public Station(int maxPassengers, int currentPassenger, int satisfaction, int numReserveTrain, int crowdLevel,
			boolean accident, Train train) {
		this.maxPassengers = maxPassengers;
		this.currentPassenger = currentPassenger;
		this.satisfaction = satisfaction;
		this.numReserveTrain = numReserveTrain;
		this.crowdLevel = crowdLevel;
		this.accident = accident;
		this.train = train;
	}
	
	
	
	//GETTER AND SETTER

	public int getMaxPassengers() {
		return maxPassengers;
	}

	public void setMaxPassengers(int maxPassengers) {
		this.maxPassengers = maxPassengers;
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
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}
	
	
	
}
