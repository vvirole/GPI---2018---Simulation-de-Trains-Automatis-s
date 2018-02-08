package core.entity;

import java.util.ArrayList;
import java.util.List;

import core.TerminusException;
import gui.GUIConstants;


public class Line {
	
	private static Line instance ;
	
	//Name of the line
	private String name;
	
	//Size of the line on the frame.
	private int length;
	
	//Number of Canton on the line
	private int numCanton;
	
	//Canton List on the line
	private List<Canton> cantons = new ArrayList<Canton>();


	public Line(String name, int length, int numCanton) {
		this.name = name;
		this.length = length;
		this.numCanton = numCanton;
		this.startPoint = GUIConstants.WINDOW_WIDTH - length/2;
	}
	
	/**
	 * Add a canton on the line
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
	
	public static Line getInstance() {
		return instance;
	}

	public static void setInstance(Line instance) {
		Line.instance = instance;
	}
	
	public String getName(){
		return name;
	}

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
	
	public Canton getCanton(int i) {
		return cantons.get(i);
	}

	public void setCantons(List<Canton> cantons) {
		this.cantons = cantons;
	}
	
}
