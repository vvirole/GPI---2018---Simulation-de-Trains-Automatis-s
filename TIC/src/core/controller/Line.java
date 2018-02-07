package core.controller;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import core.Drawable;


public class Line implements Drawable {
	
	//Size of the line on the frame.
	private int length;
	
	//Number of Canton on the line
	private int numCanton;
	
	//Canton List on the line
	private List<Canton> cantons = new ArrayList<Canton>();

	public Line(int length, int numCanton) {
		this.length = length;
		this.numCanton = numCanton;
	}
	
	@Override
	public void draw(Graphics g) {
		
	}
	
	/**
	 * Add a canton in the line
	 * @param canton a canton
	 */
	public void addCanton(Canton canton){
		cantons.add(canton);
	}
	
	public Canton getCantonByPosition(int position) throws TerminusException {
		for (Canton canton : cantons) {
			if (canton.getEndPoint() > position) {
				return canton;
			}
		}
		throw new TerminusException();
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
