package core.entity;

import java.util.ArrayList;
import java.util.List;

import core.TerminusException;


public class Line {
	
	private static Line instance ;
	
	//List of different period
	public static final String PERIOD_VOID = "creuse";
	public static final String PERIOD_NORMAL = "normale";
	public static final String PERIOD_FULL = "pleine";
	
	//Name of the line
	private String name;
	
	//Size of the line on the frame.
	private int length;
	
	//Number of Canton on the line
	private int numCanton;
	
	//Period of traffic (void, normal and full)
	private String period = PERIOD_NORMAL;
	
	//Indicate if the line is working
	private boolean working;
	
	//Canton List on the line
	private List<Canton> cantons = new ArrayList<Canton>();
	
	//List of trains on the line
	private List<Train> trains = new ArrayList<Train>();
	
	//List of current incidents on the line
	private List<Incident> incidents = new ArrayList<Incident>();


	public Line(String name, int length, int numCanton) {
		this.name = name;
		this.length = length;
		this.numCanton = numCanton;
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
	
	/**
	 * Verify if a canton has incident or not
	 * @param canton on the line
	 * @return true if there is an incident, else false 
	 */
	public boolean hasIncident(Canton canton){
		for (Incident incident : incidents){
			// Position of the incident on the line
			int position = incident.getLocation();
			if (position > canton.getStartPoint() && position <= canton.getEndPoint()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the list of available stations
	 * @return a list of stations
	 */
	public List<Station> getStationList(){
		List<Station> stations = new ArrayList<Station>();
		for (Canton canton : cantons){
			stations.add(canton.getStation());
		}
		return stations;
	}
	
	/*******************************************************/
	
	public void addTrain(Train train){
		trains.add(train);
	}
	
	public List<Train> getTrains(){
		return trains;
	}
	
	public void addCanton(Canton canton){
		cantons.add(canton);
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
	
	public void addIncident(Incident incident){
		incidents.add(incident);
	}
	
	public List<Incident> getIncidents() {
		return incidents;
	} 
	
	/*****************************************************/
	
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
	
	public String getPeriod(){
		return period;
	}
	
	public void setPeriod(String period){
		this.period = period;
	}
	
	public boolean isWorking(){
		return working;
	}
	
	public void setWorking(boolean working){
		this.working = working;
	}
	
}
