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
	
	// ===========================================================================
	//							GENERAL PART
	// ===========================================================================
	
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
	
	/**
	 * Get the total number of passengers on the line
	 * @return the number of passengers
	 */
	public int getTotalPassengers(){
		int total = 0;
		// Passengers on the stations
		for (Station station : getStationList()){
			total += station.getCurrentPassenger();
		}
		// Passengers on the trains
		for (Train train : trains){
			total += train.getCurrentPassenger();
		}
		return total;
	}
	
	/**
	 * Get a canton with his own position on the line
	 * @param i the position of canton on the line
	 * @return the canton
	 */
	public Canton getCanton(int i) {
		return cantons.get(i);
	}
	
	/**
	 * Get the number of canton
	 * @return the number of canton
	 */
	public int getNbCanton() {
		return cantons.size();
	}
	
	/**
	 * Add a new canton on the line
	 * @param canton the new canton
	 */
	public void addCanton(Canton canton){
		cantons.add(canton);
	}
	
	/**
	 * Add a new train that will cross the line
	 * @param train
	 */
	public void addTrain(Train train){
		trains.add(train);
	}
	
	
	// ===========================================================================
	//							INCIDENT PART
	// ===========================================================================
	

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
	 * Get the incident happened on a canton
	 * @param canton on the line
	 * @return the incident if the canton has one, or null if he hasn't
	 */
	public Incident getIncident(Canton canton){
		if (!canton.hasIncident()){
			return null;
		}
		for (Entry<Incident, Integer> entry : incidents.entrySet()){
			Incident incident = entry.getKey();
			int location = incident.getLocation();
			if (location > canton.getStartPoint() && location <= canton.getEndPoint()){
				return incident;
			}
		}
		return null;
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
	 * Run the resolution procedure to resolve the incidents on the line
	 */
	public void resolveIncident(){
		for (Entry<Incident, Integer> entry : incidents.entrySet()){
			Incident incident = entry.getKey();
			Integer remainingTime = entry.getValue();
			incidents.put(incident, --remainingTime);
			if (remainingTime == 0){
				removeIncident(incident);
			}
		}
	}
	
	/**
	 * List the incident on the line
	 * @return
	 */
	public ConcurrentHashMap<Incident, Integer> listIncidents() {
		return incidents;
	} 
	
	
	// ======================================================================
	//						STANDARD GETTERS & SETTERS
	// ======================================================================
	
		
	public ConcurrentLinkedQueue<Train> getTrains(){
		return trains;
	}
	
	public List<Canton> getCantons() {
		return cantons;
	}

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
