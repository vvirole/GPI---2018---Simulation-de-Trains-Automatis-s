package gui.panel;

import java.util.HashMap;
import java.util.Map;

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
	
	private Map<String, HashMap<Integer, Integer>> StationData  = new HashMap<String, HashMap<Integer, Integer>>();
	private HashMap<Integer, Integer> NbTravelerData = new HashMap<Integer, Integer>();
	private Map<String, Integer> TimeIncidentData = new HashMap<String, Integer>();
	private Map<String, Integer> SatisfactionData = new HashMap<String, Integer>();
	
	
	private static SimulationData instance = new SimulationData();
	
	private SimulationData(){

	}
	
	public static SimulationData getInstance(){
        return instance;
	}
	
	public void addTravelerStationData(String station, int turn, int number){
		NbTravelerData.put(turn, number);
		StationData.put(station,NbTravelerData);
	}
	
	public int getNbTraveler(String station, int turn){
		StationData.get(station);NbTravelerData.get(turn);
		
		/*Map<String, HashMap<String, Integer>> map1 = new HashMap<String, HashMap<String, Integer>> ();
		        //  On utilise la méthode entrySet de l'intertace Map.
		        Set<Entry<String, HashMap<String, Integer>>> set1 = map1.entrySet();
		        for (Entry<String, HashMap<String, Integer>> e1 : set1) {
		            String key1 = e1.getKey();
		            Map<String, Integer> map2 = e1.getValue();
		            Set<Entry<String, Integer>> set2 = map2.entrySet();
		            for (Entry<String, Integer> e2 : set2) {
		                String key2 = e2.getKey();
		                Integer value2 = e2.getValue();
		            }
		        }*/
		
		return 0;
	}
	

}