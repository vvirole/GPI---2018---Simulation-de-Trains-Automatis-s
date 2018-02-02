package controller;

import java.util.ArrayList;
import java.util.List;

import demo.Canton;

public class Line {
	
	//Maximum number of passenger on the line.
	public final static int MAX_PASSENGER = 100000;
	
	//Size of the line on the frame.
	private int length;
	
	//Number of Canton on the line
	private int numCanton;
	
	//Unique instance class
	private static Line instance;
	
	//Canton List on the line
	private List<Canton> cantons = new ArrayList<Canton>();

	private Line(int length, int numCanton) {
		this.length = length;
		this.numCanton = numCanton;
	}
	
	/**
	 * Create a new instance of Line
	 * @param length : Size of the line on the frame.
	 * @param numCanton : Number of Canton on the line
	 * @return new instance of Line
	 */
	public static Line newInstance(int length, int numCanton) {
		if(instance == null) {
			instance = new Line(length, numCanton);
		}
		return instance;
	}
	
	/**
	 * 
	 * @return the current instance of Line
	 */
	public static Line getInstance() {
		return instance;
	}
	
	
	
	//GETTER AND SETTER

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNumCanton() {
		return numCanton;
	}

	public void setNumCanton(int numCanton) {
		this.numCanton = numCanton;
	}

	public List<Canton> getCantons() {
		return cantons;
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}
	
	
	
	
}
