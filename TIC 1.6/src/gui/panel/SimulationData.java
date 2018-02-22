package gui.panel;

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
public class SimulationData {
	
	//private Map<String, HashMap<Integer, Integer>> StationData  = new HashMap<String, HashMap<Integer, Integer>>();
	private Map<Integer, Integer> NbTravelerData = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> NbIncidentData = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> SatisfactionData = new HashMap<Integer, Integer>();
	
	
	private static SimulationData instance = new SimulationData();
	
	private SimulationData(){

	}
	
	public static SimulationData getInstance(){
        return instance;
	}
	
	public void addTravelerStationData(int turn, int number){
		NbTravelerData.put(turn, number);
		//StationData.put(station,NbTravelerData);
	}
	
	public int getNbTraveler(int turn){
		//StationData.get(station);NbTravelerData.get(turn);
		
		/*Set<Map.Entry<String, HashMap<Integer, Integer>>> set1 = StationData.entrySet();
		for (Map.Entry<String, HashMap<Integer, Integer>> e1 : set1) {
			station = e1.getKey();
			HashMap<Integer, Integer> t = e1.getValue();
			Set<HashMap.Entry<Integer, Integer>> set2 = NbTravelerData.entrySet();
			for (HashMap.Entry<Integer, Integer> e2 : set2) {
				Integer NbTraveler = e2.getValue();
			}
		}
		return 0;*/
		return NbTravelerData.get(turn);
	}
	
	public void addTimeIncidentData(int turn, int time){
		NbIncidentData.put(turn, time);
	}
	
	public Integer getIncidentNb(int turn){
		return NbIncidentData.get(turn);
	}
	
	
	public void addSatisfactionData(int turn, int satisfaction){
		SatisfactionData.put(turn, satisfaction);
	}
	
	public Integer getSatisfactionNb(int turn){
		return SatisfactionData.get(turn);
	}

}