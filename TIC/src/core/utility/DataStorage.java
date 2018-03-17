package core.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Stckage des données de la simulation (Singleton)
 * 
 * @author Maxime Joly
 * @author Arnaud Sery
 * @author Benoît Cons
 * @author Vincent Virole
 * @author Thomas Re
 *
 */
public class DataStorage {
	
	private Map<Integer, Integer> nbPassengerData = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> nbIncidentData = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> satisfactionData = new HashMap<Integer, Integer>();
	
	private static DataStorage instance = new DataStorage();
	
	public void clear(){
		nbPassengerData.clear();
		nbIncidentData.clear();
		satisfactionData.clear();
	}
	
	public static DataStorage getInstance(){
        return instance;
	}
	
	public void addPassengerData(int turn, int number){
		nbPassengerData.put(turn, number);
	}
	
	public int getNbPassenger(int turn){
		return nbPassengerData.get(turn);
	}
	
	public Map<Integer, Integer> getPassengerData(){
		return nbPassengerData;
	}
	
	public void addTimeIncidentData(int turn, int time){
		nbIncidentData.put(turn, time);
	}
	
	public Integer getNbIncident(int turn){
		return nbIncidentData.get(turn);
	}
	
	
	public void addSatisfactionData(int turn, int satisfaction){
		satisfactionData.put(turn, satisfaction);
	}
	
	public Integer getNbSatisfaction(int turn){
		return satisfactionData.get(turn);
	}
	
	public Map<Integer, Integer> getSatisfactionData(){
		return satisfactionData;
	}

}