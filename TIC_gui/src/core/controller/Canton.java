package core.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;

import core.Drawable;
import gui.GUIConstants;

public class Canton implements Drawable {

	//Canton identity
	private int id;
	
	//Length of the canton on the frame
	private int length;
	
	// Position where the canton begins
	private int startPoint;
	
	//If a Train is on the Canton
	private boolean occuped = false;
	
	//If an accident occurred on the Canton
	private boolean accident = false;
	
	//The station on the canton
	private Station station;

	public Canton(int id, int startPoint, int length, Station station) {
		this.id = id;
		this.startPoint = startPoint;
		this.length = length;	
		this.station = station;
	}
	
	@Override
	public void draw(Graphics2D g2, int x, int y) {
		g2.setColor(GUIConstants.LINE_COLOR);
		g2.drawString("Canton " + id, x + length/4 , y + GUIConstants.SIZE_STATION*2);
		station.draw(g2, x + length, y);
	}
	
	//GETTER AND SETTER

	public int getId() {
		return id;
	}
	
	public int getStartPoint(){
		return startPoint;
	}
	
	public int getEndPoint(){
		return startPoint + length;
	}

	public int getLength() {
		return length;
	}

	public boolean isOccuped() {
		return occuped;
	}

	public void setOccuped(boolean occuped) {
		this.occuped = occuped;
	}

	public boolean isAccident() {
		return accident;
	}

	public void setAccident(boolean accident) {
		this.accident = accident;
	}

	public Station getStation() {
		return station;
	}

	
}
