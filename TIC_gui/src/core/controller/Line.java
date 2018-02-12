package core.controller;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import core.Drawable;
import gui.GUIConstants;


public class Line implements Drawable {
	
	private static Line instance ;
	
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
	public void draw(Graphics2D g2, int x, int y) {
		g2.setColor(GUIConstants.LINE_COLOR);
		g2.drawLine(x - length/2, y, x + length/2, y);
		int startPoint = x - length/2;
		for(Canton canton : cantons) {
			canton.draw(g2, startPoint, y);
			startPoint += canton.getLength();
		}
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

	public static Line getInstance() {
		return instance;
	}

	public static void setInstance(Line instance) {
		Line.instance = instance;
	}
	
}
