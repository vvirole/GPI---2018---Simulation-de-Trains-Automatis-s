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

	//List of trains on the line
	private List<Train> trains = new ArrayList<Train>();

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
	
	/**
	 * Get a canton by the position of a train on the line
	 * @param position of the train
	 * @return the canton where the train is
	 * @throws TerminusException
	 */
	public Canton getCantonByPosition(int position) throws TerminusException {
		for (Canton canton : cantons) {
			if (canton.getEndPoint() > position) {
				return canton;
			}
		}
		throw new TerminusException();
	}
	
	public void addTrain(Train train){
		trains.add(train);
	}
	
	public List<Train> getTrains(){
		return trains;
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
