package core.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Drawable;
import gui.GUIConstants;

public class Station implements Drawable {

	//Maximum capacity of the station
	private int maxPassengers;
	
	//Number of current passenger
	private int currentPassenger;
	
	//Satisfaction of the passenger in the Station initialised at 75 (value beetwen 0 and 100)
	private int satisfaction = 75;
	
	//Number of reserve trains
	private int numReserveTrain;
	
	//Crowd of the Station
	private int crowdLevel;
	
	//If an accident occurred on the Station
	private boolean accident = false;
	
	//The train in the Station
	private Train train = null;

	public Station(int maxPassengers, int numReserveTrain, int crowdLevel) {
		this.maxPassengers = maxPassengers;
		this.numReserveTrain = numReserveTrain;
		this.crowdLevel = crowdLevel;
	}
	
	@Override
	public void draw(Graphics2D g2, int x, int y) {
		g2.setColor(GUIConstants.STATION_COLOR);
		g2.drawLine(x, y - GUIConstants.SIZE_STATION/2, x, y + GUIConstants.SIZE_STATION/2);
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
