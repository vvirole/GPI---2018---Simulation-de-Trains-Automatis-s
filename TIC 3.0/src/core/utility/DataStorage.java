package core.utility;

import java.util.HashMap;
import java.util.Map;


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
	
	private Map<Float, Integer> nbPassengerData = new HashMap<Float, Integer>();
	private Map<Float, Integer> incidentData = new HashMap<Float, Integer>();
	private Map<Float, Integer> satisfactionData = new HashMap<Float, Integer>();
	
	private static DataStorage instance = new DataStorage();
	
	public void clear(){
		nbPassengerData.clear();
		incidentData.clear();
		satisfactionData.clear();
	}
	
	public static DataStorage getInstance(){
        return instance;
	}
	
	public void addPassengerData(float time, int number){
		nbPassengerData.put(time, number);
	}
	
	public int getNbPassenger(float time){
		return nbPassengerData.get(time);
	}
	
	public Map<Float, Integer> getPassengerData(){
		return nbPassengerData;
	}
	
	public void addIncidentData(float time, int nbIncident){
		incidentData.put(time, nbIncident);
	}
	
	public Integer getNbIncident(float time){
		return incidentData.get(time);
	}
	
	public Map<Float, Integer> getIncidentData(){
		return incidentData;
	}
	
	public void addSatisfactionData(Float time, int satisfaction){
		satisfactionData.put(time, satisfaction);
	}
	
	public Integer getNbSatisfaction(Float time){
		return satisfactionData.get(time);
	}
	
	public Map<Float, Integer> getSatisfactionData(){
		return satisfactionData;
	}

}