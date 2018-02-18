package core.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import core.TerminusException;
import core.utility.RandomUtility;
import gui.GUIConstants;


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
	
	//Period of traffic (void, normal and full)
	private String period = PERIOD_NORMAL;
	
	//Indicate if the line is working
	private boolean working;
	
	//Canton List on the line
	private List<Canton> cantons = new ArrayList<Canton>();
	
	//List of trains on the line
	private ConcurrentLinkedQueue<Train> trains = new ConcurrentLinkedQueue<Train>();
	
	//List of current incidents on the line
	private ConcurrentHashMap<Incident, Integer> incidents = new ConcurrentHashMap<Incident, Integer>();


	public Line(String name, int length) {
		this.name = name;
		this.length = length;
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
	 * @return if there are incidents on the line
	 */
	public boolean hasIncident(){
		return (incidents.size() > 0);
	}
	
	/**
	 * Verify if a canton has incident or not
	 * @param canton on the line
	 * @return true if there is an incident, else false 
	 */
	public boolean hasIncident(Canton canton){
		for (Entry<Incident, Integer> entry : incidents.entrySet()){
			// Position of the incident on the line
			int position = entry.getKey().getLocation();
			if (position > canton.getStartPoint() && position <= canton.getEndPoint()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * New incident on a canton given
	 * @param canton where the incident is happened
	 * @param type of the incident
	 */
	public void newIncident(Canton canton, int type){
		canton.setIncident(true);
		Incident incident; 
		switch(type){
			case Incident.PASSENGER_INCIDENT : 		Station station = canton.getStation();
													incident = new Incident(station.getPosition(), type);
													incidents.put(incident, incident.getTimeToResolve());
													break;
				
			case Incident.INFRASTRUCTURE_INCIDENT : int xMin = canton.getStartPoint() + GUIConstants.SIZE_STATION/2;
													int xMax = canton.getEndPoint() - GUIConstants.SIZE_STATION/2;
													incident = new Incident(RandomUtility.rand(xMin, xMax), type);
													incidents.put(incident, incident.getTimeToResolve());
													break;
													
			default : Logger.getAnonymousLogger().severe("Unknown incident type");
		}
	}
	
	/**
	 * Remove an incident happened on the line
	 * @param incident the incident which has been resolved
	 */
	public void removeIncident(Incident incident) {
		try {
			incidents.remove(incident);
			int location = incident.getLocation();
			getCantonByPosition(location).setIncident(false);
		} catch (TerminusException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * List the incident on the line
	 * @return
	 */
	public ConcurrentHashMap<Incident, Integer> listIncidents() {
		return incidents;
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
	
	public ConcurrentLinkedQueue<Train> getTrains(){
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

	public int getNbCanton() {
		return cantons.size();
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
