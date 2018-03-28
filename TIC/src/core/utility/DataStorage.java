package core.utility;

import java.util.ArrayList;
import java.util.List;


/**
 * Storage of datas of simulation
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Vincent Virole
 * @author Thomas Re
 *
 */
public class DataStorage {
	
	private List<Float> times = new ArrayList<Float>();
	private List<Integer> nbPassengers = new ArrayList<Integer>();
	private List<Integer> nbIncidents = new ArrayList<Integer>();
	private List<Integer> satisfactions = new ArrayList<Integer>();
	
	private static DataStorage instance = new DataStorage();
	
	public void clear(){
		times.clear();
		nbPassengers.clear();
		nbIncidents.clear();
		satisfactions.clear();
	}
	
	public static DataStorage getInstance(){
        return instance;
	}
	
	/**
	 * Add datas of a cycle of the simulation
	 * @param time
	 * @param nbPassenger the number of passenger
	 * @param nbIncident the number of incident
	 * @param satisfaction the level of satisfaction
	 */
	public void addData(float time, int nbPassenger, int nbIncident, int satisfaction){
		times.add(time);
		nbPassengers.add(nbPassenger);
		nbIncidents.add(nbIncident);
		satisfactions.add(satisfaction);
	}	
	
	/**
	 * @param time
	 * @return the index of storage for this time
	 */
	public int getTimeIndex(float time){
		for (int i = 0 ; i < times.size() ; i++){
			if (times.get(i) == time){
				return i;
			}
		}
		return -1;
	}
	
	public int getNbPassenger(float time){
		return nbPassengers.get(getTimeIndex(time));
	}
	
	public int getNbIncident(float time){
		return nbIncidents.get(getTimeIndex(time));
	}
	
	public int getLevelSatisfaction(float time){
		return satisfactions.get(getTimeIndex(time));
	}
	
	public List<Float> getTimes(){
		return times;
	}
	
	public List<Integer> getPassengerData(){
		return nbPassengers;
	}
	
	public List<Integer> getIncidentData(){
		return nbIncidents;
	}
	
	public List<Integer> getSatisfactionData(){
		return satisfactions;
	}

}