package core.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import core.Constants;
import core.Drawable;
import gui.GUIConstants;

public class Train extends Thread implements Drawable {

	//List of the different train
	public final static int SHORT_TYPE = 0;
	public final static int LONG_TYPE = 1;
	
	//Maximum capacity of the Train
	private int maxPassengers;
		
	//Number of current passenger
	private volatile int currentPassenger;
	
	//Canton of the train
	private volatile Canton currentCanton;
	
	//Current position in the Canton
	private volatile int currentPosition;
	
	//Type of the train
	private int type;
	
	//Contain the number of the passenger and the Station where they want to go
	private Map<Integer,Integer> destination = new HashMap<Integer,Integer>();
	
	// Indicate if the train 
	private boolean hasArrived = false;
	
	// Speed of the train
	private int speed;
	
	
	/**************************************************************************/

	public Train(int currentPassenger, Map<Integer, Integer> destination, int speed, int type) {
		this.currentPassenger = currentPassenger;
		this.destination = destination;
		this.speed = speed;
		this.type = type;
	}
	
	@Override
	public void draw(Graphics2D g2, int x, int y) {
		g2.setColor(GUIConstants.TRAIN_COLOR);
		g2.drawString("Train", x, y - GUIConstants.SIZE_STATION*2);
		g2.drawLine(x, y - GUIConstants.SIZE_STATION/4, x, y + GUIConstants.SIZE_STATION/4);		
	}
	
	@Override
	public void run(){
		while (!hasArrived){
			try {
				sleep(Constants.TIME_UNIT);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setCurrentPosition(currentPosition++);
		}
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
