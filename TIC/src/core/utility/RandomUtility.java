package core.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import core.entity.Station;

public class RandomUtility {
	
	/**
	 * Generate a random number beetwen 
	 * @param min the inferior born
	 * @param max the maximum born
	 * @return a random number
	 */
	public static int rand(int min, int max) {
        Random rnd = new Random();
        int number = min + rnd.nextInt(max - min + 1);
        return number;
    }
	
	/**
	 * Generate the new destinations of the new passengers of the train
	 * @param nbPassenger the number of new passengers
	 * @param destinations a map of destinations
	 * @return the updated destination map with the new passengers
	 */
	public static Map<Station, Integer> generateDest(int nbPassenger, Map<Station, Integer> destinations) {
		
		if (destinations.size() > 0){			
			int remaining = nbPassenger;
			
			// We create a specific map (station => crowdLevel)
			Map<Station, Integer> drawMap = new HashMap<Station, Integer>();
			for (Entry<Station, Integer> entry : destinations.entrySet()){
				Station station = entry.getKey();
				drawMap.put(station, station.getCrowdLevel());
			}
			
			// Generation of the new destinations in fact of the crowd level of the stations
			while (remaining > 0){
				Station rand = draw(drawMap);
				destinations.put(rand, destinations.get(rand) + 1);
				remaining--;
			}
		}
		return destinations;
	}
	
	/**
	 * Draw an element T in a list
	 * @param map element => probability (0 <= n <= 100)
	 * @return a random element
	 */
	private static <T> T draw(Map<T, Integer> map){
		ArrayList<T> list = new ArrayList<T>();
		for(T key : map.keySet()){
			 // we add X times the element
			 for(int i = 0 ; i < map.get(key) ; i++){
				 list.add(key);
			 }
		}
		Collections.shuffle(list); // we shuffle the list
		return list.get(rand(0, list.size() - 1)); // We draw an element T
	}
}
